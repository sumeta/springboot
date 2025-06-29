package com.github.sumeta.springboot.webflux.webclient.utils

import com.github.sumeta.springboot.webflux.webclient.config.WebClientConfig
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.toEntity
import java.util.concurrent.TimeoutException

private typealias ReactiveTimeOutException = TimeoutException
private typealias NettyTimeOutException = io.netty.handler.timeout.TimeoutException

suspend inline fun <reified ResponseBody : Any> WebClient.callGetClient(
        endpoint: String,
        httpHeaders: HttpHeaders = HttpHeaders.EMPTY,
): ResponseEntity<ResponseBody> =
    try{
        get()
            .uri(endpoint)
            .headers { it.addAll(httpHeaders) }
            .retrieve()
            .toEntity<ResponseBody>().awaitSingle()
            .also {
                WebClientConfig.log.info("Client Response -> $endpoint -> body: ${it.body}")
            }
    }catch (e: Exception){
        when (e) {
        is NettyTimeOutException -> throw ClientException(e.message.orEmpty())
        is ReactiveTimeOutException -> throw ClientException(e.message.orEmpty())
        else -> throw e
    }
}

suspend inline fun <reified RequestBody : Any, reified ResponseBody : Any> WebClient.callPostClient(
    endpoint: String,
    body: RequestBody,
    httpHeaders: HttpHeaders = HttpHeaders.EMPTY,
): ResponseEntity<ResponseBody>  =
    try{
        post()
        .uri(endpoint)
        .headers { it.addAll(httpHeaders) }
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(body)
        .also {
            WebClientConfig.log.info("Client Request -> $endpoint -> body: $body")
        }
        .retrieve()
        .toEntity<ResponseBody>().awaitSingle()
        .also {
            WebClientConfig.log.info("Client Response -> $endpoint -> body: $body")
        }
    }catch (e: Exception){
        when (e) {
            is NettyTimeOutException -> throw ClientException(e.message.orEmpty())
            is ReactiveTimeOutException -> throw ClientException(e.message.orEmpty())
            else -> throw e
        }
    }



