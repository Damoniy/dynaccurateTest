package com.damoniy.dynaccurateTest.messaging

import org.springframework.amqp.core.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class Configuration {
    private val queueName: String = "events"

    @Bean
    fun queue(): Queue {
        return Queue(queueName, true)
    }
}