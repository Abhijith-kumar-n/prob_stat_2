package com.jsonMapper.Controller;

import com.jsonMapper.model.Orders;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jsonMapper.service.OrderService;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value ="/mappedData")
@FieldDefaults(level= AccessLevel.PUBLIC)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getOrderDetails/{id}")
    public ResponseEntity<Orders> findById(@PathVariable("id") int id){

        Orders orders=orderService.findByOrderId(id);
        if(orders==null){
            return new ResponseEntity<Orders>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Orders>(orders,HttpStatus.OK);
    }

    @GetMapping("/getAllOrder")
    public List<Orders> getalldetails(){
        List<Orders> ordersList=orderService.getalldetails();
        return ordersList;
    }

    @PostMapping("/takeinput")
    public Orders addDetails(@RequestBody Orders order){

        return orderService.addDetails(order);
        //return order.toString();

    }

}
