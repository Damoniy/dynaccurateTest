package com.damoniy.dynaccurateTest.util

import com.damoniy.dynaccurateTest.model.Event

object EventHelper {

    private lateinit var eventType: String

    fun add(eventType: String): EventHelper {
        this.eventType = eventType
        return this
    }

    fun build(): Event {
        return Event(eventType = eventType)
    }

    fun getLast(list: List<Event>?): Event? {
        return list?.last()
    }

    fun stringify(userId: Int?, event: Event?): String {
        return "{\"userId\": \"$userId\","+
                "\"event\": {\"id\": \"${event?.id}\"," +
                "\"eventType\": \"${event?.eventType}\"," +
                " \"eventDateTime\": \"${DateTimeUtil.formatLocalDateTime("MM-dd-yyyy HH:mm:ss", event?.eventDateTime)}\"}})"
    }
}