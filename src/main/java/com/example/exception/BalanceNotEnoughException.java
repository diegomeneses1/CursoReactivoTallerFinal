package com.example.exception;

public class BalanceNotEnoughException extends RuntimeException{
    public BalanceNotEnoughException(String resultadoNoValido) {
        super(resultadoNoValido);
    }
}
