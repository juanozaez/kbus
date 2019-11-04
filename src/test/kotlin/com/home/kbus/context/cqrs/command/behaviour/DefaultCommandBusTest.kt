package com.home.kbus.context.cqrs.command.behaviour

import com.home.kbus.context.cqrs.command.domain.Command
import com.home.kbus.context.cqrs.command.domain.CommandHandler
import com.home.kbus.context.cqrs.command.domain.NoCommandHandlerForCommandException
import com.home.kbus.context.cqrs.command.infrastructure.DefaultCommandBus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SimpleCommandBusTest {

    private val commandBus = DefaultCommandBus()
    private val commandHandler = SimpleCommandHandler()

    @Test
    fun `it should handle a command`() {
        commandBus.register(commandHandler)
        val command = SimpleCommand()

        commandBus.handle(command)

        assertEquals(commandHandler.command, command)
    }

    @Test
    fun `it should throw an exception if no command handler registered for command`() {
        assertThrows<NoCommandHandlerForCommandException> {
            commandBus.handle(SimpleCommand())
        }
    }
}

class SimpleCommand : Command
class SimpleCommandHandler : CommandHandler {
    var command: Command? = null
    override fun subscribedCommand() = SimpleCommand::class
    override fun handle(command: Command) {
        this.command = command
    }
}