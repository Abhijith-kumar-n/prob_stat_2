package com.jsonmapper.service;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jsonmapper.repository.UserMappingRepository;
import com.jsonmapper.model.UserMapping;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
@Service
public class UserMappingService {
    @Autowired
    private UserMappingRepository userMappingRepository;

    static final Logger logger= LoggerFactory.getLogger(UserMappingService.class);
    public String getUserMapping(Long userId) {
        return userMappingRepository.findByUserId(userId).getMapping();
    }

    public String addMappings(UserMapping userMap){
        userMappingRepository.save(userMap);
        try {
            return userMappingRepository.findByUserId(userMap.getUserId()).getMapping();
        }
        catch (NullPointerException nullPointerException){
            return "invalid Request";
        }
    }

    public void deleteMappings(Long userId){
        userMappingRepository.deleteByUserId(userId);
    }
    public String updateMappings(UserMapping userMap){
        userMappingRepository.deleteByUserId(userMap.getUserId());
        try {
            return userMappingRepository.save(userMap).getMapping();
        }
        catch (NullPointerException nullPointerException){
            return "invalid Request";
        }
    }

    public List<UserMapping> getAllUserMappings() {
        return userMappingRepository.findAll();
    }


    //Custom JSON Mapping Controller Algo
    //I/P : Mappings (JSON Object), Master Object (JSON Object)
    //O/P : Master Object mapped to user mappings (stringified JSON Object)
    public String mapUserMappingsToMaster(JsonObject mapJsonObject,JsonObject orderJsonObject){
        //Initializing new JSON Object for O/p
        JSONObject outputJson = new JSONObject();

        //Getting Set of Keys from Orders JSON Object
        Set<String> orderKeys = orderJsonObject.keySet();

        //Iterating orders Json Object with object Keys
        for (String x : orderKeys) {

            //Checking Json Value is Primitive or not
            // ( Arrays and JSON Objects are Not Primitive)
            if (!orderJsonObject.get(x).isJsonPrimitive()) {
                //Checking is JSON Value is Array or not
                // If Array iterating Through the Array for Json Objects
                if (orderJsonObject.get(x).isJsonArray()) {
                    List<JSONObject> itemjsonlist = new ArrayList<>();
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
                                if (!mapJsonObject.get(indexobject).isJsonNull()) {
                                    subobjects.put(mapJsonObject.get(indexobject).getAsString(),object.get(indexobject).getAsJsonPrimitive().getAsInt());

                                }
                            } catch (NullPointerException nullPointerException) {
                                logger.warn(String.valueOf(nullPointerException));
                            }
                            catch(NumberFormatException numberFormatException){
                                subobjects.put(mapJsonObject.get(indexobject).getAsString(),object.get(indexobject).getAsJsonPrimitive().getAsString());
                            }
                        }
                        itemjsonlist.add(subobjects);
                    }
                    try {
                        if (!mapJsonObject.get(x).isJsonNull()) {
                            outputJson.put(mapJsonObject.get(x).getAsString(), itemjsonlist);
                        }
                    } catch (NullPointerException nullPointerException) {
                        logger.warn(String.valueOf(nullPointerException));
                    }
                } else {
                    //If value is JSON Object
                    //Adding (value of MapObject(orderKey):value of OrdersObject(orderKey))
                    //on Null Values Skipping the addition of Mapped Data
                    JSONObject jsonobject = new JSONObject();
                    Set<String> subobjectKeys = orderJsonObject.get(x).getAsJsonObject().keySet();
                    for (String indexobject : subobjectKeys) {

                        try {
                            if (!mapJsonObject.get(indexobject).isJsonNull()) {
                                if(orderJsonObject.get(x).getAsJsonObject().get(indexobject).getAsJsonPrimitive().isNumber()) {
                                    jsonobject.put(mapJsonObject.get(indexobject).getAsString(), Math.round(orderJsonObject.get(x).getAsJsonObject().get(indexobject).getAsFloat()));
                                }
                                else {
                                    jsonobject.put(mapJsonObject.get(indexobject).getAsString(),orderJsonObject.get(x).getAsJsonObject().get(indexobject).getAsString());
                                }
                                jsonobject.put(mapJsonObject.get(indexobject).getAsString(), orderJsonObject.get(x).getAsJsonObject().get(indexobject));
                            }
                        } catch (NullPointerException nullPointerException) {
                            logger.warn(nullPointerException.toString());
                        }
                    }
                    try {
                        if (!mapJsonObject.get(x).isJsonNull()) {
                            outputJson.put(mapJsonObject.get(x).getAsString(), jsonobject);
                        }
                    } catch (NullPointerException nullPointerException) {
                        logger.warn(String.valueOf(nullPointerException));
                    }

                }
            } else {
                //If Primitive Directly Mapping data from Mapping to Orders Data
                try {
                    if (!mapJsonObject.get(x).isJsonNull()) {
                        if (orderJsonObject.get(x).getAsJsonPrimitive().isNumber()){
                            outputJson.put(mapJsonObject.get(x).getAsString(),Math.round( orderJsonObject.get(x).getAsFloat()));
                        }
                        else{
                            outputJson.put(mapJsonObject.get(x).getAsString(), orderJsonObject.get(x).getAsString());
                        }
                    }
                } catch (NullPointerException nullPointerException) {
                    logger.debug("-->NPE");
                }
            }
        }
        return outputJson.toJSONString();
    }
}
