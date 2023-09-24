package com.junyoungc.notification

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.stream.Consumer
import org.springframework.data.redis.connection.stream.ReadOffset
import org.springframework.data.redis.connection.stream.StreamOffset
import org.springframework.data.redis.stream.StreamMessageListenerContainer
import org.springframework.data.redis.stream.Subscription
import java.time.Duration

@Configuration
class RedisStreamConfig(
    private val orderEventStreamListener: OrderEventStreamListener,
    private val paymentEventStreamListener: PaymentEventStreamListener
) {

    @Bean
    fun orderSubscription(factory: RedisConnectionFactory): Subscription {
        val options = StreamMessageListenerContainer.StreamMessageListenerContainerOptions
            .builder()
            .pollTimeout(Duration.ofSeconds(1))
            .build()

        val listenerContainer = StreamMessageListenerContainer.create(factory, options)

        val subscription = listenerContainer.receiveAutoAck(
            Consumer.from("notification-service-group", "instance-1"), // 컨슈머 그룹을 미리 생성해두어야 한다. XGROUP CREATE order-events payment-service-group $ MKSTREAM
            StreamOffset.create("order-events", ReadOffset.lastConsumed()),
            orderEventStreamListener
        )

        listenerContainer.start()
        return subscription
    }

    @Bean
    fun paymentSubscription(factory: RedisConnectionFactory): Subscription {
        val options = StreamMessageListenerContainer.StreamMessageListenerContainerOptions
            .builder()
            .pollTimeout(Duration.ofSeconds(1))
            .build()

        val listenerContainer = StreamMessageListenerContainer.create(factory, options)

        val subscription = listenerContainer.receiveAutoAck(
            Consumer.from("notification-service-group", "instance-1"), // 컨슈머 그룹을 미리 생성해두어야 한다. XGROUP CREATE order-events payment-service-group $ MKSTREAM
            StreamOffset.create("payment-events", ReadOffset.lastConsumed()),
            paymentEventStreamListener
        )

        listenerContainer.start()
        return subscription
    }
}