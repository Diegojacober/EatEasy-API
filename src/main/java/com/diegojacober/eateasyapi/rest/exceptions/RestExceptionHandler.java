package com.diegojacober.eateasyapi.rest.exceptions;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.diegojacober.eateasyapi.rest.controller.dto.errors.ErrorDTO;
import com.diegojacober.eateasyapi.rest.controller.dto.errors.ErrorObject;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodNotValidException(MethodArgumentNotValidException ex) {

        List<ErrorObject> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ErrorObject(fieldError.getDefaultMessage(), fieldError.getField()))
                .collect(Collectors.toList());

        ErrorDTO apiErrorMessage = new ErrorDTO("INVALID FIELDS", 400, "BAD REQUEST", errors);

        System.out.println(apiErrorMessage);

        return ResponseEntity.badRequest().body(apiErrorMessage);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,
            WebRequest request) {
        String errorMessage = "Invalid permission value";

        List<ErrorObject> errors = Arrays.asList(new ErrorObject(errorMessage, "role"));

        ErrorDTO apiErrorMessage = new ErrorDTO("INVALID FIELDS", 400, "BAD REQUEST", errors);

        return ResponseEntity.badRequest().body(apiErrorMessage);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<Map<String, String>> userExistsException(UserExistsException ex) {
        HashMap<String, String> message = new HashMap<String, String>();
        message.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(BussinessException.class)
    public ResponseEntity<Map<String, String>> bussinessException(BussinessException ex) {
        HashMap<String, String> message = new HashMap<String, String>();
        message.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(message);
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(ExpiredJwtException.class)
    public @ResponseBody ErrorDTO handleExpiredTokenException(HttpServletRequest request,
            Exception ex){
        List<ErrorObject> errors = Arrays.asList(new ErrorObject("Token expired", "token"));
        ErrorDTO response = new ErrorDTO(ex.getMessage(), 400, "FORBIDDEN", errors); 
        return response;
    }
}