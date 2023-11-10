package com.diegojacober.eateasyapi.rest.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.diegojacober.eateasyapi.domain.entity.Order;
import com.diegojacober.eateasyapi.domain.entity.OrderItem;
import com.diegojacober.eateasyapi.domain.entity.Product;
import com.diegojacober.eateasyapi.domain.entity.User;
import com.diegojacober.eateasyapi.rest.controller.dto.requests.OrderDTO;
import com.diegojacober.eateasyapi.rest.controller.dto.requests.OrderItemDTO;
import com.diegojacober.eateasyapi.rest.exceptions.BussinessException;
import com.diegojacober.eateasyapi.rest.repository.OrderItemRepository;
import com.diegojacober.eateasyapi.rest.repository.OrderRepository;
import com.diegojacober.eateasyapi.rest.repository.ProductRepository;
import com.diegojacober.eateasyapi.rest.repository.RestaurantRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Transactional
    public Order save(OrderDTO dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Order order = new Order();
        order.setTotal(dto.getTotal());
        order.setOrderDate(LocalDate.now());
        order.setRestaurant(restaurantRepository.findById(dto.getRestaurantId()).orElseThrow(() -> new BussinessException("Restaurant not found")));
        order.setUser(user);

        List<OrderItem> orderItems = convertItems(order, dto.getItems());
        repository.save(order);
        orderItemRepository.saveAll(orderItems);
        order.setItems(orderItems);
        return order;
    }

    private List<OrderItem> convertItems(Order order, List<OrderItemDTO> items) {
        if (items.isEmpty()) {
            throw new BussinessException("An order must have items");
        }

        return items.stream().map(dto -> {
            Integer productId = dto.getProduct();
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new BussinessException("Product not found"));
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(dto.getQuantity());
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            return orderItem;
        }).collect(Collectors.toList());
    }

    public List<Order> getOrdersByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return repository.findByUserOrderByOrderDate(user);
    }

    public Optional<Order> getCompleteOrder(Integer id) {
        return repository.findByIdFetchItems(id);
    }
}
