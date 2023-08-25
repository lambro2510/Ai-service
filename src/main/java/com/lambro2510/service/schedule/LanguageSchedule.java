package com.lambro2510.service.schedule;

import com.lambro2510.service.factory.LanguageAiComponent;
import com.lambro2510.service.service.DataTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LanguageSchedule {
  @Autowired
  DataTrainingService dataTrainingService;
  @Autowired
  LanguageAiComponent languageAiComponent;

  @Scheduled( fixedRate = 100000)
  public void updateModule() throws IOException {
    languageAiComponent.updateModule();
  }

  @Scheduled(fixedDelay = 30000, fixedRate = 1000 * 60 * 30)
  public void autoTraining() throws IOException {
    dataTrainingService.autoTraining();
  }
}
