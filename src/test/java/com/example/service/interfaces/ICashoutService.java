package com.example.service.interfaces;

import com.example.model.Cashout;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICashoutService {
    Mono<Cashout> save(Cashout cashout);

    //@Override
    Flux<Cashout> findAll();

    //@Override
    Mono<Cashout> findById(String id);

    Flux<Cashout> findByUserid(String userId);
}
