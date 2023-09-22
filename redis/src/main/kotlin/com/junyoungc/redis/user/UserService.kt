package com.junyoungc.redis.user

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class UserService(
    private val externalApiService: ExternalApiService,
    private val redisTemplate: StringRedisTemplate,
) {

    fun getUserProfile(userId: String): UserProfile {
        val ops = redisTemplate.opsForValue()
        val cachedName = ops.get("nameKey:${userId}")
        var userName = ""

        if (cachedName != null) {
            userName = cachedName
        } else {
            userName = externalApiService.getUserName(userId)
            ops.set("nameKey:${userId}", userName, 5, TimeUnit.SECONDS)
        }

        val userAge: Int = externalApiService.getUserAge(userId)

        return UserProfile(userName, userAge)
    }
}