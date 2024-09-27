package com.example.controller.handler;

import com.example.exception.UserNoFoundException;
import com.example.exception.Error400Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalHandleError {

    @ExceptionHandler({UserNoFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<ResponseEntity<String>> handleNoFoundException(UserNoFoundException exception){
        return Mono.just(ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage()));
    }

    @ExceptionHandler(Error400Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ResponseEntity<String>> handleError400Exception(Error400Exception exception){
        return Mono.just(ResponseEntity
                .badRequest().
                body(exception.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public Mono<ResponseEntity<String>> handleValidationExceptions(RuntimeException ex) {
        return Mono.just(ResponseEntity
                .status((HttpStatus.BAD_GATEWAY))
                .body(ex.getMessage()));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<String>> handleValidationExceptions(WebExchangeBindException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return Mono.just(ResponseEntity
                .badRequest()
                .body(errorMessage));
    }

}
