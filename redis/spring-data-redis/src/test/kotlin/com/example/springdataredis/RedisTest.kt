package com.example.springdataredis

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.geo.Distance
import org.springframework.data.geo.Metrics
import org.springframework.data.geo.Point


@SpringBootTest
class RedisTest {
    @Autowired
    private lateinit var personRepository: PersonRepository

    @Test
    fun basicTest() {
        val rand = Person("Junyoung", "Choi").apply {
            address = Address("sillim", country = "korea")
        }

        val saveRand = personRepository.save(rand)
        println(saveRand)

        val findRand = personRepository.findById(saveRand.id!!).get()

        println(findRand)

        println(personRepository.count())

//        println(personRepository.delete(rand))
    }

    @Test
    fun geospatialIndexTest() {
        val rand = Person("Junyoung", "Choi").apply {
            address = Address(location = Point(13.361389, 38.115556))
        }

        personRepository.save(rand)

        // GEORADIUS people:address:location 15.0 37.0 200.0 km 와 동일
        println(personRepository.findByAddressLocationNear(Point(15.0, 37.0), Distance(200.0, Metrics.KILOMETERS)))
    }
}