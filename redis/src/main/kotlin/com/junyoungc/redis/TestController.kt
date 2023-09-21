package com.junyoungc.redis

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession

@RestController
@RequestMapping("/test")
class TestController(
    private val redisTemplate: StringRedisTemplate
) {

    @PostMapping("/fruits")
    fun setFruit(
        @RequestParam name: String
    ): ResponseEntity<Unit> {
        val ops: ValueOperations<String, String> = redisTemplate.opsForValue()
        ops.set("fruit", name)

        return ResponseEntity.ok().build()
    }

    @GetMapping("/fruits")
    fun getFruit(
        @RequestParam name: String
    ): ResponseEntity<String> {
        val ops: ValueOperations<String, String> = redisTemplate.opsForValue()
        val value = ops.get("fruit")

        return ResponseEntity.ok(value)
    }

}