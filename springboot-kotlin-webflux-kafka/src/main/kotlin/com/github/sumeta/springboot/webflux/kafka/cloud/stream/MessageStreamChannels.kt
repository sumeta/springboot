package com.github.sumeta.springboot.webflux.kafka.cloud.stream


import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.SubscribableChannel

interface MessageStreamChannels {

    @Input("LoggingConsumer")
    fun consumeLogging(): SubscribableChannel

    @Output("LoggingProducer")
    fun produceLogging(): SubscribableChannel
}