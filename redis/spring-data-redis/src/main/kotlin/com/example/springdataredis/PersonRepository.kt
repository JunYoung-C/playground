package com.example.springdataredis

import org.springframework.data.geo.Circle
import org.springframework.data.geo.Distance
import org.springframework.data.geo.Point
import org.springframework.data.repository.CrudRepository

interface PersonRepository : CrudRepository<Person, String> {
    fun findByAddressLocationNear(point: Point, distance: Distance): List<Person>
    fun findByAddressLocationWithin(circle: Circle): List<Person?>
}