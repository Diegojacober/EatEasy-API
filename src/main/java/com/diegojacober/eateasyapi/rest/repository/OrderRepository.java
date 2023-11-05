package com.diegojacober.eateasyapi.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegojacober.eateasyapi.domain.entity.Order;

public interface OrderRepository  extends JpaRepository<Order, Integer>{
    
}
