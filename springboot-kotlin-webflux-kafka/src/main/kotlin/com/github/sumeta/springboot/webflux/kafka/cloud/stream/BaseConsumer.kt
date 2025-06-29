package com.github.sumeta.springboot.webflux.kafka.cloud.stream

import com.github.sumeta.springboot.webflux.kafka.commons.Logging
import com.github.sumeta.springboot.webflux.kafka.commons.logger
import com.github.sumeta.springboot.webflux.kafka.exception.KafkaRetryAbleException
import kotlinx.coroutines.runBlocking

open class BaseConsumer{
    private companion object : Logging {
        val log = logger()
    }

    fun <T> processConsumer(
        topicNameBinder: String,
        payload: T,
        referenceProcessId: String,
        process: suspend (T) -> Unit
    ) {
        log.info("$topicNameBinder : $payload RefProcessId: $referenceProcessId")
        try {
            runBlocking {
                process(payload)

                log.info("RefProcessId: $referenceProcessId Process Successfully.")
            }
        } catch (ex: KafkaRetryAbleException) {
            log.error("KafkaRetryAbleException from $topicNameBinder caused: ${ex.statusDesc} RefProcessId: $referenceProcessId")
            throw ex
        } catch (ex: Exception) {
            log.error("Exception from $topicNameBinder caused: ${ex.message} RefProcessId: $referenceProcessId}")
        }
    }
}