package com.junyoungc.notification

import org.springframework.data.redis.connection.stream.MapRecord
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.stream.StreamListener
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class OrderEventStreamListener(
    private val redisTemplate: StringRedisTemplate
) : StreamListener<String, MapRecord<String, String, String>> {

    override fun onMessage(message: MapRecord<String, String, String>?) {
        val map = message!!.value

        val userId = map["userId"] as String
        val productId = map["productId"] as String

        // 주문 건에 대한 메일 발송 처리
        println("[order consumed] userId: $userId + productId: $productId")
    }
}