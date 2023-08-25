package com.lambro2510.service.response;

import com.lambro2510.service.entity.types.TextStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageDataResponse {
  String id;
  String text;
  TextStatus status;
  String description;
  double percent;
  boolean isCorrect;
}
