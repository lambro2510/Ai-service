package com.lambro2510.service.entity.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TextAccurate {
  PER100("Văn bản hoàn toàn chính xác và có thể tin cậy trong mọi trường hợp"),
  PER90("Văn bản chính xác trong hầu hết các tình huống, chỉ có ít trường hợp đặc biệt có thể dẫn đến sai sót nhỏ"),
  PER80("Văn bản có độ chính xác khá cao và thường được sử dụng một cách tin cậy"),
  PER70("Văn bản chính xác ở mức trung bình, thích hợp cho nhiều trường hợp sử dụng"),
  PER60("Văn bản không đủ chính xác để đáp ứng yêu cầu nhiều trường hợp, cần cân nhắc khi sử dụng"),
  PER50("Văn bản chỉ đạt được mức độ chính xác ở mức trung bình, thường không thể tin cậy"),
  PER40("Văn bản thường không chính xác, cần rất nhiều cải thiện"),
  PER30("Văn bản thường sai lệch và không thể sử dụng một cách đáng tin cậy"),
  PER20("Văn bản có ít thông tin chính xác và hiếm khi có thể sử dụng"),
  PER10("Văn bản rất không chính xác và không thể sử dụng trong hầu hết các tình huống"),
  PER0("Văn bản hoàn toàn sai lệch và không thể tin cậy trong bất kỳ trường hợp nào");

  String description;

  public String getDescription() {
    return description;
  }
}
