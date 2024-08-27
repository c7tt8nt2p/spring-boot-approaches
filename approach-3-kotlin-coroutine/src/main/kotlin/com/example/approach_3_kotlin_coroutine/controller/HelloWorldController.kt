package com.example.approach_3_kotlin_coroutine.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBodyOrNull
import java.time.Instant
import java.util.concurrent.atomic.AtomicInteger

@RestController
class HelloWorldController(private val webClient: WebClient) {
    private val counter = AtomicInteger(0)

    @GetMapping("/hello-world")
    suspend fun helloWorld(): String? {
        println("${Thread.currentThread()}: ${Instant.now()}: REQUEST#: ${counter.addAndGet(1)}")
        return webClient.get()
            .uri("http://localhost:9091/hello-slow")
            .retrieve()
            .awaitBodyOrNull()
    }
}
