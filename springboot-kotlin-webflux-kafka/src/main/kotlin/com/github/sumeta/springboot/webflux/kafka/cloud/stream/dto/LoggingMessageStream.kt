package com.github.sumeta.springboot.webflux.kafka.cloud.stream.dto

import com.github.sumeta.springboot.webflux.kafka.commons.MessagePayLoad

data class LoggingMessageStream(val message: String, val timestamp: String?) : MessagePayLoad