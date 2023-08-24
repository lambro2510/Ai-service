package com.lambro2510.service.service;

import com.lambro2510.service.Utils.DateUtils;
import com.lambro2510.service.Utils.Helper;
import com.lambro2510.service.dto.LanguageDataTraining.CreateLanguageDataTrainingDto;
import com.lambro2510.service.entity.LanguageDataTraining;
import com.lambro2510.service.entity.types.TextAccurate;
import com.lambro2510.service.entity.types.TextStatus;
import com.lambro2510.service.factory.LanguageAiComponent;
import com.lambro2510.service.response.LanguageDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataTrainingService extends BaseService{

  @Autowired @Lazy LanguageAiComponent languageAiComponent;
  public void createLanguageDataTraining(List<CreateLanguageDataTrainingDto> dto){
    for(CreateLanguageDataTrainingDto trainingDto : dto){
      LanguageDataTraining dataTraining = createData(trainingDto.getText(), trainingDto.getStatus(), trainingDto.getPercent());
      languageDataTrainingRepository.save(dataTraining);
    }
  }

  public List<LanguageDataTraining> getAllTrainingData(){
    return languageDataTrainingRepository.findAll();
  }
  private LanguageDataTraining createData(String text, TextStatus status, Double percent){
    return LanguageDataTraining.builder()
        .text(text)
        .status(status)
        .percent(percent)
        .createdAt(DateUtils.getNow())
        .build();
  }

  public LanguageDataResponse getStatusOfText(String text) {
    LanguageDataResponse data = languageAiComponent.getStatus(text);
    if(data.isCorrect() || data.getPercent() > 0.5){
      LanguageDataTraining dataTraining = createData(text, data.getStatus(), data.getPercent());
      languageDataTrainingRepository.save(dataTraining);
    }
    trainingSubText(text);
    return data;
  }

  public void trainingSubText(String text){
    List<String> subTexts = Helper.splitTextIntoSentences(text);
    if(subTexts.size() == 1) return;
    for(String subText : subTexts){
      getStatusOfText(subText);
    }
  }

  public void updateTrainingData() {
    List<LanguageDataTraining> dataTrainings =  languageDataTrainingRepository.findAll();
    for(LanguageDataTraining dataTraining : dataTrainings){
      dataTraining.setPercent(1D);
      dataTraining.setCreatedAt(DateUtils.getNow());
    }
    languageDataTrainingRepository.saveAll(dataTrainings);
  }

  public void updateAccurateTrainingData() {
    List<LanguageDataTraining> dataTrainings =  languageDataTrainingRepository.findAll();
    for(LanguageDataTraining dataTraining : dataTrainings){
      checkTextAccurate(dataTraining);
    }
    languageDataTrainingRepository.saveAll(dataTrainings);
  }

  public void clearDuplicateData() {
    List<LanguageDataTraining> dataTrainings = languageDataTrainingRepository.findAll();

    for (LanguageDataTraining dataTraining : dataTrainings) {
      List<LanguageDataTraining> trainings = languageDataTrainingRepository.findByText(dataTraining.getText());

      if (trainings.size() > 1) {
        LanguageDataTraining newestData = trainings.get(0);
        List<LanguageDataTraining> dataToDelete = new ArrayList<>();

        // Tìm bản ghi mới nhất trong các bản ghi trùng lặp
        for (LanguageDataTraining oldData : trainings) {
          if (oldData.getCreatedAt() > newestData.getCreatedAt()) {
            newestData = oldData;
          }
        }

        // Thêm các bản ghi cần xóa vào danh sách
        for (LanguageDataTraining oldData : trainings) {
          if (!oldData.getId().equals(newestData.getId())) {
            dataToDelete.add(oldData);
          }
        }

        // Xóa các bản ghi trùng lặp
        languageDataTrainingRepository.deleteAll(dataToDelete);
      }
    }
  }

  public void checkTextAccurate(LanguageDataTraining dataTraining){
    Double percent = dataTraining.getPercent();
    if (percent <= 1.0 && percent > 0.9) {
      dataTraining.setAccurate(TextAccurate.PER100);
    } else if (percent <= 0.9 && percent > 0.8) {
      dataTraining.setAccurate(TextAccurate.PER90);
    } else if (percent <= 0.8 && percent > 0.7) {
      dataTraining.setAccurate(TextAccurate.PER80);
    } else if (percent <= 0.7 && percent > 0.6) {
      dataTraining.setAccurate(TextAccurate.PER70);
    } else if (percent <= 0.6 && percent > 0.5) {
      dataTraining.setAccurate(TextAccurate.PER60);
    } else if (percent <= 0.5 && percent > 0.4) {
      dataTraining.setAccurate(TextAccurate.PER50);
    } else if (percent <= 0.4 && percent > 0.3) {
      dataTraining.setAccurate(TextAccurate.PER40);
    } else if (percent <= 0.3 && percent > 0.2) {
      dataTraining.setAccurate(TextAccurate.PER30);
    } else if (percent <= 0.2 && percent > 0.1) {
      dataTraining.setAccurate(TextAccurate.PER20);
    } else if (percent <= 0.1) {
      dataTraining.setAccurate(TextAccurate.PER10);
    } else {
      dataTraining.setAccurate(TextAccurate.PER0);
    }
  }

  public void autoTraining() {
    List<LanguageDataTraining> dataTrainings =  languageDataTrainingRepository.findAll();
    for(LanguageDataTraining dataTraining : dataTrainings){
      getStatusOfText(dataTraining.getText());
    }
  }
}
