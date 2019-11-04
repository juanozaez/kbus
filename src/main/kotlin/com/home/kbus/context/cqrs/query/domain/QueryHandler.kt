package com.home.kbus.context.cqrs.query.domain

import kotlin.reflect.KClass

interface QueryHandler {
    fun <R> handle(query: Query): R
    fun subscribedQuery(): KClass<out Query>
}