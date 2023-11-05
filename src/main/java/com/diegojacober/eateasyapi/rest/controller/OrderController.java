package com.diegojacober.eateasyapi.rest.controller;

import static org.springframework.http.HttpStatus.*;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.diegojacober.eateasyapi.domain.entity.Order;
import com.diegojacober.eateasyapi.domain.entity.OrderItem;
import com.diegojacober.eateasyapi.rest.controller.dto.OrderDTO;
import com.diegojacober.eateasyapi.rest.controller.dto.OrderInformationDTO;
import com.diegojacober.eateasyapi.rest.controller.dto.OrderItemInformationDTO;
import com.diegojacober.eateasyapi.rest.service.OrderService;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody @Valid OrderDTO dto) {
        System.out.println(dto);
        Order pedido = service.save(dto);
        return pedido.getId();
    }

    @GetMapping("/{id}")
    public OrderInformationDTO getById(@PathVariable Integer id) {
        return service.getCompleteOrder(id).map(o -> convert(o))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Order not found"));
    }

    private OrderInformationDTO convert(Order order) {
        return OrderInformationDTO
                .builder()
                .code(order.getId())
                .orderDate(order.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")))
                .clientName(order.getUser().getFirstname())
                .total(order.getTotal())
                .items(convert(order.getItems()))
                .build();
    }

    private List<OrderItemInformationDTO> convert(List<OrderItem> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }

        return items.stream().map(
                item -> OrderItemInformationDTO
                        .builder()
                        .name(item.getProduct().getName())
                        .price(item.getProduct().getValue())
                        .quantity(item.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }
}
