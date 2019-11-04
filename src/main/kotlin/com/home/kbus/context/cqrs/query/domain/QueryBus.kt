package com.home.kbus.context.cqrs.query.domain

interface QueryBus {
    fun <R> ask(query: Query): R
    fun register(queryHandler: QueryHandler)
}