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
    if(getTotalLanguageGood() > getTotalLanguageNormal() && getTotalLanguageGood() > getTotalLanguagePoor()){
      return false;

    }
    setTotalLanguageGood(getTotalLanguageGood() + value);
    return true;
  }

  public boolean addNormal(long value){
    if(getTotalLanguageNormal() > getTotalLanguageGood() && getTotalLanguageNormal() > getTotalLanguagePoor()){
      return false;

    }
    setTotalLanguageGood(getTotalLanguageGood() + value);
    return true;
  }

  public boolean addPoor(long value){
    if(getTotalLanguagePoor() > getTotalLanguageGood() && getTotalLanguagePoor() > getTotalLanguageNormal()){
      return false;
    }
    setTotalLanguageGood(getTotalLanguageGood() + value);
    return true;
  }
}
