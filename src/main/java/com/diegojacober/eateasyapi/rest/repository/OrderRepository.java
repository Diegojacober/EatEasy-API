package com.diegojacober.eateasyapi.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.diegojacober.eateasyapi.domain.entity.Order;
import com.diegojacober.eateasyapi.domain.entity.User;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("select o from Order o left join fetch o.items where o.id = :id")
    Optional<Order> findByIdFetchItems(@Param("id") Integer id);

    List<Order> findByUserOrderByOrderDateDesc(User user);
}
