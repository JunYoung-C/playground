package com.example.springdataredis

import org.springframework.data.annotation.Id
import org.springframework.data.geo.Point
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.GeoIndexed

@RedisHash("people")
data class Person(
    @Id
    var id: String?,
    var firstname: String? = null,
    var lastname: String? = null,
    var address: Address? = null
)

data class Address(
    val city: String? = null,
    @GeoIndexed val location: Point? = null,
    val country: String? = null
)