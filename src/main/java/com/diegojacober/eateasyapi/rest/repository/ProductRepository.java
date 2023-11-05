package com.diegojacober.eateasyapi.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegojacober.eateasyapi.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}