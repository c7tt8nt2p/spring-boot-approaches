package com.example.slow_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class SlowController {

    @GetMapping("/hello-slow")
    public String helloWorld() throws InterruptedException {
        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        return "Hello Slow!";
    }

}
