package com.diegojacober.eateasyapi.rest.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.diegojacober.eateasyapi.rest.validation.constraintValidation.NotEmptyListValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyListValidator.class)
public @interface NotEmptyList {

    String message() default "The list items must be not null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}