package com.diegojacober.eateasyapi.rest.controller.dto.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorDTO {

    private final String message;
    private final int code;
    private final String status;
    private final List<ErrorObject> errors;
}