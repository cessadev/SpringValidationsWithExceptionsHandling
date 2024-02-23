package com.app.advice;

import com.app.advice.CustomException.CustomDataAccessException;
import com.app.advice.CustomException.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(CustomDataAccessException.class)
    public ResponseEntity<?> handlerDataAccessException(CustomDataAccessException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handleGenericException(Exception exception) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error interno");
//    }
}
