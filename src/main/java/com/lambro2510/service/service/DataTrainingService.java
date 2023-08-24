package com.lambro2510.service.service;

import com.lambro2510.service.dto.LanguageDataTraining.CreateLanguageDataTrainingDto;
import com.lambro2510.service.entity.LanguageDataTraining;
import com.lambro2510.service.entity.types.TextAccurate;
import com.lambro2510.service.entity.types.TextStatus;
import com.lambro2510.service.factory.LanguageAiComponent;
import com.lambro2510.service.response.LanguageDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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
        .build();
  }

  public LanguageDataResponse getStatusOfText(String text) {
    LanguageDataResponse data = languageAiComponent.getStatus(text);
    if(data.isCorrect()){
      LanguageDataTraining dataTraining = createData(text, data.getStatus(), data.getPercent());
      languageDataTrainingRepository.save(dataTraining);
    }
    return data;
  }

  public void updateTrainingData() {
    List<LanguageDataTraining> dataTrainings =  languageDataTrainingRepository.findAll();
    for(LanguageDataTraining dataTraining : dataTrainings){
      dataTraining.setPercent(1D);
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
}
