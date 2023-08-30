package com.lambro2510.service.service;

//import com.lambro2510.service.Utils.cache.LockManager;
import com.lambro2510.service.repository.LanguageDataTrainingRepository;
import com.lambro2510.service.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseService {
  @Autowired protected LanguageDataTrainingRepository languageDataTrainingRepository;
  @Autowired protected StatisticRepository statisticRepository;
//  @Autowired protected LockManager lockManager;
}
