/*package com.jsonMapper.service;


import com.jsonMapper.Repository.MappingRepository;
import com.jsonMapper.model.mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MappingService {

    @Autowired
    private MappingRepository mappingRepository;

    public mapping addMappings(mapping maps){
        return mappingRepository.save(maps);
    }
    public List<mapping> getAllMappings(){
        return mappingRepository.findAll();
    }
    public mapping deleteMapping(int Id){
        mapping map= mappingRepository.findByUserId(Id);
        mappingRepository.deleteByUserId(Id);
        return map;
    }
    public mapping getMapping(int id){
        return mappingRepository.findByUserId(id);

    }
    public mapping updateMapping(mapping maps){
        mappingRepository.deleteByUserId(maps.getUserId());
        return mappingRepository.save(maps);
    }
}


 */