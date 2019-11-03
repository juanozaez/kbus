package com.home.kbus.context.cqrs.command.domain

interface CommandHandler {
    fun handle(command: Command)
}