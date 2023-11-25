package com.example.springdataredis

import org.springframework.data.repository.CrudRepository

interface PersonRepository : CrudRepository<Person, String>