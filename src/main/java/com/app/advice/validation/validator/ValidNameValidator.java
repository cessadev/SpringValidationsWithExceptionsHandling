package com.app.advice.validation.validator;

import com.app.advice.validation.anotation.ValidName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidNameValidator implements ConstraintValidator<ValidName, String> {
    @Override
    public void initialize(ValidName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        // The value cannot be null or empty
        if (value == null || value.isBlank()) { return false; }

        // Validation of length
        if (!value.matches("^.{2,50}$")) { return false; }

        // Validation of characters
        if (!value.matches("^[a-zA-ZáéíóúñÑ ]+$")) { return false; }

        // Validation of first character in capital letters
        String[] words = value.split("\\s+");
        // Check each word
        for (String word : words) {
            // Validation of first character in capital letters for each word
            if (!word.matches("\\b\\p{Lu}\\p{L}*\\b")) { return false; }
        }

        return true;
    }

}
