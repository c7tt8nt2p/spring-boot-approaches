package com.example.slow_service_reactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class SlowController {

    @GetMapping("/hello-slow")
    public Mono<String> helloWorld() {
        return Mono.delay(Duration.ofSeconds(5))
                .thenReturn("Hello Slow!");
    }

}
