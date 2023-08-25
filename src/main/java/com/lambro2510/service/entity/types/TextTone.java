package com.lambro2510.service.entity.types;

import lombok.Getter;

@Getter
public enum TextTone {
  FUNNY("Vui vẻ"),
  ANGRY("Tức giận"),
  BORING("Nhàm chán"),
  NORMAL("Bình thường"),
  SARCASTIC("Chế nhạo"),
  EXCITED("Hào hứng"),
  SAD("Buồn bã"),
  CONFUSED("Lúng túng"),
  HAPPY("Hạnh phúc"),
  CALM("Bình tĩnh"),
  MYSTERIOUS("Bí ẩn"),
  INFORMAL("Không chính thống"),
  ROMANTIC("Lãng mạn"),
  PROFESSIONAL("Chuyên nghiệp"),
  HOPEFUL("Hi vọng"),
  CURIOUS("Tò mò"),
  CONFIDENT("Tự tin"),
  GRATEFUL("Biết ơn"),
  INSPIRED("Truyền cảm hứng"),
  TIRED("Mệt mỏi"),
  GLOOMY("U ám"),
  SILLY("Ngốc nghếch"),
  PROUD("Tự hào");

  private final String description;

  TextTone(String description) {
    this.description = description;
  }

}
