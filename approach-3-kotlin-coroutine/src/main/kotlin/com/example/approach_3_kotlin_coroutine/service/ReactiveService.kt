package com.example.approach_3_kotlin_coroutine.service

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.time.Duration

@Service
class ReactiveService {
    fun hello(): Mono<String> {
        println("hello() executed")
        return Mono.delay(Duration.ofSeconds(2))
            .then(Mono.just("Hello"))
    }

    fun world(): Mono<String> {
        println("world() executed")
        return Mono.delay(Duration.ofSeconds(2))
            .then(Mono.just("World"))
    }
}