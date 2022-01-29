package com.damoniy.dynaccurateTest.mapper

import com.damoniy.dynaccurateTest.dto.EventView
import com.damoniy.dynaccurateTest.model.User
import org.springframework.stereotype.Component

@Component
class EventViewMapper : RawMapper<User, EventView> {
    override fun map(t: User): EventView {
        return EventView(t.id, t.events)
    }
}