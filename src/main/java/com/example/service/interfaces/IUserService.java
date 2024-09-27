package com.example.service.interfaces;

import com.example.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService {
    Flux<User> findAll();

    Mono<User> findById(String id);

    Mono<User> save(User user);

    Mono<Void> deleteById(String id);
}
