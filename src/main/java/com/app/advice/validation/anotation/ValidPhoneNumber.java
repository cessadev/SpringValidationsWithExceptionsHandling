package com.app.advice.validation.anotation;

import com.app.advice.validation.validator.ValidPhoneNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidPhoneNumberValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface ValidPhoneNumber {
    String message() default "{custom.validation.numberphone.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
