package com.jsonMapper.Repository;

import com.jsonMapper.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerItemOrderRepository extends JpaRepository<Orders, Integer> {
}
