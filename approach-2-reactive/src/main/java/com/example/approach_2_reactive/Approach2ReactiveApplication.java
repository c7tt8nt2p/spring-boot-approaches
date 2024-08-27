package com.example.approach_2_reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@SpringBootApplication
public class Approach2ReactiveApplication {

	public static void main(String[] args) {
//		Flux<Long> publisher = Flux.interval(Duration.ofMillis(1))
//				.onBackpressureDrop()
//				.log()
//				.concatMap(x -> Mono.delay(Duration.ofMillis(100)));
//
//		publisher.blockLast();
		SpringApplication.run(Approach2ReactiveApplication.class, args);
	}

	@Bean
	public WebClient webClient() {
		var provider = ConnectionProvider.builder("myConnectionPool")
				.maxConnections(10000)
				.pendingAcquireMaxCount(10000)
				.build();

		var httpClient = HttpClient.create(provider);

		return WebClient.builder()
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.build();
	}

}
