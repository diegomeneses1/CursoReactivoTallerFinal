package com.example.config;

import com.example.exception.Error400Exception;
import com.example.service.interfaces.IPaymentRestPay;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient createWebClient(WebClient.Builder builder){
        return builder.baseUrl("http://localhost:8080")
                .defaultStatusHandler(HttpStatusCode::is4xxClientError, clientResponse -> {
                    return clientResponse.bodyToMono(String.class)
                            .flatMap(error -> Mono.error(new Error400Exception(error)));
                })
                .defaultStatusHandler(HttpStatusCode::is5xxServerError, clientResponse -> {
                    return clientResponse.bodyToMono(String.class)
                            .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)))
                            .flatMap(error -> Mono.error(new RuntimeException(error)));
                })
                .build();
    }

    @Bean
    public IPaymentRestPay createIPaymentRestPay(WebClient webClient){
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                    .builderFor(WebClientAdapter.create(webClient))
                .build();
        return factory.createClient(IPaymentRestPay.class);
    }


}
