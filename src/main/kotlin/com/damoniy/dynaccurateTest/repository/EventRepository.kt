package com.damoniy.dynaccurateTest.repository

import com.damoniy.dynaccurateTest.model.Event
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository: JpaRepository<Event, Int> {
}