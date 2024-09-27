package com.example.exception;

public class UserNoValidException extends RuntimeException{
    public UserNoValidException(String resultadoNoValido) {
        super(resultadoNoValido);
    }
}
