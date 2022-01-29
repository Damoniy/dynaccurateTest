package com.damoniy.dynaccurateTest.controller

import com.damoniy.dynaccurateTest.dto.*
import com.damoniy.dynaccurateTest.service.UserService
import com.damoniy.dynaccurateTest.util.DateTimeUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
) {

    @GetMapping
    fun list(): List<UserView> {
        return userService.list()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): UserView {
        return userService.findUserViewById(id)
    }

    @GetMapping("/{id}/events")
    fun findEvents(
        @PathVariable id: Int,
        @RequestParam(required = false) fromDate: String?,
        @RequestParam(required = false) toDate: String?
    ): EventView {
        if(DateTimeUtil.assertDateStringNonNull(fromDate, toDate)) {
            val initialDate = DateTimeUtil.extractDateFrom(fromDate)
            val finalDate = DateTimeUtil.extractDateFrom(toDate)
            return userService.findEventsFiredByUser(id, initialDate, finalDate)
        }
        return userService.findEventsFiredByUser(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    fun post(
        @RequestBody @Valid form: UserCreationFormulary, uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<UserView> {
        val topicView = userService.post(form)
        val uri = uriBuilder.path("/topics/${topicView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicView)
    }

    @PutMapping("/{id}")
    @Transactional
    fun update(@RequestBody @Valid updateForm: UserUpdateFormulary, @PathVariable id: Int): ResponseEntity<UserView> {
        val updatedTopic = userService.update(id, updateForm)
        return ResponseEntity.ok().body(updatedTopic)
    }

    @PutMapping("/{id}/events")
    @Transactional
    fun putEvent(@RequestBody @Valid eventFormulary: EventFormulary, @PathVariable id: Int): ResponseEntity<EventView> {
        val user = userService.fireEvent(id, eventFormulary.eventType)
        val view = EventView(user.id, user.events)
        return ResponseEntity.ok().body(view)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun delete(@PathVariable id: Int) {
        userService.delete(id)
    }
}