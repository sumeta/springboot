package com.github.sumeta.springboot.webflux.webclient.webclients.worker

import com.github.sumeta.springboot.webflux.webclient.utils.ClientException
import com.github.sumeta.springboot.webflux.webclient.utils.ErrorCode.CLIENT_ERROR
import com.github.sumeta.springboot.webflux.webclient.webclients.ClientError
import com.github.sumeta.springboot.webflux.webclient.webclients.ClientResult
import com.github.sumeta.springboot.webflux.webclient.webclients.worker.model.WorkerRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class WorkerProvider(
    private val workerClient: WorkerClient
) {

    suspend fun updateIp(req: WorkerRequest) = workerClient.updateIp(req).let {
        when (it.statusCode) {
            HttpStatus.OK -> ClientResult.Success(it.body ?: throw ClientException("Can't update ip"))
            else -> ClientResult.Error(
                ClientError(CLIENT_ERROR, "Can't update ip")
            )
        }
    }
}