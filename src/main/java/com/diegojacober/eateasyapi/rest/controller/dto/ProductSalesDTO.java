package com.diegojacober.eateasyapi.rest.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSalesDTO {
    private Integer productId;
    private String productName;
    private Double productValue;
    private Long sales;
    private Double total;
}
