package com.lambro2510.service.response;

import com.lambro2510.service.entity.types.TextStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LanguageDataResponse {
  TextStatus status;
  String description;
  double percent;
}
