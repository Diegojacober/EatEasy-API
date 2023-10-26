package com.diegojacober.eateasyapi.rest.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diegojacober.eateasyapi.rest.controller.dto.UserDTO;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping("/me")
    public UserDTO getCurrentUserContext() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        List<String> roles = authentication.getAuthorities().stream().map(r -> r.getAuthority()).toList(); 
        UserDTO dto = UserDTO.builder()
                        .email(authentication.getName())
                        .roles( roles)
                        .build();
        return dto;
    }
}
