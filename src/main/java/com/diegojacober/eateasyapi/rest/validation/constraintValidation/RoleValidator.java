package com.diegojacober.eateasyapi.rest.validation.constraintValidation;

import com.diegojacober.eateasyapi.domain.entity.enums.Role;
import com.diegojacober.eateasyapi.rest.validation.ValidRole;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleValidator implements ConstraintValidator<ValidRole, Role> {

    @Override
    public void initialize(ValidRole constraintAnnotation) {
    }

    @Override
    public boolean isValid(Role value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; 
        }

        for (Role role : Role.values()) {
            if (role.equals(value)) {
                return true; // Valor válido
            }
        }

        return false;
    }
}
