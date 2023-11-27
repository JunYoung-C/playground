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
        val rand = Person("Junyoung", "Choi").apply {
            address = Address("sillim", "korea")
        }

        val saveRand = personRepository.save(rand)
        println(saveRand)

        val findRand = personRepository.findById(saveRand.id!!).get()

        println(findRand)

        println(personRepository.count())

//        println(personRepository.delete(rand))
    }
}