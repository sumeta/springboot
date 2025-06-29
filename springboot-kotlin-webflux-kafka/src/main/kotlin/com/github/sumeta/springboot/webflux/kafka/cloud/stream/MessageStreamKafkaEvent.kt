package com.github.sumeta.springboot.webflux.kafka.cloud.stream

import com.github.sumeta.springboot.webflux.kafka.cloud.stream.dto.LoggingMessageStream
import com.github.sumeta.springboot.webflux.kafka.commons.Logging
import com.github.sumeta.springboot.webflux.kafka.commons.logger
import com.github.sumeta.springboot.webflux.kafka.commons.toMessagePayload
import org.springframework.stereotype.Component

@Component
class MessageStreamKafkaService(
    private val messageStreamChannels: MessageStreamChannels
) {
    companion object : Logging {
        val log = logger()
    }

    fun produceLogging(payload: LoggingMessageStream) =
        messageStreamChannels
            .produceLogging()
            .send(payload.toMessagePayload())
            .also {
                println("Produce: Logging -> payload")
                log.info("Produce: Logging -> payload: $payload")
            }
}