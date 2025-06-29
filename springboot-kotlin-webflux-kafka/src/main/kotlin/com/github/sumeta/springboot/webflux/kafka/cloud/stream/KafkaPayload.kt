package com.github.sumeta.springboot.webflux.kafka.cloud.stream

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
data class KafkaPayload (
    val message : String,
    val timestamp: LocalDate
    )