package com.example.controller;

import com.example.controller.handler.GlobalHandleError;
import com.example.exception.UserNoFoundException;
import com.example.model.Cashout;
import com.example.service.interfaces.ICashoutService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@ContextConfiguration(classes = {CashoutControllerTest.class, GlobalHandleError.class})
@WebFluxTest(CashoutControllerTest.class)
class CashoutControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    ICashoutService cashoutService;

    @Test
    void crear() {
        /*
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
                .getResponseBody();*/

    }


    @Test
    void obtenerPorCashoutId() {
        var cashoutOutput = new Cashout();
        cashoutOutput.setId("123");
        cashoutOutput.setUserId("1");
        cashoutOutput.setAmount(50.0);

        Mockito.when(cashoutService.findById("")).thenReturn(Mono.just(cashoutOutput));

        webTestClient.get()
                .uri("/cashout/{id}", "")
                .exchange()
                .expectStatus().isOk()
                .expectBody();
                //.jsonPath("$.name").isEqualTo("Test Doe")
                //.jsonPath("$.balance").isEqualTo(50.0);
    }

    @Test
    void obtenerPorId_sadPath() {

        Mockito.when(cashoutService.findById("123")).thenReturn(Mono.error(new UserNoFoundException("CashOut no encotrado")));

        webTestClient.get()
                .uri("/cashout/{id}", "123")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void obtenerTodos() {

        var cashoutOutput = new Cashout();
        cashoutOutput.setId("123");
        cashoutOutput.setUserId("1");
        cashoutOutput.setAmount(50.0);
        Mockito.when(cashoutService.findAll()).thenReturn(Flux.just(cashoutOutput));


        webTestClient.get()
                .uri("/users")
                .exchange()
                .expectStatus().isOk()
                .expectBody();
                //.jsonPath("$[0].nombre").isEqualTo("Raul")
                //.jsonPath("$[0].email").isEqualTo("raul@gmail.com");
    }


}