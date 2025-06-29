package com.github.sumeta.springboot.webflux.h2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringbootH2DatabaseTemplateApplication

fun main(args: Array<String>) {
	runApplication<SpringbootH2DatabaseTemplateApplication>(*args)
}
