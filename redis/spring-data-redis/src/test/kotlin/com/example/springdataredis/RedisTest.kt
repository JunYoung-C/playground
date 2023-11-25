package com.example.springdataredis

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RedisTest {
    @Autowired
    private lateinit var personRepository: PersonRepository

    @Test
    fun basicTest() {
        val rand = Person("rand", "al'thor").apply {
            address = Address("emond's field", "andor")
        }

        println(personRepository.save(rand))

        val findRand = personRepository.findById(rand.id).get()

        println(findRand)

        println(personRepository.count())

        println(personRepository.delete(rand))
    }
}