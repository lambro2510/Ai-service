package com.lambro2510.service.service;

import com.lambro2510.service.dto.LanguageDataTraining.CreateLanguageDataTrainingDto;
import com.lambro2510.service.entity.LanguageDataTraining;
import com.lambro2510.service.entity.types.TextStatus;
import org.springframework.stereotype.Service;

@Service
public class DataTrainingService extends BaseService{
  
  public void createLanguageDataTraining(CreateLanguageDataTrainingDto dto){
    LanguageDataTraining dataTraining = createData(dto.getText(), dto.getStatus(), dto.getPercent());
    languageDataTrainingRepository.save(dataTraining);
  }

  private LanguageDataTraining createData(String text, TextStatus status, Integer percent){
    return LanguageDataTraining.builder()
        .text(text)
        .status(status)
        .percent(percent)
        .build();
  }
}
