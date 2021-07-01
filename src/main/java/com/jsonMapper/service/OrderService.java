package com.jsonMapper.service;

import com.jsonMapper.Repository.CustomerItemOrderRepository;
import com.jsonMapper.Repository.OrderRepository;
import com.jsonMapper.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerItemOrderRepository customerItemOrderRepository;

    public List<Orders> getalldetails(){
        List<Orders> list=orderRepository.findAll();
        return list;
    }

    public Orders findByOrderId(int id){

        Orders orders= orderRepository.findByOrderId(id);
        return orders;
    }

    public Orders addDetails(Orders order){
        Orders order1=orderRepository.save(order);
        return order1;
    }
}

