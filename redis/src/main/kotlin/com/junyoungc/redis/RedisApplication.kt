package com.junyoungc.redis

import com.junyoungc.redis.chat.ChatService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@EnableCaching
@SpringBootApplication
class RedisApplication(
    private val chatService: ChatService
): CommandLineRunner {
    // 편의상 커맨드로 구현. ui 미구현
    override fun run(vararg args: String?) {
        println("Application started..")

        chatService.enterChatRoom("chat1")
    }
}

fun main(args: Array<String>) {
    runApplication<RedisApplication>(*args)
}
