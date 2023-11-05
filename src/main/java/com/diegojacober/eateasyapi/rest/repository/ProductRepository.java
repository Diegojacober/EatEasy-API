package com.diegojacober.eateasyapi.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegojacober.eateasyapi.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByOrderByValueDesc();
}