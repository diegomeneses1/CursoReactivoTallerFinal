package com.example.controller.handler;


import com.example.exception.BalanceNotEnoughException;
import com.example.exception.NotFoundException;
import com.example.exception.NotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalHandleError {

    @ExceptionHandler(NotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ResponseEntity<String>> handleError400Exception(NotValidException exception) {
        return Mono.just(ResponseEntity.badRequest().body(exception.getMessage()));
    }

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<ResponseEntity<String>> handleNoFoundException(NotFoundException exception) {
        return Mono.just(ResponseEntity.notFound().build());
    }

    @ExceptionHandler(BalanceNotEnoughException.class)
    @ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
    public Mono<ResponseEntity<String>> handleError402Exception(BalanceNotEnoughException exception) {
        return Mono.just(ResponseEntity.badRequest().body(exception.getMessage()));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<String>> handleValidationExceptions(WebExchangeBindException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return Mono.just(ResponseEntity.badRequest().body(errorMessage));
    }
    /*
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ResponseEntity<String>> handleValidationExceptions(RuntimeException ex) {
        return Mono.just(ResponseEntity.status((HttpStatus.BAD_REQUEST)).body(ex.getMessage()));
    }*/

}
