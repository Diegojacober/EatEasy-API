package com.diegojacober.eateasyapi.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diegojacober.eateasyapi.rest.controller.dto.ProductSalesDTO;
import com.diegojacober.eateasyapi.rest.service.OrderItemService;

@RestController
@RequestMapping("/api/v1/sales")
public class SalesController {
    
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/products/{productId}")
    public ProductSalesDTO getProductSales(@PathVariable int productId) {
        return orderItemService.sumProductSales(productId);
    }
}
