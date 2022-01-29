package com.damoniy.dynaccurateTest.dto

import com.damoniy.dynaccurateTest.model.Event

data class EventView(
    val userId: Int?,
    val events: List<Event>?,
)