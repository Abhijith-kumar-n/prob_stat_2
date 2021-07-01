package com.jsonMapper.Controller;


import com.jsonMapper.model.Orders;
import com.jsonMapper.model.mapping;
import com.jsonMapper.service.MappingService;
import com.jsonMapper.service.OrderService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value ="/mapping")
@FieldDefaults(level= AccessLevel.PUBLIC)
public class mappingController {

    @Autowired
    private MappingService mappingService;

    @Autowired
    private OrderService orderService;
    Logger logger = LoggerFactory.getLogger(mappingController.class);
    @GetMapping("/getAllMappings")
    public List<mapping> findAllMappings(){
        return mappingService.getAllMappings();
    }
    @GetMapping("/getmapping/{id}")
    public mapping findMappingById(@PathVariable("id") int id){
        return mappingService.getMapping(id);
    }
    @GetMapping("/deleteMapping/{id}")
    public mapping deleteMappings(@PathVariable("id") int id){
        return mappingService.deleteMapping(id);
    }
    @PostMapping("/addMapping")
    mapping addMappingData(@RequestBody mapping maps){
        return mappingService.addMappings(maps);
    }

    @GetMapping("/mapuser/{orderid}/{userid}")
    String mapUsersData(@PathVariable int orderid,@PathVariable int userid){
        Orders orders;
        orders = orderService.findByOrderId(orderid);
        mapping map= mappingService.getMapping(userid);
        JSONObject itemobject=new JSONObject();
        JSONObject custumerJson=new JSONObject();
        List<JSONObject> itemjsonlist=new ArrayList<JSONObject>();
        logger.debug(String.valueOf(orders.getItemId().size()));
        for(int i=0;i<orders.getItemId().size();i++){
            itemobject.put(map.getItemId(),orders.getItemId().get(i).getItemId());
            itemobject.put(map.getItemPrice(),orders.getItemId().get(i).getItemPrice());
            itemobject.put(map.getItemDiscount(),orders.getItemId().get(i).getItemDiscount());
            itemobject.put(map.getItemStatus(),orders.getItemId().get(i).getStatus());
            itemobject.put(map.getSkuId(),orders.getItemId().get(i).getSkuId());
            itemjsonlist.add(itemobject);
        }
        custumerJson.put(map.getFirstName(),orders.getCustomerId().getFirstName());
        custumerJson.put(map.getMiddleName(),orders.getCustomerId().getLastName());
        custumerJson.put(map.getLastName(),orders.getCustomerId().getMiddleName());
        custumerJson.put(map.getEmail(),orders.getCustomerId().getEmail());
        custumerJson.put(map.getPhoneNo(),orders.getCustomerId().getPhoneNumber());
        custumerJson.put(map.getPincode(),orders.getCustomerId().getPincode());
        custumerJson.put(map.getAddress(),orders.getCustomerId().getAddress());
        JSONObject orderjson = new JSONObject();
        orderjson.put(map.getOrderId(),orders.getOrderId());
        orderjson.put(map.getDate(),orders.getDate());
        orderjson.put(map.getTotalSellingPrice(),orders.getTotalSellingPrice());
        orderjson.put(map.getTotalDiscount(),orders.getTotalDiscount());
        orderjson.put(map.getTenentId(),orders.getTenantId());
        orderjson.put(map.getStatus(),orders.getStatus());
        orderjson.put(map.getItem(),itemjsonlist);
        orderjson.put(map.getCustomer(),custumerJson);
        return JSONObject.toJSONString(orderjson);
    }


}



