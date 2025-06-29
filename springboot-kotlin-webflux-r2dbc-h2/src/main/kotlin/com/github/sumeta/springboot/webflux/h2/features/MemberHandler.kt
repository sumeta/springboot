package com.github.sumeta.springboot.webflux.h2.features

import com.github.sumeta.springboot.webflux.h2.features.dto.MemberRequest
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import java.time.LocalDateTime

@Component
class MemberHandler(private val memberRepository: MemberRepository) {

    suspend fun getAll(serverRequest: ServerRequest):ServerResponse{
        return ServerResponse.ok().bodyAndAwait(memberRepository.getAll())
    }

    suspend fun get(serverRequest: ServerRequest):ServerResponse{
        memberRepository.get(serverRequest.pathVariable("id"))?.let {
            return ServerResponse.ok().bodyValueAndAwait(it)
        }
        return ServerResponse.notFound().buildAndAwait()
    }

    suspend fun add(serverRequest: ServerRequest) =
        ServerResponse.ok().json().bodyValueAndAwait(
            serverRequest.awaitBody<MemberRequest>().let {
                kotlin.runCatching {
                    memberRepository.add(
                        MemberEntity(
                            id= it.id,
                            firstName = it.firstName,
                            lastName = it.lastName,
                            createdBy = "Top",
                            createdDate = LocalDateTime.now()
                        )
                    )
                }.onSuccess {
                    return ServerResponse.ok().bodyValueAndAwait("Success")
                }.onFailure {
                    return ServerResponse.badRequest().buildAndAwait()
                }
            }
        )

    suspend fun edit(serverRequest: ServerRequest) =
            ServerResponse.ok().json().bodyValueAndAwait(
                    serverRequest.awaitBody<MemberRequest>().let {
                        kotlin.runCatching {
                            memberRepository.edit(
                                    MemberEntity(
                                            id= it.id,
                                            firstName = it.firstName,
                                            lastName = it.lastName,
                                            createdBy = "Sumeta",
                                            createdDate = LocalDateTime.now()
                                    )
                            )
                        }.onSuccess {
                            return ServerResponse.ok().bodyValueAndAwait("Success")
                        }.onFailure {
                            return ServerResponse.badRequest().buildAndAwait()
                        }
                    }
            )

    suspend fun delete(serverRequest: ServerRequest):ServerResponse{
        return ServerResponse.ok().json().bodyValueAndAwait(memberRepository.delete(serverRequest.pathVariable("id")))
    }

}