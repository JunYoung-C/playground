package com.junyoungc.redis

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
class ClusterTest{
    @Autowired
    lateinit var redisTemplate: StringRedisTemplate
    val dummyValue = "banana"

    @Test
    fun setValues() {
        val ops = redisTemplate.opsForValue()

        for (i in 0..1000) {
            ops.set("name:$i", dummyValue)
        }
    }

    @Test
    fun getValues() {
        val ops = redisTemplate.opsForValue()

        for (i in 0..1000) {
            val value = ops.get("name:$i")

            Assertions.assertThat(value).isEqualTo(dummyValue)
        }
    }
}