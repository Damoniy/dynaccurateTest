package com.damoniy.dynaccurateTest.repository

import com.damoniy.dynaccurateTest.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Int>