package com.lambro2510.service.repository;

import com.lambro2510.service.entity.Statistic;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends MongoRepository<Statistic, ObjectId> {
  Statistic findByKey(String languageStatistic);
}
