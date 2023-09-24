package com.junyoungc.payment

import org.springframework.data.redis.connection.stream.MapRecord
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.stream.StreamListener
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class OrderEventStreamListener(
    private val redisTemplate: StringRedisTemplate
) : StreamListener<String, MapRecord<String, String, String>> {
    var paymentProcessId: Int = 0

    override fun onMessage(message: MapRecord<String, String, String>?) {
        val map = message!!.value

        val userId = map["userId"] as String
        val productId = map["productId"] as String
        val price = map["price"] as String

        // 결제 관련 로직
        // ...

        paymentProcessId++

        // 결제 완료 이벤트 발행
        val fieldMap = mapOf(
            "userId" to userId,
            "productId" to productId,
            "price" to price,
            "paymentProcessId" to paymentProcessId.toString()
        )


        redisTemplate.opsForStream<String, Map<String, String>>()
            .add(MapRecord.create("payment-events", fieldMap))

        println("[order consumed] created payment: $paymentProcessId")
    }
}