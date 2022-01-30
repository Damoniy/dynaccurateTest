package com.damoniy.dynaccurateTest.service

import com.damoniy.dynaccurateTest.dto.*
import com.damoniy.dynaccurateTest.exception.NotFoundException
import com.damoniy.dynaccurateTest.mapper.EventViewMapper
import com.damoniy.dynaccurateTest.mapper.UserFormularyMapper
import com.damoniy.dynaccurateTest.mapper.UserViewMapper
import com.damoniy.dynaccurateTest.messaging.MessageSender
import com.damoniy.dynaccurateTest.model.Event
import com.damoniy.dynaccurateTest.model.User
import com.damoniy.dynaccurateTest.repository.UserRepository
import com.damoniy.dynaccurateTest.util.DateTimeUtil
import com.damoniy.dynaccurateTest.util.EventHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.stream.Collectors

@Service
data class UserService(
    private val repository: UserRepository,
    private val userViewMapper: UserViewMapper,
    private val userFormularyMapper: UserFormularyMapper,
    private val eventViewMapper: EventViewMapper,
    @Autowired private val messageSender: MessageSender,
    private val notFoundMessage: String = "The id of the scope was invalid."
) {
    fun list(): List<UserView> {
        return repository.findAll().stream().map { user -> userViewMapper.map(user)
        }.collect(Collectors.toList())
    }

    private fun findUser(id: Int): User {
        return repository.findById(id).orElseThrow{ NotFoundException(notFoundMessage) }
    }

    fun findUserViewById(id: Int): UserView {
        val user = findUser(id)
        return userViewMapper.map(user)
    }

    fun findEventsFiredByUser(
        id: Int,
        initialDate: LocalDate,
        finalDate: LocalDate
    ): EventView {
        val user = findUser(id)
        user.events = user.events?.stream()?.filter {
                event -> DateTimeUtil.filterDate(event, initialDate.atStartOfDay(), finalDate.atStartOfDay())
        }?.collect(Collectors.toList())
        return eventViewMapper.map(user)
    }

    fun findEventsFiredByUser(id: Int): EventView {
        val user = findUser(id)
        return eventViewMapper.map(user)
    }

    fun post(userCreationFormulary: UserCreationFormulary): UserView {
        val user = userFormularyMapper.map(userCreationFormulary)
        repository.save(user)
        return userViewMapper.map(user)
    }

    fun update(id: Int, updateFormulary: UserUpdateFormulary): UserView {
        val user = this.findUser(id = id)
        user.nickname = updateFormulary.nickname
        return userViewMapper.map(user)
    }

    fun fireEvent(id: Int, eventType: String): User {
        val user = this.findUser(id)
        updateEventList(user, eventType)
        dispatch(user)
        return user
    }

    private fun updateEventList(user: User, eventType: String) {
        user.events = user.events?.plus(EventHelper.add(eventType).build())
    }

    private fun dispatch(user: User?) {
        val event = EventHelper.getLast(user?.events)
        flush()
        sendMessage(EventHelper.stringify(user?.id, event))
    }

    private fun sendMessage(message: String) {
        messageSender.send(message)
    }

    fun delete(id: Int) {
        repository.deleteById(id)
    }

    private fun flush() {
        repository.flush()
    }
}