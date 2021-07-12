package com.jsonMapper.Repository;

import com.jsonMapper.model.masterJsonMappings;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterJsonMappingsRepository extends MongoRepository<masterJsonMappings,Long> {
    masterJsonMappings findByOrderId(Long masterId);
    void deleteByOrderId(Long masterId);

}
