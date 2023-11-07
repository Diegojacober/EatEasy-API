package com.diegojacober.eateasyapi.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegojacober.eateasyapi.rest.controller.dto.ProductSalesDTO;
import com.diegojacober.eateasyapi.rest.repository.OrderItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    @Autowired
    private OrderItemRepository repository;

    public ProductSalesDTO sumProductSales(Integer productId) {
        return repository.findProductSummaryById(productId);
    }
}
