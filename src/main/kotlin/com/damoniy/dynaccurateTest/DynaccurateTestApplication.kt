package com.damoniy.dynaccurateTest

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.amqp.RabbitProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


@EnableRabbit
@SpringBootApplication
class DynaccurateTestApplication {

	@Bean
	fun rabbitConnectionFactory(config: RabbitProperties?): CachingConnectionFactory? {
		val connectionFactory = CachingConnectionFactory()
		connectionFactory.rabbitConnectionFactory.setUri("amqps://qmshcfth:t2vIixo-qqsUcNzuzvnWyTEAU55l09DS@fox.rmq.cloudamqp.com/qmshcfth")
		return connectionFactory
	}
}

fun main(args: Array<String>) {
	runApplication<DynaccurateTestApplication>(*args)
}
