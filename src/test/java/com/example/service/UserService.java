package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.interfaces.IUserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Flux<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public Mono<User> findById(String id) {
        return userRepository.findById(id);
    }


    @Override
    public Mono<User> save(User user) {
        return userRepository.save(user);
    }


    @Override
    public Mono<Void> deleteById(String id) {
        return userRepository.deleteById(id);
    }
}

