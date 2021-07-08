/*package com.jsonMapper.Controller;


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


 */
/*
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
        System.out.println("in add mapping");
        return mappingService.addMappings(maps);
    }
    @PostMapping("/updateMapping")
    mapping updateMappingData(@RequestBody mapping maps){
        return mappingService.updateMapping(maps);
    }
    @GetMapping("/mapuser/{orderid}/{userid}")
    String mapUsersData(@PathVariable int orderid,@PathVariable int userid){
        Orders orders;
        orders = orderService.findByOrderId(orderid);
        mapping map= mappingService.getMapping(userid);

        JSONObject custumerJson=new JSONObject();
        List<JSONObject> itemjsonlist=new ArrayList<JSONObject>();

        logger.debug(String.valueOf(orders.getItem().size()));
        for(int i=0;i<orders.getItem().size();i++){
            JSONObject itemobject=new JSONObject();
            itemobject.put(map.getItemId(),orders.getItem().get(i).getItemId());
            itemobject.put(map.getItemPrice(),orders.getItem().get(i).getItemPrice());
            itemobject.put(map.getItemDiscount(),orders.getItem().get(i).getItemDiscount());
            itemobject.put(map.getItemStatus(),orders.getItem().get(i).getItemStatus());
            itemobject.put(map.getSkuId(),orders.getItem().get(i).getSkuId());
            itemobject.remove(null);
            itemjsonlist.add(itemobject);
        }
        custumerJson.put(map.getFirstName(),orders.getCustomer().getFirstName());
        custumerJson.put(map.getMiddleName(),orders.getCustomer().getLastName());
        custumerJson.put(map.getLastName(),orders.getCustomer().getMiddleName());
        custumerJson.put(map.getEmail(),orders.getCustomer().getEmail());
        custumerJson.put(map.getPhoneNo(),orders.getCustomer().getPhoneNumber());
        custumerJson.put(map.getPincode(),orders.getCustomer().getPincode());
        custumerJson.put(map.getAddress(),orders.getCustomer().getAddress());
        custumerJson.remove(null);
        JSONObject orderjson = new JSONObject();
        orderjson.put(map.getOrderId(),orders.getOrderId());
        orderjson.put(map.getDate(),orders.getDate());
        orderjson.put(map.getTotalSellingPrice(),orders.getTotalSellingPrice());
        orderjson.put(map.getTotalDiscount(),orders.getTotalDiscount());
        orderjson.put(map.getTenentId(),orders.getTenantId());
        orderjson.put(map.getStatus(),orders.getStatus());
        orderjson.put(map.getItem(),itemjsonlist);
        orderjson.put(map.getCustomer(),custumerJson);
        orderjson.remove(null);
        return JSONObject.toJSONString(orderjson);
    }


}


 */


