package com.diegojacober.eateasyapi.rest.controller.dto;

import com.diegojacober.eateasyapi.domain.entity.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegisterRequestDTO {

    @NotBlank(message = "firstname is required.")
    private String firstname;

    @NotBlank(message = "lastname is required.")
    private String lastname;

    @NotBlank(message = "email is required.")
    @Email(message = "O campo deve ser um email válido")
    private String email;

    @NotBlank(message = "password is required.")
    private String password;

    // @ValidRole(message = "Defina as roles do usuário")
    private Role role;
}
