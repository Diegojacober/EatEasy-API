package com.diegojacober.eateasyapi.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegojacober.eateasyapi.domain.entity.Restaurant;

public interface RestaurantRepository  extends JpaRepository<Restaurant, Integer>{
    
}
