package com.jsonMapper.service;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jsonMapper.Repository.UserMappingRepository;
import com.jsonMapper.model.userMapping;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


import com.google.gson.JsonParser;
import com.jsonMapper.Repository.OrderRepository;
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
@Service
public class UserMappingService {
    @Autowired
    private UserMappingRepository userMappingRepository;

    public String getUserMapping(Long userId) {
        return userMappingRepository.findByUserId(userId).getMapping();
    }

    public String addMappings(userMapping userMap){
        userMappingRepository.save(userMap);
        return userMappingRepository.findByUserId(userMap.getUserId()).getMapping();
    }

    public void deleteMappings(Long userId){
        userMappingRepository.deleteByUserId(userId);
    }
    public String updateMappings(userMapping userMap){
        userMappingRepository.deleteByUserId(userMap.getUserId());
        return userMappingRepository.save(userMap).getMapping();
    }

    public List<userMapping> getAllUserMappings() {
        return userMappingRepository.findAll();
    }

    public String mapUserMappingsToMaster(JsonObject mapJsonObject,JsonObject orderJsonObject){
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
                                    try {
                                        subobjects.put(mapJsonObject.get(indexobject).getAsString(),object.get(indexobject).getAsJsonPrimitive().getAsInt());
                                    }
                                    catch(NumberFormatException numberFormatException){
                                        subobjects.put(mapJsonObject.get(indexobject).getAsString(),object.get(indexobject).getAsJsonPrimitive().getAsString());
                                    }
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
                                if(orderJsonObject.get(x).getAsJsonObject().get(indexobject).getAsJsonPrimitive().isNumber()) {
                                    jsonobject.put(mapJsonObject.get(indexobject).getAsString(), Math.round(orderJsonObject.get(x).getAsJsonObject().get(indexobject).getAsFloat()));
                                }
                                else {
                                    jsonobject.put(mapJsonObject.get(indexobject).getAsString(),orderJsonObject.get(x).getAsJsonObject().get(indexobject).getAsString());
                                }
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
                        if (orderJsonObject.get(x).getAsJsonPrimitive().isNumber()){
                            outputJson.put(mapJsonObject.get(x).getAsString(),Math.round( orderJsonObject.get(x).getAsFloat()));
                        }
                        else{
                            outputJson.put(mapJsonObject.get(x).getAsString(), orderJsonObject.get(x).getAsString());
                        }
                    }
                } catch (NullPointerException nullPointerException) {
                    System.out.println("-->NPE");
                }
            }
        }
        return outputJson.toJSONString();
    }
}
