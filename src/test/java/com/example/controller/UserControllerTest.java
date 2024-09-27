package com.example.controller;

import com.example.controller.handler.GlobalHandleError;
import com.example.exception.UserNoFoundException;
import com.example.model.User;
import com.example.service.interfaces.IUserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

@ContextConfiguration(classes = {UserController.class, GlobalHandleError.class})
@WebFluxTest(UserController.class)
class UserControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    IUserService userService;

    @Test
    void crear() {
        var userInput = new User();
        userInput.setName("Test Doe");
        userInput.setBalance(50.0);

        var userOutput = new User();
        userOutput.setId("123");
        userOutput.setName("Test Doe");
        userOutput.setBalance(50.0);

        Mockito.when(userService.save(any(User.class))).thenReturn(Mono.just(userOutput));

        webTestClient
                .post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userInput)
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .returnResult()
                .getResponseBody();

    }


    @Test
    void obtenerPorId() {
        var userOutput = new User();
        userOutput.setId("123");
        userOutput.setName("Test Doe");
        userOutput.setBalance(50.0);

        Mockito.when(userService.findById("123")).thenReturn(Mono.just(userOutput));

        webTestClient.get()
                .uri("/users/{id}", 1L)
                .exchange()
                .expectStatus().isOk()
                .expectBody();
                //.jsonPath("$.name").isEqualTo("Test Doe")
                //.jsonPath("$.balance").isEqualTo(50.0);
    }

    @Test
    void obtenerPorId_sadPath() {

        Mockito.when(userService.findById("123")).thenReturn(Mono.error(new UserNoFoundException("Usuario no encotrado")));

        webTestClient.get()
                .uri("/users/{id}", "123")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void obtenerTodos() {

        var userOutput = new User();
        userOutput.setId("123");
        userOutput.setName("Test Doe");
        userOutput.setBalance(50.0);
        Mockito.when(userService.findAll()).thenReturn(Flux.just(userOutput));


        webTestClient.get()
                .uri("/users")
                .exchange()
                .expectStatus().isOk()
                .expectBody();
                //.jsonPath("$[0].nombre").isEqualTo("Raul")
                //.jsonPath("$[0].email").isEqualTo("raul@gmail.com");
    }


}