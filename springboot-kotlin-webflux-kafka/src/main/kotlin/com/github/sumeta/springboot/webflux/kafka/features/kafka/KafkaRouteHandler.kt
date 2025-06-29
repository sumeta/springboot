package com.github.sumeta.springboot.webflux.kafka.features.kafka

import com.github.sumeta.springboot.webflux.kafka.cloud.stream.KafkaPayload
import com.github.sumeta.springboot.webflux.kafka.stream.StreamChanelService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import java.time.LocalDate

@Component
class KafkaRouteHandler(
    private val streamChanelService: StreamChanelService
) {
    suspend fun sendMessage(serverRequest: ServerRequest) =
        ServerResponse.ok().json().bodyValueAndAwait(
            serverRequest.awaitBody<KafkaMessageRequest>().let {
                streamChanelService.sendMessageProduct(
                    KafkaPayload(
                    message = it.message,
                    timestamp = LocalDate.now()
                )
                )
            }
        )
}
