package com.home.kbus.context.cqrs.command.domain

import kotlin.reflect.KClass

interface CommandHandler {
    fun handle(command: Command)
    fun subscribedCommand(): KClass<out Command >
}