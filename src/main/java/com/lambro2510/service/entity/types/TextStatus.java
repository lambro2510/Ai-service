package com.lambro2510.service.entity.types;

import lombok.Getter;

@Getter
public enum TextStatus {
  VERY_GOOD("Rất tốt"),
  GOOD("Tốt"),
  NORMAL("Bình thường"),
  POOR("Kém"),
  VERY_POOR("Rất kém");

  private final String description;

  TextStatus(String description){
    this.description = description;
  }

}
