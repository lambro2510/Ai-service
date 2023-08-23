package com.lambro2510.service.service;

import com.lambro2510.service.repository.LanguageDataTrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseService {
  @Autowired protected LanguageDataTrainingRepository languageDataTrainingRepository;
}
