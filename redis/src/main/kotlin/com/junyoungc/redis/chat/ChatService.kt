package com.junyoungc.redis.chat

import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.stereotype.Service
import java.util.*

@Service
class ChatService(
    private val container: RedisMessageListenerContainer,
    private val redisTemplate: StringRedisTemplate
): MessageListener {
    override fun onMessage(message: Message, pattern: ByteArray?) {
        println("Message: $message")
    }

    fun enterChatRoom(chatRoomName: String) {
        container.addMessageListener(this, ChannelTopic(chatRoomName)) // chatRoomName 이름의 토픽에 입장

        val scanner = Scanner(System.`in`)

        while(scanner.hasNextLine()) {
            val line = scanner.nextLine()

            if (line.equals("q")) {
                println("Quit..")
                break
            }

            redisTemplate.convertAndSend(chatRoomName, line)
        }

        container.removeMessageListener(this)
    }
}