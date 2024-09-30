package com.example.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String userNoEncotrado) {
        super(userNoEncotrado);
    }
}
