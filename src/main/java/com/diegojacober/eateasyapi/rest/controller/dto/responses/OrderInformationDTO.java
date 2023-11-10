package com.diegojacober.eateasyapi.rest.controller.dto.responses;

import java.util.List;

import com.diegojacober.eateasyapi.domain.entity.Restaurant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderInformationDTO {
    private Integer code;
    private String clientName;
    private Double total;
    private String orderDate;
    private List<OrderItemInformationDTO> items;
    private Restaurant restaurant;
}