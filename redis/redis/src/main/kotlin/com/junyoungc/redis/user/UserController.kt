package com.junyoungc.redis.user

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
) {
    @GetMapping("{userId}/profile")
    fun getUserProfile(
        @PathVariable userId: String
    ): ResponseEntity<UserProfile> {
        return ResponseEntity.ok(userService.getUserProfile(userId))
    }
}