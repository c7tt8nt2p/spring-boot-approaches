package com.example.approach_2_reactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class HelloWorldController {

    private final AtomicInteger counter = new AtomicInteger(0);

    private final WebClient webClient;

    public HelloWorldController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/hello-world")
    public Mono<String> helloWorld() {
        System.out.printf("%s: %s: REQUEST#: %d\n", Thread.currentThread(), Instant.now(), counter.addAndGet(1));

        return webClient.get()
                .uri("http://localhost:9091/hello-slow")
                .retrieve()
                .bodyToMono(String.class);
    }

}
