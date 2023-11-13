package com.junyoungc.serializer

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@SpringBootTest
class SerializerApplicationTests {

    @Autowired
    lateinit var redisTemplate: RedisTemplate<Any, Any>

    @BeforeEach
    fun init() {
        redisTemplate.keySerializer = StringRedisSerializer()
    }

    @Test
    fun contextLoads() {
    }

    @Test
    fun testJdkSerializationRedisSerializer() {
        // JdkSerializationRedisSerializer는 default다.
        val user: User = User("stella", 123, User.Level.GOLD)

        redisTemplate.opsForValue().set("key1", user)
    }

    @Test
    fun testStringRedisTemplate() {
        // JdkSerializationRedisSerializer는 default다.
        redisTemplate.valueSerializer = StringRedisSerializer()
        val user: User = User("stella", 123, User.Level.GOLD)

//        redisTemplate.opsForValue().set("key2", user) // user가 문자열이 아니라서 에러 발생
        redisTemplate.opsForValue().set("key2", "name : stella, id : 123, level : GOLD")
    }

    @Test
    fun testJackson2JsonRedisSerializer() {
        // JdkSerializationRedisSerializer는 default다.
        redisTemplate.valueSerializer = Jackson2JsonRedisSerializer(User::javaClass.javaClass)
        val user: User = User("stella", 123, User.Level.GOLD)

        redisTemplate.opsForValue().set("key3", user)
    }
}
