package com.lambro2510.service.repository;

import com.lambro2510.service.entity.LanguageDataTraining;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageDataTrainingRepository extends MongoRepository<LanguageDataTraining, ObjectId> {
}
