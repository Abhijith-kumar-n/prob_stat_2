package com.jsonmapper.service;


import com.jsonmapper.repository.MasterJsonMappingsRepository;
import com.jsonmapper.model.MasterJsonMappings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterJsonMappingsService {

    @Autowired
    private MasterJsonMappingsRepository masterJsonMappingsRepository;


    static final Logger logger = LoggerFactory.getLogger(MasterJsonMappingsService.class);
    public MasterJsonMappings saveMasterMappings(MasterJsonMappings master){
        return masterJsonMappingsRepository.save(master);
    }
    public boolean isMasterPresent(Long masterId){
        return (masterJsonMappingsRepository.findByOrderId(masterId) != null);
    }
    public MasterJsonMappings findMappingsByMasterId(Long masterId){
        return masterJsonMappingsRepository.findByOrderId(masterId);
    }

    public void deleteMappingsByMasterId(Long masterId){
        masterJsonMappingsRepository.deleteByOrderId(masterId);
    }

    public List<MasterJsonMappings> findAllMasters() {
        return masterJsonMappingsRepository.findAll();
    }
}
