package com.damoniy.dynaccurateTest.mapper

import com.damoniy.dynaccurateTest.dto.UserCreationFormulary
import com.damoniy.dynaccurateTest.model.User
import org.springframework.stereotype.Component

@Component
class UserFormularyMapper: RawMapper<UserCreationFormulary, User> {
    override fun map(t: UserCreationFormulary): User {
        return User(
            nickname = t.nickname,
            registrationDate = t.registrationDate,
        )
    }
}