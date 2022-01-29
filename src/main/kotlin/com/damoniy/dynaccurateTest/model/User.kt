package com.damoniy.dynaccurateTest.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int? = null,
    var nickname: String,
    var registrationDate: LocalDateTime = LocalDateTime.now(),
    @ManyToMany(cascade = [CascadeType.PERSIST])
    var events: List<Event>? = null,
)