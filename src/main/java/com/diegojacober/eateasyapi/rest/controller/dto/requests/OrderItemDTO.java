package com.diegojacober.eateasyapi.rest.controller.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    @NotBlank
    private Integer product;
    @NotBlank
    private Integer quantity;
}