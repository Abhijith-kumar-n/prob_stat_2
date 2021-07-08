package com.jsonMapper.Repository;

import com.jsonMapper.model.userMapping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMappingRepository extends MongoRepository<userMapping,Long> {
    userMapping findByUserId(long userId);
    void deleteByUserId(long userId);
}
