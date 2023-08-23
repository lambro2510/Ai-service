package com.lambro2510.service.entity.types;

import lombok.Getter;

@Getter
public enum TextStatus {
  EXCELLENT("Xuất sắc"),
  GOOD("Tốt"),
  VERY_GOOD("Rất tốt"),
  AVERAGE("Trung bình"),
  POOR("Yếu"),
  BAD("Kém"),
  VERY_POOR("Rất kém"),
  OUTSTANDING("Nổi bật"),
  BELOW_AVERAGE("Dưới trung bình"),
  SUPERB("Tuyệt vời"),
  MEDIOCRE("Bình thường"),
  IMPRESSIVE("ấn tượng");

  private final String description;

  TextStatus(String description){
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
