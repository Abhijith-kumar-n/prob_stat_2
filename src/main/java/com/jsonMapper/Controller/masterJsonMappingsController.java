package com.jsonMapper.Controller;


import com.jsonMapper.model.masterJsonMappings;
import com.jsonMapper.service.MasterJsonMappingsService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value ="/Master")
@FieldDefaults(level= AccessLevel.PUBLIC)
public class masterJsonMappingsController {

    @Autowired
    private MasterJsonMappingsService masterJsonMappingsService;

    @PostMapping("/AddMaster")
    public masterJsonMappings saveMaster(@RequestBody masterJsonMappings masterMap){
        return masterJsonMappingsService.saveMasterMappings(masterMap);
    }
    @GetMapping("/FindMaster/{masterId}")
    public String findMaster(@PathVariable Long masterId){
        return masterJsonMappingsService.findMappingsByMasterId(masterId).getMasterJson();
    }

    @GetMapping("/FindAllMasters")
    public List<masterJsonMappings> findAllMasters(){
        return masterJsonMappingsService.findAllMasters();
    }
}
