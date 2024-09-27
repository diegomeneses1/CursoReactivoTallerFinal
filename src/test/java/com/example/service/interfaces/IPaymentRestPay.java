package com.example.service.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

public interface IPaymentRestPay {
    @GetExchange("/validations/payment/{userId}")
    Mono<String> validarPago(@PathVariable("userId") String clientId);
}
