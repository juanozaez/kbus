package com.home.kbus.context.cqrs.query.behaviour

import com.home.kbus.context.cqrs.query.domain.NoQueryHandlerForQueryException
import com.home.kbus.context.cqrs.query.domain.Query
import com.home.kbus.context.cqrs.query.domain.QueryHandler
import com.home.kbus.context.cqrs.query.infrastructure.DefaultQueryBus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.reflect.KClass

class SimpleCommandBusTest {
    private val queryBus = DefaultQueryBus()
    private val queryHandler = SimpleQueryHandler()

    @Test
    fun `it should handle a query`() {
        queryBus.register(queryHandler)
        val query = SimpleQuery()

        val result: String = queryBus.ask(query)
        assertEquals("", result)
    }

    @Test
    fun `it should throw an exception if no query handler registered for query`() {
        assertThrows<NoQueryHandlerForQueryException> {
            queryBus.ask(SimpleQuery())
        }
    }
}

class SimpleQuery : Query
@Suppress("UNCHECKED_CAST")
class SimpleQueryHandler : QueryHandler {
    override fun <R> handle(query: Query): R {
        return "" as R
    }

    override fun subscribedQuery(): KClass<out Query> = SimpleQuery::class
}