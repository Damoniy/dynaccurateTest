package com.damoniy.dynaccurateTest.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.context.support.RequestHandledEvent
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class SpringFoxConfig: WebMvcConfigurer {

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addRedirectViewController("/", "/docs").setKeepQueryParams(true)
        registry.addRedirectViewController("/docs", "/swagger-ui/").setKeepQueryParams(true)
    }

    @Bean
    fun swagger(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.damoniy.dynaccurateTest.controller"))
            .paths(PathSelectors.any())
            .build()
    }
}