package com.damoniy.dynaccurateTest

import com.damoniy.dynaccurateTest.model.Event
import com.damoniy.dynaccurateTest.model.User
import com.damoniy.dynaccurateTest.repository.UserRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest(
) {
    @Autowired private lateinit var mockMvc: MockMvc

    @Autowired private lateinit var userRepository: UserRepository

    private var mapper = ObjectMapper()

    fun setupJackson() {
        mapper.registerModule(Jdk8Module());
        mapper.registerModule(JavaTimeModule())
    }

    @Test
    fun getUserList() {
        userRepository.save(User(nickname = "Test"))

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("\$").isArray)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].id").isNumber)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].nickname").isString)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].registrationDate").isString)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun getUserById() {
        val user = userRepository.save(User(nickname = "Test"))
        mockMvc.perform(MockMvcRequestBuilders.get("/users/${user.id}"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.id").value(user.id))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.nickname").value(user.nickname))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun createUser() {
        setupJackson()
        userRepository.deleteAll()
        val json = mapper.writeValueAsString(User(nickname = "Test"))
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun updateUser() {
        setupJackson()
        val user = userRepository
            .save(User(nickname = "Test"))
            .copy(nickname = "Updated")
        val json = mapper.writeValueAsString(user)

        mockMvc.perform(MockMvcRequestBuilders.put("/users/${user.id}")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())

        val findById = userRepository.findById(user.id!!)
        Assertions.assertTrue(findById.isPresent)
        Assertions.assertEquals(user.nickname, findById.get().nickname)
    }

    @Test
    fun deleteUser() {
        setupJackson()
        val user = userRepository.save(User(nickname = "Test"))

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/${user.id}"))
            .andExpect(MockMvcResultMatchers.status().isNoContent)
            .andDo(MockMvcResultHandlers.print())

        val findById = userRepository.findById(user.id!!)
        Assertions.assertFalse(findById.isPresent)
    }

    @Test
    fun fireEvent() {
        setupJackson()
        val user = userRepository.save(User(nickname = "Test"))
        val event = Event(eventType = "Click")
        val json = mapper.writeValueAsString(event)

        mockMvc.perform(MockMvcRequestBuilders.put("/users/${user.id}/events")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun getEventList() {
        val user = userRepository.save(User(nickname = "Test", events = listOf()))
        mockMvc.perform(MockMvcRequestBuilders.get("/users/${user.id}/events"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }
}