package com.damoniy.dynaccurateTest.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Event(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) val id: Int? = null,
    val eventType: String,
    val eventDateTime: LocalDateTime = LocalDateTime.now(),
) {
    override fun toString(): String {
        return "Event(id=$id, eventType=$eventType, eventDateTime=$eventDateTime)"
    }
}