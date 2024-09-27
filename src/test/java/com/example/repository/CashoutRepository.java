package com.example.repository;

import com.example.model.Cashout;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CashoutRepository extends ReactiveMongoRepository<Cashout, String> {

        Flux<Cashout> findByuserId(String userId);
}

