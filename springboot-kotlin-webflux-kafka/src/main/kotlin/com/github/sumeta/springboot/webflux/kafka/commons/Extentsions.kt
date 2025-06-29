package com.github.sumeta.springboot.webflux.kafka.commons

import org.springframework.http.MediaType
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.support.MessageBuilder
import java.util.*

fun <T : Any> T.toMessagePayload() = this.let {
    MessageBuilder
        .createMessage(
            it,
            MessageHeaders(
                Collections.singletonMap<String, Any>(
                    MessageHeaders.CONTENT_TYPE,
                    MediaType.APPLICATION_JSON_VALUE
                )
            )
        )
}
