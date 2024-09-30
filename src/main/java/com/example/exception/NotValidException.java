package com.example.exception;

public class NotValidException extends RuntimeException{
    public NotValidException(String resultadoNoValido) {
        super(resultadoNoValido);
        System.out.println("NotValidException");
    }
}
