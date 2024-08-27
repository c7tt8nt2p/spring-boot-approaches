package com.example.approach_3_kotlin_coroutine.controller

import com.example.approach_3_kotlin_coroutine.service.ReactiveService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class ReactiveController(private val service: ReactiveService) {

    @GetMapping("/hello-world-reactive")
    fun helloWorldReactive(): Mono<String> {
        val hello: Mono<String> = service.hello()
        val world: Mono<String> = service.world()
        return hello
            .zipWith(world)
            .map { "${it.t1} ${it.t2}" }
    }

    @GetMapping("/hello-world-coroutine")
    suspend fun helloWorldCoroutine(): String = coroutineScope {
        val hello: Deferred<String> = async { service.hello().awaitSingle() }
        val world: Deferred<String> = async { service.world().awaitSingle() }
        val results = awaitAll(hello, world)
        return@coroutineScope "${results[0]} ${results[1]}"
    }
}
