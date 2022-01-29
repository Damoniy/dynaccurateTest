package com.damoniy.dynaccurateTest.util

import com.damoniy.dynaccurateTest.model.Event
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateTimeUtil {
    private lateinit var dateTimeFormater: DateTimeFormatter

    fun formatLocalDateTime(configuration: String, localDateTime: LocalDateTime?): String? {
        dateTimeFormater = DateTimeFormatter.ofPattern(configuration)
        return localDateTime?.format(dateTimeFormater)
    }

    fun filterDate(
        event: Event,
        initialDate: LocalDateTime,
        finalDate: LocalDateTime,
    ) = event.eventDateTime.isEqual(initialDate) ||
            event.eventDateTime.isAfter(initialDate) &&
            event.eventDateTime.isBefore(finalDate) ||
            event.eventDateTime.isEqual(finalDate)

    fun extractDateFrom(fromDate: String?): LocalDate {
        return LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("yyyyMMdd"))
    }

    fun assertDateStringNonNull(initialDate: String?, finalDate: String?): Boolean {
        return !(initialDate.isNullOrEmpty() || finalDate.isNullOrEmpty())
    }
}