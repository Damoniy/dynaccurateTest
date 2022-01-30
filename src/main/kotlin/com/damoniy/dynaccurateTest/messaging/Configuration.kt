package com.damoniy.dynaccurateTest.messaging

import com.rabbitmq.client.ConnectionFactory
import org.apache.tomcat.jni.User.username
import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
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