package com.diegojacober.eateasyapi.rest.controller.dto;

import java.util.List;

import com.diegojacober.eateasyapi.rest.validation.NotEmptyList;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    @NotNull
    private Double total;

    @NotEmptyList
    private List<OrderItemDTO> items;
}
