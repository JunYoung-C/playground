package com.example.springdataredis

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.convert.KeyspaceConfiguration
import org.springframework.data.redis.core.convert.MappingConfiguration
import org.springframework.data.redis.core.index.IndexConfiguration
import org.springframework.data.redis.core.index.IndexDefinition
import org.springframework.data.redis.core.index.SimpleIndexDefinition
import org.springframework.data.redis.core.mapping.RedisMappingContext
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories


@Configuration
@EnableRedisRepositories
class RedisConfig {
    @Bean
    fun connectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory()
    }

    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory?): RedisTemplate<*, *> {
        return RedisTemplate<ByteArray, ByteArray>().apply {
            this.connectionFactory = redisConnectionFactory
        }
    }

    @Bean
    fun keyValueMappingContext(): RedisMappingContext {
        return RedisMappingContext(
            MappingConfiguration(MyIndexConfiguration(), KeyspaceConfiguration())
        )
    }

    class MyIndexConfiguration : IndexConfiguration() {
        override fun initialConfiguration(): Iterable<IndexDefinition> {
            return setOf<IndexDefinition>(SimpleIndexDefinition("people", "firstname"))
        }
    }
}


