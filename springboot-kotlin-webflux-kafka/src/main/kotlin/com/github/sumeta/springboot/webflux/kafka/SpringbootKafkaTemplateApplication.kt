package com.github.sumeta.springboot.webflux.kafka

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringbootKafkaTemplateApplication

fun main(args: Array<String>) {
	runApplication<SpringbootKafkaTemplateApplication>(*args)
}
