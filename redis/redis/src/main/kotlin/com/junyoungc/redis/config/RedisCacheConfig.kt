package com.junyoungc.redis.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.CacheKeyPrefix
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@Configuration
class RedisCacheConfig {

    @Bean
    fun cacheManager(connectionFactory: RedisConnectionFactory): RedisCacheManager {
        val redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
            .disableCachingNullValues()
            .entryTtl(Duration.ofSeconds(10)) // 기본 ttl
            .computePrefixWith(CacheKeyPrefix.simple())
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer())
            )

        val configMap: HashMap<String, RedisCacheConfiguration> = HashMap()

        configMap.put(
            "userAgeCache",
            RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(10))
        ) // 특정 캐시에 대한 ttl

        return RedisCacheManager
            .RedisCacheManagerBuilder
            .fromConnectionFactory(connectionFactory)
            .cacheDefaults(redisCacheConfiguration)
            .withInitialCacheConfigurations(configMap)
            .build()
    }
}