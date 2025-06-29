package com.github.sumeta.springboot.webflux.webclient.features.host

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class HostRouter {

    @Bean
    fun hostRoutes(
            hostHandler: HostHandler
    ) = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            "host".nest {
                GET("/get", hostHandler::get)
                POST("/update", hostHandler::update)
            }
        }
    }
}