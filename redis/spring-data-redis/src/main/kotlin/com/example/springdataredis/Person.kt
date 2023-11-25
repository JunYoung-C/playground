package com.example.springdataredis

import org.springframework.data.annotation.Id

import org.springframework.data.redis.core.RedisHash

@RedisHash("people")
data class Person(
    @Id
    var id: String,
    var firstname: String? = null,
    var lastname: String? = null,
    var address: Address? = null
)

data class Address(
    val city: String? = null,
    val country: String? = null
)