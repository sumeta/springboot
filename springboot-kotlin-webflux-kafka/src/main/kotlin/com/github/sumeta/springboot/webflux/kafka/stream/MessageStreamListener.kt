package com.github.sumeta.springboot.webflux.kafka.stream

import com.github.sumeta.springboot.webflux.kafka.cloud.stream.BaseConsumer
import com.github.sumeta.springboot.webflux.kafka.cloud.stream.dto.LoggingMessageStream
import com.github.sumeta.springboot.webflux.kafka.commons.Logging
import com.github.sumeta.springboot.webflux.kafka.commons.logger

import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class MessageStreamListener()
     : BaseConsumer() {

    private companion object : Logging {
        const val destination = "LoggingConsumer"
        val log = logger()
    }

    @StreamListener(destination)
    fun consumeFaceResult(@Payload payload: LoggingMessageStream) {
        println("consumeFaceResult()")
        processConsumer(
            topicNameBinder = destination,
            payload = payload,
            referenceProcessId = System.currentTimeMillis().toString()
        ) {

            log.info("Consume > Message: ${payload.message}, Timestamp: ${payload.timestamp}")

        }
    }


}