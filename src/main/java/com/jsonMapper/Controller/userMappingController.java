package com.jsonMapper.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jsonMapper.Repository.OrderRepository;
import com.jsonMapper.model.Orders;
import com.jsonMapper.model.userMapping;
import com.jsonMapper.service.MasterJsonMappingsService;
import com.jsonMapper.service.UserMappingService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value ="/userMappings")
@FieldDefaults(level= AccessLevel.PUBLIC)
public class userMappingController {


    @Autowired
    private UserMappingService userMappingService;
    @Autowired
    private OrderRepository orderRepository;
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
    public List<userMapping> getAllUserMappings(){
        //Get All User Mappings
        return userMappingService.getAllUserMappings();
    }
    @GetMapping("/DeleteUserMappings/{userId}")
    public String deleteUserMappings(@PathVariable Long userId){
        //DELETE User Mappings from UserId
        String userMap=userMappingService.getUserMapping(userId);
        userMappingService.deleteMappings(userId);
        return userMap;

    }
    @PostMapping("/AddMapping")
    public String addMapping(@RequestBody userMapping userMap){
        if(getUserMapping(userMap.getUserId())==null) {
            return userMappingService.addMappings(userMap);
        }
        else{
            return "UserId already present";
        }
    }
    @PostMapping("/UpdateMapping")
    public String updateMapping(@RequestBody userMapping userMap){
        return userMappingService.updateMappings(userMap);
    }
    @GetMapping("/mapOrdersToMappings/{orderId}/{userId}")
    public String mappedJsonData(@PathVariable Long orderId,@PathVariable Long userId) {

        //Getting User Mappings from MongoDb with userId
        String mapData = userMappingService.getUserMapping(userId);

        JsonObject orderJsonObject;//=new JsonObject();
        JsonObject mapJsonObject = new JsonParser().parse(mapData).getAsJsonObject();
        if (masterJsonMappingsService.findMappingsByMasterId(orderId)!=null) {
            orderJsonObject = new JsonParser().parse(masterJsonMappingsService.findMappingsByMasterId(orderId).getMasterJson()).getAsJsonObject();
        }
        else{
            JSONObject error = new JSONObject();
            error.put("error", "No Order with ID--> " + orderId);
            return error.toJSONString();
        }
        return userMappingService.mapUserMappingsToMaster(mapJsonObject, orderJsonObject);
    }

}
