package com.home.kbus.context.cqrs.command.domain

import kotlin.reflect.KClass

interface CommandBus {
    fun handle(command: Command)
    fun register(commandHandler: CommandHandler, command: KClass<*>)
}