package com.example.service.interfaces;

import com.example.exception.Error400Exception;
import com.example.model.Cashout;
import com.example.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.function.Function;

public interface ICashoutService {
    Mono<Cashout> save(Cashout cashout);

    //@Override
    Flux<Cashout> findAll();

    //@Override
    Mono<Cashout> findById(String id);

    Flux<Cashout> findByUserid(String userId);
}
