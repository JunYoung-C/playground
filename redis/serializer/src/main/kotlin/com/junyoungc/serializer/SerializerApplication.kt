package com.junyoungc.serializer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate

@SpringBootApplication
class SerializerApplication

fun main(args: Array<String>) {
    runApplication<SerializerApplication>(*args)
}


