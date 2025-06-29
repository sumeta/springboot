package com.github.sumeta.springboot.webflux.webclient.webclients.worker

import com.github.sumeta.springboot.webflux.webclient.properties.WebClientConfigProperty
import com.github.sumeta.springboot.webflux.webclient.utils.callPostClient
import com.github.sumeta.springboot.webflux.webclient.webclients.worker.model.WorkerRequest
import com.github.sumeta.springboot.webflux.webclient.webclients.worker.model.WorkerResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class WorkerClient(
    private val webClient: WebClient,
    private val webClientProperty: WebClientConfigProperty
) {

    suspend fun updateIp(req: WorkerRequest): ResponseEntity<WorkerResponse> {
        return webClient.callPostClient(webClientProperty.worker.url, req)
    }
}