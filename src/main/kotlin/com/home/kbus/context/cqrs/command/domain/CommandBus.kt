package com.home.kbus.context.cqrs.command.domain

interface CommandBus {
    fun handle(command: Command)
    fun register(commandHandler: CommandHandler)
}