package com.lambro2510.service.repository;

import com.lambro2510.service.entity.LanguageDataTraining;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageDataTrainingRepository extends MongoRepository<LanguageDataTraining, ObjectId> {

  List<LanguageDataTraining> findByText(String text);

  Page<LanguageDataTraining> findByText(String text, Pageable pageable);
}
