package com.diegojacober.eateasyapi.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diegojacober.eateasyapi.rest.controller.dto.AuthenticationRequestDTO;
import com.diegojacober.eateasyapi.rest.controller.dto.AuthenticationResponseDTO;
import com.diegojacober.eateasyapi.rest.controller.dto.RegisterRequestDTO;
import com.diegojacober.eateasyapi.rest.service.AuthenticationService;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponseDTO> register(
      @RequestBody @Valid RegisterRequestDTO request
  ) {
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponseDTO> authenticate(
      @RequestBody @Valid AuthenticationRequestDTO request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException, StreamWriteException, DatabindException, java.io.IOException {
    service.refreshToken(request, response);
  }


}
