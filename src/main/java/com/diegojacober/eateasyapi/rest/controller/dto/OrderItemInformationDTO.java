package com.diegojacober.eateasyapi.rest.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemInformationDTO {
    private String name;
    private Double price;
    private Integer quantity;
}
