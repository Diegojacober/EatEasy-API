package com.diegojacober.eateasyapi.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegojacober.eateasyapi.domain.entity.OrderItem;

public interface OrderItemRepository  extends JpaRepository<OrderItem, Integer>{
    
}