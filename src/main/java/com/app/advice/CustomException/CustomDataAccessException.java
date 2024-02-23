package com.app.advice.CustomException;

public class CustomDataAccessException extends RuntimeException {
    public CustomDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
