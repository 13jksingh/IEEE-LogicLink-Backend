package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class UserRegistrationException extends RuntimeException {

    private final HttpStatus status;

    public UserRegistrationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}