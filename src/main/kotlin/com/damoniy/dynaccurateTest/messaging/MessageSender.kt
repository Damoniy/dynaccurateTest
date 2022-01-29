package com.damoniy.dynaccurateTest.messaging

import org.springframework.boot.CommandLineRunner
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired

@Component
class MessageSender(
    val rabbitTemplate: RabbitTemplate,
    @Autowired val queue: Queue
) : CommandLineRunner {
    override fun run(vararg args: String) {
    }

    fun send(message: String) {
        rabbitTemplate.convertAndSend(queue.name, message)
    }
}