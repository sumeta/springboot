package com.github.sumeta.springboot.webflux.webclient.webclients

sealed class ClientResult<out T> {
    data class Success<T>(val response: T) : ClientResult<T>()
    data class Error(val exception: ClientError) : ClientResult<Nothing>()
}

data class ClientError(
    val errorCode: String,
    val errorDesc: String,
    val reverseFlag: Boolean? = null
)