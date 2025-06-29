package com.github.sumeta.springboot.webflux.kafka.exception

data class KafkaRetryAbleException(var statusDesc: String) : RuntimeException()
