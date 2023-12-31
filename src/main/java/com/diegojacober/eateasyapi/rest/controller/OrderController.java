package com.diegojacober.eateasyapi.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.diegojacober.eateasyapi.domain.entity.Order;
import com.diegojacober.eateasyapi.domain.entity.OrderItem;
import com.diegojacober.eateasyapi.rest.controller.dto.requests.OrderDTO;
import com.diegojacober.eateasyapi.rest.controller.dto.responses.OrderInformationDTO;
import com.diegojacober.eateasyapi.rest.controller.dto.responses.OrderItemInformationDTO;
import com.diegojacober.eateasyapi.rest.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody @Valid OrderDTO dto) {
        Order pedido = service.save(dto);
        return pedido.getId();
    }

    @GetMapping("/{id}")
    public OrderInformationDTO getById(@PathVariable Integer id) {
        return service.getCompleteOrder(id).map(o -> convert(o))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Order not found"));
    }

    @GetMapping("myorders")
    public ResponseEntity<?> getByUser() {
        List<Order> ordersByUser = service.getOrdersByUser();
        List<OrderInformationDTO> dto = new ArrayList<>();

        for (Order order : ordersByUser) {
            dto.add(getById(order.getId()));
        }

        Map<String, List<OrderInformationDTO>> responseData = new HashMap<>();
        responseData.put("data", dto);

        return ResponseEntity.ok(responseData);
    }

    @GetMapping("restaurants/{id}")
    public ResponseEntity<?> getByRestaurant(@PathVariable Integer id) {
        List<Order> ordersByRestaurant = service.getOrdersByRestaurant(id);
        List<OrderInformationDTO> dto = new ArrayList<>();

        for (Order order : ordersByRestaurant) {
            dto.add(getById(order.getId()));
        }

        Map<String, List<OrderInformationDTO>> responseData = new HashMap<>();
        responseData.put("data", dto);

        return ResponseEntity.ok(responseData);
    }

    private OrderInformationDTO convert(Order order) {
        return OrderInformationDTO
                .builder()
                .code(order.getId())
                .orderDate(order.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")))
                .clientName(order.getUser().getFirstname())
                .total(order.getTotal())
                .items(convert(order.getItems()))
                .restaurant(order.getRestaurant())
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
