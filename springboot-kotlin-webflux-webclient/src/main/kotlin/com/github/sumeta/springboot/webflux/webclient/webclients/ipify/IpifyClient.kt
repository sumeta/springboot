package com.github.sumeta.springboot.webflux.webclient.webclients.ipify

import com.github.sumeta.springboot.webflux.webclient.properties.WebClientConfigProperty
import com.github.sumeta.springboot.webflux.webclient.utils.callGetClient
import com.github.sumeta.springboot.webflux.webclient.webclients.ipify.model.GetIpResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class IpifyClient(
    private val webClient: WebClient,
    private val webClientProperty: WebClientConfigProperty
) {

    suspend fun getIp(): ResponseEntity<GetIpResponse> {
        return webClient.callGetClient(webClientProperty.ipify.url)
    }
}