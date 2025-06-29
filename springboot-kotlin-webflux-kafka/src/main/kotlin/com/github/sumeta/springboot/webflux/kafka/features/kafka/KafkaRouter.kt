package com.github.sumeta.springboot.webflux.kafka.features.kafka

import com.github.sumeta.springboot.webflux.kafka.features.kafka.KafkaRouteHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class KafkaRouter {
    @Bean
    fun kafkaMessageQueue(
        kafkaHandler: KafkaRouteHandler
    ) = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            "kafka".nest {
                POST("/send",kafkaHandler::sendMessage)
            }
        }
    }


}