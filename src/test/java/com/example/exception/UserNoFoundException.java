package com.example.exception;

public class UserNoFoundException extends RuntimeException {
    public UserNoFoundException(String userNoEncotrado) {
        super(userNoEncotrado);
    }
}
