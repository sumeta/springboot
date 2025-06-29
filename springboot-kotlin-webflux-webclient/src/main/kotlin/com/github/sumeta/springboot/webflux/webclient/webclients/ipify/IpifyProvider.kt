package com.github.sumeta.springboot.webflux.webclient.webclients.ipify

import com.github.sumeta.springboot.webflux.webclient.utils.ClientException
import com.github.sumeta.springboot.webflux.webclient.utils.ErrorCode.CLIENT_ERROR
import com.github.sumeta.springboot.webflux.webclient.utils.ErrorCode.SYSTEM_ERROR
import com.github.sumeta.springboot.webflux.webclient.webclients.ClientError
import com.github.sumeta.springboot.webflux.webclient.webclients.ClientResult
import com.github.sumeta.springboot.webflux.webclient.webclients.worker.WorkerClient
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class IpifyProvider(
    private val ipifyClient: IpifyClient
) {

    suspend fun getIp() = ipifyClient.getIp().let {
        when (it.statusCode) {
            HttpStatus.OK -> ClientResult.Success(it.body ?: throw ClientException("Can't check ip"))
            else -> ClientResult.Error(
                ClientError(
                        CLIENT_ERROR, "Can't check ip"
                )
            )
        }
    }
}