package com.damoniy.dynaccurateTest.dto

import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class ErrorView(val timestamp: LocalDateTime = LocalDateTime.now(),
                     @field:NotNull val status: Int,
                     @field:NotEmpty val error: String,
                     val message: String?,
                     @field:NotEmpty val path: String)