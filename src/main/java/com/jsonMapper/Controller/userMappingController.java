package com.jsonMapper.Controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jsonMapper.Repository.OrderRepository;
import com.jsonMapper.model.Orders;
import com.jsonMapper.model.userMapping;
import com.jsonMapper.service.UserMappingService;
import com.jsonMapper.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value ="/userMappings")
@FieldDefaults(level= AccessLevel.PUBLIC)
public class userMappingController {


    @Autowired
    private UserMappingService userMappingService;
    @Autowired
    private OrderRepository orderRepository;

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
    public String mappedJsonData(@PathVariable int orderId,@PathVariable Long userId){

        //Getting User Mappings from MongoDb with userId
        String mapData=userMappingService.getUserMapping(userId);
        //Getting Order Details from MySQL with OrderId
        Orders orderData=orderRepository.findByOrderId(orderId);

        //Checking if the Order Details for orderId valid or Not
        try {
            System.out.println(orderData.equals(null));
        }

        // If Order Details for orderId is null returning error information to FrontEnd
        catch (NullPointerException nullPointerException){
            JSONObject error = new JSONObject();
            error.put("error","NoOrderID--> "+orderId);
            return error.toJSONString();
        }
        String orderJsonStr="";

        //Creating JsonString from Java Object
        ObjectMapper Obj = new ObjectMapper();
        try {
            orderJsonStr = Obj.writeValueAsString(orderData);
            System.out.println("JSON --> "+orderJsonStr);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //Parsing Json Object from JSON String
        JsonObject mapJsonObject = new JsonParser().parse(mapData).getAsJsonObject();
        JsonObject orderJsonObject = new JsonParser().parse(orderJsonStr).getAsJsonObject();

        //Initializing new JSON Object for O/p
        JSONObject outputJson = new JSONObject();

        //Getting Set of Keys from Orders JSON Object
        Set<String> orderKeys = orderJsonObject.keySet();

        //Iterating orders Json Object with object Keys
        for (String x : orderKeys) {

            //Checking Json Value is Primitive or not
            // ( Arrays and JSON Objects are Not Primitive)
            if (orderJsonObject.get(x).isJsonPrimitive() == false) {
                //Checking is JSON Value is Array or not
                // If Array iterating Through the Array for Json Objects
                if (orderJsonObject.get(x).isJsonArray() == true) {
                    List<JSONObject> itemjsonlist = new ArrayList<JSONObject>();
                    int arraySize = orderJsonObject.get(x).getAsJsonArray().size();
                    Iterator<JsonElement> objectsList = orderJsonObject.get(x).getAsJsonArray().iterator();
                    //If JSON Object is Present then Adding
                    // (value of MapObject(orderKey):value of OrdersObject(orderKey))
                    //on Null Values Skipping the addition of Mapped Data
                    while (objectsList.hasNext()) {
                        JsonObject object = objectsList.next().getAsJsonObject();
                        JSONObject subobjects = new JSONObject();
                        Set<String> subobjectKeys = object.keySet();
                        for (String indexobject : subobjectKeys) {
                            try {
                                if (mapJsonObject.get(indexobject).isJsonNull()) {
                                    //subobjects.put(mapJsonObject.get(indexobject), object.get(indexobject));
                                    System.out.println(object.get(indexobject));
                                } else {
                                    subobjects.put(mapJsonObject.get(indexobject).getAsString(), object.get(indexobject));
                                }
                            } catch (NullPointerException nullPointerException) {
                                System.err.println(nullPointerException);
                            }
                        }
                        itemjsonlist.add(subobjects);
                    }
                    try {
                        if (mapJsonObject.get(x).isJsonNull() != true) {
                            outputJson.put(mapJsonObject.get(x).getAsString(), itemjsonlist);
                        }
                    } catch (NullPointerException nullPointerException) {
                        System.out.println(nullPointerException);
                    }
                } else {
                    //If value is JSON Object
                    //Adding (value of MapObject(orderKey):value of OrdersObject(orderKey))
                    //on Null Values Skipping the addition of Mapped Data
                    JSONObject jsonobject = new JSONObject();
                    Set<String> subobjectKeys = orderJsonObject.get(x).getAsJsonObject().keySet();
                    for (String indexobject : subobjectKeys) {

                        try {
                            System.out.println("!-> " + mapJsonObject.get(indexobject));
                            if (mapJsonObject.get(indexobject).isJsonNull()) {
                                //jsonobject.put(mapJsonObject.get(indexobject), orderJsonObject.get(x).getAsJsonObject().get(indexobject));
                                System.out.println(orderJsonObject.get(x).getAsJsonObject().get(indexobject));
                            } else {
                                jsonobject.put(mapJsonObject.get(indexobject).getAsString(), orderJsonObject.get(x).getAsJsonObject().get(indexobject));
                            }
                        } catch (NullPointerException nullPointerException) {
                            System.out.println(nullPointerException.toString());
                        }
                    }
                    try {
                        if (mapJsonObject.get(x).isJsonNull() != true) {
                            outputJson.put(mapJsonObject.get(x).getAsString(), jsonobject);
                        }
                    } catch (NullPointerException nullPointerException) {
                        System.out.println(nullPointerException);
                    }

                }
            } else {
                //If Primitive Directly Mapping data from Mapping to Orders Data
                try {
                    if (mapJsonObject.get(x).isJsonNull() != true) {
                        outputJson.put(mapJsonObject.get(x).getAsString(), orderJsonObject.get(x).getAsString());
                    }
                } catch (NullPointerException nullPointerException) {
                    System.out.println("-->NPE");
                }
            }
        }
        return outputJson.toJSONString();
    }

}
