package com.lambro2510.service.service;

import com.lambro2510.service.dto.LanguageDataTraining.CreateLanguageDataTrainingDto;
import com.lambro2510.service.entity.LanguageDataTraining;
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
    LanguageDataTraining dataTraining = createData(text, data.getStatus(), data.getPercent());
    languageDataTrainingRepository.save(dataTraining);
    return data;
  }

  public void updateTrainingData() {
    List<LanguageDataTraining> dataTrainings =  languageDataTrainingRepository.findAll();
    for(LanguageDataTraining dataTraining : dataTrainings){
      dataTraining.setPercent(1D);
    }
    languageDataTrainingRepository.saveAll(dataTrainings);
  }
}
