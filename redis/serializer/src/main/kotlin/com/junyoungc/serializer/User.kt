package com.junyoungc.serializer

import java.io.Serializable

data class User(
    val name: String,
    val id: Long,
    val level: Level
): Serializable {
    enum class Level{
        GOLD, SILVER
    }
}