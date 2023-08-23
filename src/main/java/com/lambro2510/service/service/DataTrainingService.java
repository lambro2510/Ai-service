package com.lambro2510.service.service;

import com.lambro2510.service.dto.LanguageDataTraining.CreateLanguageDataTrainingDto;
import com.lambro2510.service.entity.LanguageDataTraining;
import com.lambro2510.service.entity.types.TextStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataTrainingService extends BaseService{
  
  public void createLanguageDataTraining(List<CreateLanguageDataTrainingDto> dto){
    for(CreateLanguageDataTrainingDto trainingDto : dto){
      LanguageDataTraining dataTraining = createData(trainingDto.getText(), trainingDto.getStatus(), trainingDto.getPercent());
      languageDataTrainingRepository.save(dataTraining);
    }
  }

  private LanguageDataTraining createData(String text, TextStatus status, Integer percent){
    return LanguageDataTraining.builder()
        .text(text)
        .status(status)
        .percent(percent)
        .build();
  }
}
