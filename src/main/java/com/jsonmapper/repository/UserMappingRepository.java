package com.jsonmapper.repository;

import com.jsonmapper.model.UserMapping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMappingRepository extends MongoRepository<UserMapping,Long> {
    UserMapping findByUserId(long userId);
    void deleteByUserId(long userId);
}
