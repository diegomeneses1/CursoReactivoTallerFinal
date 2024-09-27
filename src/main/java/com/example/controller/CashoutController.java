package com.example.controller;

import com.example.model.Cashout;
import com.example.model.User;
import com.example.service.interfaces.ICashoutService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController

@RequestMapping("/cashout")
public class CashoutController {
    private final ICashoutService cashoutService;

    public CashoutController(ICashoutService cashoutService) {
        this.cashoutService = cashoutService;
    }

    @GetMapping
    public Flux<Cashout> getAllCashout() {
        return cashoutService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Cashout> getCashoutById(@PathVariable String id) {
        return cashoutService.findById(id);
    }

    @GetMapping("/user/{userId}")
    public Flux<Cashout> getCashoutByuserId(@PathVariable String userId) {
        return cashoutService.findByUserid(userId);
    }


    @PostMapping
    public Mono<Cashout> createCashout(@RequestBody Cashout cashout) {
        return cashoutService.save(cashout);
    }

    /*
    @PutMapping("/{id}/balance")
    public Mono<Cashout> updateProducto(@PathVariable String id, @RequestBody Cashout cashout) {
        return cashoutService.findById(id)
                .flatMap(existingCashout -> {
                    //existingProducto.setName(user.getName());
                    existingCashout.setAmount(cashout.getAmount());
                    return cashoutService.save(existingCashout);
                });
    }

     */

}
