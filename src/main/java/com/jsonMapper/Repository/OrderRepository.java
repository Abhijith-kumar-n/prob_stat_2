package com.jsonMapper.Repository;

import com.jsonMapper.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {
    public Orders findByOrderId(int id);
}
