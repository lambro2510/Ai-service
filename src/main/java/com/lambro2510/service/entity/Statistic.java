package com.lambro2510.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("statistic")
@Data
public class Statistic {

  private ObjectId id;

  private String key;

  @Field("total_language_good")
  private Long totalLanguageGood = 0L;

  @Field("total_language_normal")
  private Long totalLanguageNormal = 0L;

  @Field("total_language_poor")
  private Long totalLanguagePoor = 0L;

  @JsonIgnore
  public boolean checkStatistic() {
    Long[] values = {totalLanguageGood, totalLanguageNormal, totalLanguagePoor};
    int count = 0;
    Long max = Long.MIN_VALUE;

    for (Long value : values) {
      if (value > max) {
        max = value;
        count = 1;
      } else if (value.equals(max)) {
        count++;
      }
    }

    // Nếu có duy nhất một giá trị lớn nhất hoặc tất cả các giá trị bằng nhau, trả về true
    return count == 1 || count == values.length;
  }


}
