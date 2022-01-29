package com.damoniy.dynaccurateTest.dto

import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class UserCreationFormulary(
    @field:NotNull val id: Int,
    @field:NotEmpty @field:Size(min = 7, max = 100) val nickname: String,
    val registrationDate: LocalDateTime = LocalDateTime.now()
)