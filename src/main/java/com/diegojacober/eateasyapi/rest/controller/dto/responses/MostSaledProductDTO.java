package com.diegojacober.eateasyapi.rest.controller.dto.responses;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MostSaledProductDTO {
    private Integer productId;
    private String productName;
    private Date orderDate;
    private Integer restaurantId;
    private Integer totalQuantity;
    private Double total;
}
