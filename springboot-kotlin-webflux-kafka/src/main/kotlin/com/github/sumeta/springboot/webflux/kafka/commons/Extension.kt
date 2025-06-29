package com.github.sumeta.springboot.webflux.kafka.commons

import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.http.MediaType
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.support.MessageBuilder
import java.util.*
import kotlin.reflect.full.companionObject

interface Logging

fun <T : Any> getClassForLogging(javaClass: Class<T>): Class<*> {
    return javaClass.enclosingClass?.takeIf {
        it.kotlin.companionObject?.java == javaClass
    } ?: javaClass
}

inline fun <reified T : Logging> T.logger(): Logger = getLogger(getClassForLogging(T::class.java))

interface MessagePayLoad

fun <T : MessagePayLoad> T.toMessagePayload() = this.let {
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