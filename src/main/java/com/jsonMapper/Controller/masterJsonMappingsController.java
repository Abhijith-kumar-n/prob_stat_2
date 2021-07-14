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
    public String saveMaster(@RequestBody masterJsonMappings masterMap){
        if(masterJsonMappingsService.isMasterPresent(masterMap.getOrderId())){
            return "Present";
        }
        else {
            return masterJsonMappingsService.saveMasterMappings(masterMap).toString();
        }
    }
    @GetMapping("/FindMaster/{orderId}")
    public String findMaster(@PathVariable Long orderId){
        try {
            return masterJsonMappingsService.findMappingsByMasterId(orderId).getMasterJson();
        }
        catch (NullPointerException nullPointerException){
            return "Invalid orderId";
        }
    }

    @GetMapping("/FindAllMasters")
    public List<masterJsonMappings> findAllMasters(){
        return masterJsonMappingsService.findAllMasters();
    }
}
