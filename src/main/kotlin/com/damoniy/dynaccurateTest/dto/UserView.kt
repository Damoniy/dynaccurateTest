package com.damoniy.dynaccurateTest.dto

import java.time.LocalDateTime

data class UserView(
    val id: Int?,
    val nickname: String,
    val registrationDate: LocalDateTime,
)
