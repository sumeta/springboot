package com.github.sumeta.springboot.webflux.webclient.config

import com.github.sumeta.springboot.webflux.webclient.exceptions.LoggerDelegate
import io.netty.channel.ChannelOption
import io.netty.handler.ssl.SslContextBuilder
import io.netty.handler.ssl.util.InsecureTrustManagerFactory
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider
import java.time.Duration
import java.util.concurrent.TimeUnit

    @Configuration
    class WebClientConfig {
        companion object {
            val log by LoggerDelegate()
            private const val TIME_OUT = 10
            private const val ONE_SECOND = 1000
        }

        @Bean
        fun webClient() = WebClient.builder()
            .clientConnector(
                ReactorClientHttpConnector(
                    HttpClient
                        .create(connectionProvider)
                        .secure {
                            it.sslContext(insecure)
                        }
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIME_OUT * ONE_SECOND)
                        .option(ChannelOption.TCP_NODELAY,true)
                        .doOnConnected { connection ->
                            val wsTimeOut = TIME_OUT.toLong()
                            connection.addHandlerLast(ReadTimeoutHandler(wsTimeOut, TimeUnit.SECONDS))
                            connection.addHandlerLast(WriteTimeoutHandler(wsTimeOut, TimeUnit.SECONDS))
                        }
                )
            )
            .filters {
                it.add(logRequest())
                it.add(logResponse())
            }
            .build()

        private fun logRequest(): ExchangeFilterFunction {
            return ExchangeFilterFunction.ofRequestProcessor { request: ClientRequest ->
                Mono.just(request)
            }
        }

        private fun logResponse(): ExchangeFilterFunction {
            return ExchangeFilterFunction.ofResponseProcessor { response: ClientResponse ->
                Mono.just(response)
            }
        }

        private val insecure =
            SslContextBuilder
                .forClient()
                .trustManager(
                    InsecureTrustManagerFactory.INSTANCE
                ).build()

        private val connectionProvider = ConnectionProvider.builder("fixed")
            .maxConnections(500)
            .maxIdleTime(Duration.ofSeconds(20))
            .maxLifeTime(Duration.ofSeconds(60))
            .pendingAcquireTimeout(Duration.ofSeconds(60))
            .evictInBackground(Duration.ofSeconds(120)).build()
    }