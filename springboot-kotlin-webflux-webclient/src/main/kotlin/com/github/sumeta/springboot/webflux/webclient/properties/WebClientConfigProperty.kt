package com.github.sumeta.springboot.webflux.webclient.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("ws-client")
class WebClientConfigProperty {
    val ipify = Ipify()
    val worker = Worker()

    class Ipify {
        lateinit var url: String
    }

    class Worker {
        lateinit var url: String
    }


}
