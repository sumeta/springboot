package com.github.sumeta.springboot.webflux.kafka.stream

import com.github.sumeta.springboot.webflux.kafka.cloud.stream.KafkaPayload
import com.github.sumeta.springboot.webflux.kafka.cloud.stream.MessageStreamChannels
import com.github.sumeta.springboot.webflux.kafka.commons.toMessagePayload
import org.springframework.stereotype.Service

@Service
class StreamChanelService(
    private val messageStreamChannels: MessageStreamChannels
) {

    suspend fun sendMessageProduct(payload: KafkaPayload) {
        messageStreamChannels.produceLogging().send(payload.toMessagePayload())
    }
}