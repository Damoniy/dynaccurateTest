package com.damoniy.dynaccurateTest.mapper

import com.damoniy.dynaccurateTest.dto.UserView
import com.damoniy.dynaccurateTest.model.User
import org.springframework.stereotype.Component

@Component
class UserViewMapper: RawMapper<User, UserView> {
    override fun map(t: User): UserView {
        return UserView(
            id = t.id,
            nickname = t.nickname,
            registrationDate = t.registrationDate,
        )
    }
}