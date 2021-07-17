package com.jsonmapper.controller;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jsonmapper.model.UserMapping;
import com.jsonmapper.service.MasterJsonMappingsService;
import com.jsonmapper.service.UserMappingService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value ="/userMappings")
@FieldDefaults(level= AccessLevel.PUBLIC)
public class UserMappingController {


    @Autowired
    private UserMappingService userMappingService;

    @Autowired
    private MasterJsonMappingsService masterJsonMappingsService;
    @GetMapping("/GetUserMapping/{userId}")
    public String getUserMapping(@PathVariable Long userId) {
        //Get User Mappings from UserId
        try {
            return userMappingService.getUserMapping(userId);
        }
        catch (NullPointerException nullPointerException){
            return null;
        }
    }
    @GetMapping("/GetAllUserMappings")
    public List<UserMapping> getAllUserMappings(){
        //Get All User Mappings
        return userMappingService.getAllUserMappings();
    }
    @DeleteMapping("/DeleteUserMappings/{userId}")
    public String deleteUserMappings(@PathVariable Long userId){
        //DELETE User Mappings from UserId
        String userMap=userMappingService.getUserMapping(userId);
        userMappingService.deleteMappings(userId);
        return userMap;

    }
    @PostMapping("/AddMapping")
    public String addMapping(@RequestBody UserMapping userMap){
        if(getUserMapping(userMap.getUserId())==null) {
            return userMappingService.addMappings(userMap);
        }
        else{
            return "UserId already present";
        }
    }
    @PutMapping("/UpdateMapping")
    public String updateMapping(@RequestBody UserMapping userMap){
        return userMappingService.updateMappings(userMap);
    }
    @GetMapping("/mapOrdersToMappings/{orderId}/{userId}")
    public String mappedJsonData(@PathVariable Long orderId,@PathVariable Long userId) {

        //Getting User Mappings from MongoDb with userId
        String mapData = userMappingService.getUserMapping(userId);
        if (mapData == null){
            return "Please Add User Mappings ";
        }
        JsonObject orderJsonObject;
        JsonObject mapJsonObject = new JsonParser().parse(mapData).getAsJsonObject();
        if (masterJsonMappingsService.findMappingsByMasterId(orderId)!=null) {
            orderJsonObject = new JsonParser().parse(masterJsonMappingsService.findMappingsByMasterId(orderId).getMasterJson()).getAsJsonObject();
        }
        else{
            JSONObject error = new JSONObject();
            error.put("error", "No Order with ID--> " + orderId);
            return error.toJSONString();
        }
        return userMappingService.mapUserMappingsToMaster(mapJsonObject, orderJsonObject).toJSONString();
    }

}
