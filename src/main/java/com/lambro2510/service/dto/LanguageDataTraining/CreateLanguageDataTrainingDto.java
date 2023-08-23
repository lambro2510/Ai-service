package com.lambro2510.service.dto.LanguageDataTraining;

import com.lambro2510.service.entity.types.TextStatus;
import lombok.Data;

@Data
public class CreateLanguageDataTrainingDto {

  String text;

  TextStatus status;

  Double percent;
}
