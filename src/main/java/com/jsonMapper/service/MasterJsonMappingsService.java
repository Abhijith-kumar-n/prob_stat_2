package com.jsonMapper.service;


import com.jsonMapper.Repository.MasterJsonMappingsRepository;
import com.jsonMapper.model.masterJsonMappings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterJsonMappingsService {

    @Autowired
    private MasterJsonMappingsRepository masterJsonMappingsRepository;


    public Logger logger = LoggerFactory.getLogger(MasterJsonMappingsService.class);
    public masterJsonMappings saveMasterMappings(masterJsonMappings master){
        return masterJsonMappingsRepository.save(master);
    }
    public boolean isMasterPresent(Long masterId){
        if (masterJsonMappingsRepository.findByOrderId(masterId)!=null){
            return true;
        }
        else{
            return false;
        }
    }
    public masterJsonMappings findMappingsByMasterId(Long masterId){
        return masterJsonMappingsRepository.findByOrderId(masterId);
    }

    public void deleteMappingsByMasterId(Long masterId){
        masterJsonMappingsRepository.deleteByOrderId(masterId);
    }

    public List<masterJsonMappings> findAllMasters() {
        return masterJsonMappingsRepository.findAll();
    }
}
