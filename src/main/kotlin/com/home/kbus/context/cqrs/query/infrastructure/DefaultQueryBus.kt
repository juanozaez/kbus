package com.home.kbus.context.cqrs.query.infrastructure

import com.home.kbus.context.cqrs.query.domain.NoQueryHandlerForQueryException
import com.home.kbus.context.cqrs.query.domain.Query
import com.home.kbus.context.cqrs.query.domain.QueryBus
import com.home.kbus.context.cqrs.query.domain.QueryHandler
import kotlin.reflect.KClass

class DefaultQueryBus : QueryBus {
    private val queryHandlers: MutableMap<KClass<*>, QueryHandler> = mutableMapOf()

    override fun register(queryHandler: QueryHandler) {
        queryHandlers[queryHandler.subscribedQuery()] = queryHandler
    }

    override fun <R> ask(query: Query): R {
        return queryHandlers[query::class]
                .also { guardMappedQueryHandler(it) }!!
                .handle(query)
    }

    private fun guardMappedQueryHandler(queryHandler: QueryHandler?) {
        queryHandler ?: throw NoQueryHandlerForQueryException()
    }
}