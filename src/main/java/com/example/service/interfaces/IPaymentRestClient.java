package com.example.service.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

public interface IPaymentRestClient {
    @GetExchange("/validations/payment/{userId}")
    Mono<String> validatePayment(@PathVariable("userId") String clientId);
}
