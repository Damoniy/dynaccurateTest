package com.damoniy.dynaccurateTest.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class UserUpdateFormulary(
    val id: Int,
    @field:NotEmpty @field:Size(min = 7, max = 100) val nickname: String
    )