package com.lambro2510.service.schedule;

import com.lambro2510.service.service.DataTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LanguageSchedule {
  @Autowired
  DataTrainingService dataTrainingService;
  @Scheduled( fixedRate = 60000)
  public void cleanData(){
    dataTrainingService.clearDuplicateData();
  }
}
