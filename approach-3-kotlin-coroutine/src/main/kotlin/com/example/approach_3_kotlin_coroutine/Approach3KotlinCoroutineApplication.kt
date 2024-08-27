package com.example.approach_3_kotlin_coroutine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider

@SpringBootApplication
class Approach3KotlinCoroutineApplication {
    @Bean
    fun webClient(): WebClient {
        val provider = ConnectionProvider.builder("myConnectionPool")
            .maxConnections(10000)
            .pendingAcquireMaxCount(10000)
            .build()

        val httpClient = HttpClient.create(provider)

        return WebClient.builder()
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .build()
    }
}

fun main(args: Array<String>) {
    runApplication<Approach3KotlinCoroutineApplication>(*args)
}
