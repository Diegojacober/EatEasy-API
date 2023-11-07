package com.diegojacober.eateasyapi.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.diegojacober.eateasyapi.domain.entity.OrderItem;
import com.diegojacober.eateasyapi.rest.controller.dto.ProductSalesDTO;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    // SELECT oi.product_id,
    // pr.name,
    // pr.value,
    // (SELECT SUM(quantity) FROM order_item WHERE product_id = oi.product_id) as
    // total_quantity,
    // pr.value * (SELECT SUM(quantity) FROM order_item WHERE product_id = oi.product_id) as total
    // FROM order_item oi
    // INNER JOIN products pr ON oi.product_id = pr.id
    // WHERE oi.product_id = 1
    // LIMIT 1;

    @Query("SELECT NEW com.diegojacober.eateasyapi.rest.controller.dto.ProductSalesDTO( " +
        "oi.product.id, " + 
        "pr.name, " +
        "pr.value, " +
        "(SELECT SUM(oi2.quantity) FROM OrderItem oi2 WHERE oi2.product.id = oi.product.id), " +
        "pr.value * (SELECT SUM(quantity) FROM OrderItem oi2 WHERE oi2.product.id = oi.product.id) )" +
        " FROM OrderItem oi INNER JOIN oi.product pr WHERE oi.product.id = :productId ORDER BY pr.id LIMIT 1")
    ProductSalesDTO findProductSummaryById(@Param("productId") Integer productId);
}