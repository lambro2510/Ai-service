package com.lambro2510.service.dto.LanguageDataTraining;

import com.lambro2510.service.entity.types.TextStatus;
import com.lambro2510.service.entity.types.TextTone;
import lombok.Data;

@Data
public class CreateLanguageDataTrainingDto {

  String text;

  TextStatus status;

  Double percent;

  String type;

  TextTone tone;
}
