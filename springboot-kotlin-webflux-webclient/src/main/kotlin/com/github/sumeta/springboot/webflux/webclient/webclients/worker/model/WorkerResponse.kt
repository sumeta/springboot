package com.github.sumeta.springboot.webflux.webclient.webclients.worker.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class WorkerResponse(
    val ip: String,
    val status:String
)

