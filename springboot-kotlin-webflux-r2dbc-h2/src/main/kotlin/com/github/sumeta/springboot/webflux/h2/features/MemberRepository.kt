package com.github.sumeta.springboot.webflux.h2.features

import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitFirstOrNull
import org.springframework.data.r2dbc.core.awaitOneOrNull
import org.springframework.data.r2dbc.core.awaitRowsUpdated
import org.springframework.data.relational.core.query.Criteria
import org.springframework.stereotype.Repository

@Repository
class MemberRepository(private val databaseClient: DatabaseClient) {
    suspend fun getAll() =
            databaseClient.select()
                    .from(MemberEntity::class.java)
                    .fetch().all().asFlow()


    suspend fun get(id:String) =
            databaseClient.select().from(MemberEntity::class.java)
                    .matching(Criteria.where(MemberEntity::id.name) .`is`(id))
                    .fetch().awaitFirstOrNull()

    suspend fun add(memberEntity: MemberEntity) =
            databaseClient.insert()
                    .into(MemberEntity::class.java)
                    .using(memberEntity)
                    .fetch()
                    .awaitOneOrNull()

    suspend fun edit(memberEntity: MemberEntity) =
            databaseClient.update()
                    .table(MemberEntity::class.java)
                    .using(memberEntity)
                    .fetch()
                    .awaitRowsUpdated()

    suspend fun delete(id:String) =
            databaseClient.delete()
                    .from(MemberEntity::class.java)
                    .matching(Criteria.where(MemberEntity::id.name).`is`(id))
                    .fetch()
                    .awaitRowsUpdated()
}