package com.home.kbus.context.cqrs.command.behaviour

import com.home.kbus.context.cqrs.command.domain.Command
import com.home.kbus.context.cqrs.command.domain.CommandHandler
import com.home.kbus.context.cqrs.command.domain.NoCommandHandlerForCommandException
import com.home.kbus.context.cqrs.command.infrastructure.SimpleCommandBus
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SimpleCommandBusTest {

    private val commandBus = SimpleCommandBus()
    private val commandHandler: CommandHandler = mockk(relaxed = true)

    @Test
    fun `it should handle a command`() {
        commandBus.register(commandHandler, SimpleCommand::class)
        val command = SimpleCommand()

        commandBus.handle(command)

        verify { commandHandler.handle(command) }
    }

    @Test
    fun `it should throw an exception if no command handler registered for command`() {
        assertThrows<NoCommandHandlerForCommandException> {
            commandBus.handle(SimpleCommand())
        }
    }
}

class SimpleCommand : Command