package com.github.sumeta.springboot.webflux.webclient.features.host

import com.github.sumeta.springboot.webflux.webclient.utils.ClientException
import com.github.sumeta.springboot.webflux.webclient.webclients.ClientResult
import com.github.sumeta.springboot.webflux.webclient.webclients.ipify.IpifyProvider
import com.github.sumeta.springboot.webflux.webclient.webclients.ipify.model.GetIpResponse
import com.github.sumeta.springboot.webflux.webclient.webclients.worker.WorkerProvider
import com.github.sumeta.springboot.webflux.webclient.webclients.worker.model.WorkerRequest
import com.github.sumeta.springboot.webflux.webclient.webclients.worker.model.WorkerResponse
import org.springframework.stereotype.Service

@Service
class HostService(
        private val ipifyProvider: IpifyProvider,
        private val workerProvider: WorkerProvider) {

    suspend fun get() = currentIp()

    suspend fun update() = updateIp(WorkerRequest(currentIp().ip))


    private suspend fun currentIp(): GetIpResponse =
        ipifyProvider.getIp().let {
            when (it) {
                is ClientResult.Success -> it.response
                is ClientResult.Error -> throw ClientException("Can't current get ip")
            }
        }

    private suspend fun updateIp(req: WorkerRequest): WorkerResponse =
        workerProvider.updateIp(req).let {
            when (it) {
                is ClientResult.Success -> it.response
                is ClientResult.Error -> throw ClientException("Can't update ip")
            }
        }
}