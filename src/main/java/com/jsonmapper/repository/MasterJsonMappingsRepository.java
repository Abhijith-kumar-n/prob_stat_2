package com.jsonmapper.repository;

import com.jsonmapper.model.MasterJsonMappings;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterJsonMappingsRepository extends MongoRepository<MasterJsonMappings,Long> {
    MasterJsonMappings findByOrderId(Long masterId);
    void deleteByOrderId(Long masterId);

}
