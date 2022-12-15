package com.zzimkong.h2.yogurt.domain;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YogurtRepository extends ReactiveMongoRepository<Yogurt, String> {
}
