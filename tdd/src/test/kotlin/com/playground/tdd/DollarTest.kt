package com.playground.tdd

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DollarTest {
    @Test
    fun testMultiplication() {
        val five = Dollar(5)
        var product: Dollar = five.times(2)
        product.amount shouldBe 10

        product = five.times(3)
        product.amount shouldBe 15
    }
}