package com.lambro2510.service.entity;

import com.lambro2510.service.entity.types.TextStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("language_data_training")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LanguageDataTraining {
  @Id
  private ObjectId id;

  @Field("text")
  private String text;

  @Field("status")
  private TextStatus status;

  @Field("percent")
  private Integer percent;
}
