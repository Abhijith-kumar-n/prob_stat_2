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
        return userMappingService.getUserMapping(userId);
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

        return userMappingService.addMappings(userMap);
    }
    @PostMapping("/UpdateMapping")
    public String updateMapping(@RequestBody userMapping userMap){
        return userMappingService.updateMappings(userMap);
    }
    @GetMapping("/mapOrdersToMappings/{orderId}/{userId}")
    public String mappedJsonData(@PathVariable Long orderId,@PathVariable Long userId) {

        //Getting User Mappings from MongoDb with userId
        String mapData = userMappingService.getUserMapping(userId);
        /*
        //Getting Order Details from MySQL with OrderId
        Orders orderData = orderRepository.findByOrderId(orderId);

        //Checking if the Order Details for orderId valid or Not
        try {
            System.out.println(orderData.equals(null));
        }
        // If Order Details for orderId is null returning error information to FrontEnd
        catch (NullPointerException nullPointerException) {
            JSONObject error = new JSONObject();
            error.put("error", "NoOrderID--> " + orderId);
            return error.toJSONString();
        }

        String orderJsonStr = "";
        //Creating JsonString from Java Object
        ObjectMapper Obj = new ObjectMapper();
        try {
            orderJsonStr = Obj.writeValueAsString(orderData);
            System.out.println("JSON --> " + orderJsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Parsing Json Object from JSON String

         */
        JsonObject mapJsonObject = new JsonParser().parse(mapData).getAsJsonObject();
        JsonObject orderJsonObject = new JsonParser().parse(masterJsonMappingsService.findMappingsByMasterId(orderId).getMasterJson()).getAsJsonObject();
        return userMappingService.mapUserMappingsToMaster(mapJsonObject, orderJsonObject);
    }

}
