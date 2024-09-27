package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/validations")
public class PaymentController {

    @GetMapping("/payment/{clienteId}")
    public Mono<String> validarPago(@PathVariable("clienteId") String clienteId){
        char ultimoNumero =clienteId.charAt(clienteId.length() - 1);
        if (Character.isDigit(ultimoNumero) && (ultimoNumero - '0') % 2 == 0) {
            return Mono.just("El pago puede ser procesado");
        }
        return Mono.just("El pago no puede continuar");
    }
}
