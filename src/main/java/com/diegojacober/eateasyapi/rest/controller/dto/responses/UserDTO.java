package com.diegojacober.eateasyapi.rest.controller.dto.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {
    private String email;
    private List<String> roles;
}
