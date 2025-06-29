package com.github.sumeta.springboot.webflux.kafka.config

import com.github.sumeta.springboot.webflux.kafka.cloud.stream.MessageStreamChannels
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.context.annotation.Configuration

@Configuration
@EnableBinding(MessageStreamChannels::class)
internal class MessageStreamConfig