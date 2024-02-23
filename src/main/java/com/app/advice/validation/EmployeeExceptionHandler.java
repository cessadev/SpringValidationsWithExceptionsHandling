package com.app.advice.validation;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class EmployeeExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {
        Map<String, String> errors = new HashMap<>();

        Throwable cause = exception.getCause();
        while (cause != null) {
            if (cause instanceof InvalidFormatException) {
                InvalidFormatException invalidFormatException = (InvalidFormatException) cause;
                String fieldName = invalidFormatException.getPath().stream()
                        .map(JsonMappingException.Reference::getFieldName)
                        .findFirst()
                        .orElse("Campo no especificado");
                errors.put(fieldName, "Error en el campo: " + fieldName);
                return ResponseEntity.badRequest().body(errors);
            }
            cause = cause.getCause();
        }

        errors.put("error", "El número de teléfono asignado no es legible");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handlerConstraintViolationException(ConstraintViolationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en el campo 'Email': " + exception.getMessage());
    }

}
