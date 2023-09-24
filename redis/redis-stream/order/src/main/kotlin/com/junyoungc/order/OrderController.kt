package com.junyoungc.order

import org.springframework.data.redis.connection.stream.MapRecord
import org.springframework.data.redis.connection.stream.RecordId
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
class OrderController(
    private val redisTemplate: StringRedisTemplate
) {

    @GetMapping
    fun order(
        @RequestParam userId: String,
        @RequestParam productId: String,
        @RequestParam price: String
    ): ResponseEntity<String> {

        val fieldMap = mapOf("userId" to userId, "productId" to productId, "price" to price)

        redisTemplate.opsForStream<String, Map<String, String>>()
            .add(MapRecord.create("order-events", fieldMap))

        println("order created..")
        return ResponseEntity.ok("ok")
    }
}