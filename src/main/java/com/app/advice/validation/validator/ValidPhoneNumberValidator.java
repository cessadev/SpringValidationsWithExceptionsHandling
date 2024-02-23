package com.app.advice.validation.validator;

import com.app.advice.validation.anotation.ValidPhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, Long> {
    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        // Convertir el valor Long a String
        String valueAsString = (value != null) ? String.valueOf(value) : null;

        // Validación de nulo o cadena vacía
        if (valueAsString == null || valueAsString.isBlank()) {
            return false;
        }

        // Validación de longitud
        if (valueAsString.length() < 8) {
            return false;
        }

        return true;
    }
}
