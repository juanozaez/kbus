package com.home.kbus.context.cqrs.command.infrastructure

import com.home.kbus.context.cqrs.command.domain.Command
import com.home.kbus.context.cqrs.command.domain.CommandBus
import com.home.kbus.context.cqrs.command.domain.CommandHandler
import com.home.kbus.context.cqrs.command.domain.NoCommandHandlerForCommandException
import kotlin.reflect.KClass

class DefaultCommandBus : CommandBus {
    private val commandHandlers: MutableMap<KClass<*>, CommandHandler> = mutableMapOf()

    override fun register(commandHandler: CommandHandler) {
        commandHandlers[commandHandler.subscribedCommand()] = commandHandler
    }

    override fun handle(command: Command) {
        commandHandlers[command::class]
                .also { guardMappedCommandHandler(it) }!!
                .handle(command)
    }

    private fun guardMappedCommandHandler(commandHandler: CommandHandler?) {
        commandHandler ?: throw NoCommandHandlerForCommandException()
    }
}
