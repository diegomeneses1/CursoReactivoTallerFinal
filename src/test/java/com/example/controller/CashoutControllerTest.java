package com.example.controller;

import com.example.controller.handler.GlobalHandleError;
import com.example.exception.NotFoundException;
import com.example.model.Cashout;
import com.example.model.User;
import com.example.service.CashoutService;
import com.example.service.UserService;
import com.example.service.interfaces.IPaymentRestClient;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ContextConfiguration(classes = {CashoutController.class, GlobalHandleError.class})
@WebFluxTest(CashoutControllerTest.class)

class CashoutControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    CashoutService cashoutService;

    @MockBean
    UserService userService;

    @MockBean
    IPaymentRestClient restClient;

    @Test
    void createCashout_happy_path() {
        User user = new User();
        user.setId("1");
        user.setName("Albert Test");
        user.setBalance(100.0);

        Cashout cashout = new Cashout();
        cashout.setUserId("1");
        cashout.setAmount(50.0);


        Mockito.when(userService.findById(Mockito.anyString())).thenReturn(Mono.just(user));
        Mockito.when(cashoutService.save(ArgumentMatchers.any(Cashout.class))).thenReturn(Mono.just(cashout));
        Mockito.when(restClient.validatePayment(Mockito.anyString())).thenReturn(Mono.just("El pago no puede continuar"));

        webTestClient
                .post()
                .uri("/cashout")
                .bodyValue(cashout)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.userId").isEqualTo("1")
                .jsonPath("$.amount").isEqualTo(50.0);
    }

    @Test
    void createCashout_client_not_found() {
        User user = new User();
        user.setId("1");
        user.setName("Albert Test");
        user.setBalance(100.0);

        Cashout cashout = new Cashout();
        cashout.setUserId("1");
        cashout.setAmount(50.0);

        Mockito.when(userService.findById(Mockito.anyString())).thenReturn(Mono.error(new NotFoundException("Usuario no encotrado")));
        webTestClient
                .post()
                .uri("/cashouts")
                .bodyValue(cashout)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class);

    }
    @Test
    void obtenerPorId() {
        var cashoutOutput = new Cashout();
        cashoutOutput.setId("1");
        cashoutOutput.setUserId("123");
        cashoutOutput.setAmount(50.0);

        Mockito.when(cashoutService.findById(Mockito.anyString())).thenReturn(Mono.just(cashoutOutput));

        webTestClient.get()
                .uri("/cashout/{id}", "123")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.userId").isEqualTo("123")
                .jsonPath("$.amount").isEqualTo(50.0);
    }

    @Test
    void obtenerPorId_sadPath() {
        Mockito.when(cashoutService.findById("123"))
                .thenReturn(Mono.error(new NotFoundException("CashOut no encontrado")));

        webTestClient.get()
                .uri("/cashout/{id}", "123")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void obtenerTodos() {

        var cashoutOutput = new Cashout();
        cashoutOutput.setId("1");
        cashoutOutput.setUserId("123");
        cashoutOutput.setAmount(50.0);
        Mockito.when(cashoutService.findAll()).thenReturn(Flux.just(cashoutOutput));


        webTestClient.get()
                .uri("/cashout")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].userId").isEqualTo("123")
                .jsonPath("$[0].amount").isEqualTo(50.0);
    }


}