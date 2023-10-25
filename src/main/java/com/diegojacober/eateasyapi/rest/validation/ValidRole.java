package com.diegojacober.eateasyapi.rest.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.diegojacober.eateasyapi.rest.validation.constraintValidation.RoleValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RoleValidator.class)
public @interface ValidRole {
    String message() default "Valor inválido para o papel (role)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
