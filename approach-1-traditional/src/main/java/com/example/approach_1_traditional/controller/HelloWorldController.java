package com.example.approach_1_traditional.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class HelloWorldController {

    private final AtomicInteger counter = new AtomicInteger(0);

    private final RestTemplate restTemplate;

    public HelloWorldController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/hello-world")
    public ResponseEntity<String> helloWorld() {
        System.out.printf("%s: %s: REQUEST#: %d\n", Thread.currentThread(), Instant.now(), counter.addAndGet(1));

        var url = "http://localhost:8081/hello-slow";
        return restTemplate.getForEntity(url, String.class);
    }

}
