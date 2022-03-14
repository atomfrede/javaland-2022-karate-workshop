package com.github.atomfrede.javaland.beleidigungsduell.external;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.vavr.Tuple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
public class LosSchimpfosApi {

    private final WebClient webClient;

    public LosSchimpfosApi(WebClient webClient) {
        this.webClient = webClient;
    }

    @CircuitBreaker(name = "losSchimpfosApi", fallbackMethod = "fallback")
    public String getRandom() {
        return webClient.get()
                .exchangeToMono(it -> it.bodyToMono(String.class)).block();
    }


    @CircuitBreaker(name = "losSchimpfosApi", fallbackMethod = "fallback")
    @Retry(name = "losSchimpfosApi")
    public Mono<String> getRandomMono() {
        return webClient.get()
                .exchangeToMono(it -> it.bodyToMono(String.class));
    }
    public Triple<String, String, String> getRandom3x() {

        List<String> strings = Flux.merge(getRandomMono(),
                        getRandomMono(),
                        getRandomMono())
                .collectList().block();

        return Triple.of(strings.get(0), strings.get(1), strings.get(2));
    }

    public Pair<String, String> getRandomTuple() {

        List<String> strings = Flux.merge(getRandomMono(),
                        getRandomMono())
                .collectList().block();

        return Pair.of(strings.get(0), strings.get(1));
    }

    private String fallback() {

        return "Skistiefelvorwärmer";
    }


}
