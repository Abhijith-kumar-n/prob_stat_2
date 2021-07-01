package com.jsonMapper.Repository;

import com.jsonMapper.model.mapping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MappingRepository extends MongoRepository<mapping,Integer> {
    mapping findByUserId(int userId);
    mapping deleteByUserId(int UserId);
}
