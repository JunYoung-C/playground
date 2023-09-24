package com.junyoungc.redis.user

import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class ExternalApiService {
    fun getUserName(userId: String): String {
        // 외부 서비스나 db 호출
        try {
            Thread.sleep(500)
        } catch (e: InterruptedException) {
            // do nothing
        }

        println("외부 서비스에서 유저 이름 조회")

        if (userId.equals("A")) {
            return "Adam"
        }

        if (userId.equals("B")) {
            return "Bob"
        }

        return ""
    }

    @Cacheable(cacheNames = ["userAgeCache"], key = "#userId")
    fun getUserAge(userId: String): Int {
        // 외부 서비스나 db 호출
        try {
            Thread.sleep(500)
        } catch (e: InterruptedException) {
            // do nothing
        }

        println("외부 서비스에서 유저 나이 조회")

        if (userId.equals("A")) {
            return 28
        }

        if (userId.equals("B")) {
            return 32
        }

        return 0
    }
}
