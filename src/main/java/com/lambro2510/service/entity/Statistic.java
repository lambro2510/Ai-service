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

  public boolean addGood(long value){
    if(getTotalLanguageGood() > getTotalLanguageNormal() * 1.5 || getTotalLanguageGood() > getTotalLanguagePoor() * 1.5){
      return false;

    }
    setTotalLanguageGood(getTotalLanguageGood() + value);
    return true;
  }

  public boolean addNormal(long value){
    if(getTotalLanguageNormal() > getTotalLanguageGood() * 1.5|| getTotalLanguageNormal() > getTotalLanguagePoor() * 1.5){
      return false;

    }
    setTotalLanguageNormal(getTotalLanguageNormal() + value);
    return true;
  }

  public boolean addPoor(long value){
    if(getTotalLanguagePoor() > getTotalLanguageGood() * 1.5|| getTotalLanguagePoor() > getTotalLanguageNormal() * 1.5){
      return false;
    }
    setTotalLanguagePoor(getTotalLanguagePoor() + value);
    return true;
  }
}
