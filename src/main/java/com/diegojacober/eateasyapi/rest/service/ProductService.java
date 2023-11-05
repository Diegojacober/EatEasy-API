package com.diegojacober.eateasyapi.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegojacober.eateasyapi.domain.entity.Product;
import com.diegojacober.eateasyapi.rest.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAllByOrderByValueDesc();
    }
}
