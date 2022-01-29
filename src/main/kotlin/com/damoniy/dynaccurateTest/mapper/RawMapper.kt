package com.damoniy.dynaccurateTest.mapper

interface RawMapper<T, U> {
    fun map(t: T): U
}
