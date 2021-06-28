package core

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import types.SKUItem

internal class PromotionEngineTest {

    private val a = SKUItem("A", 50)
    private val b = SKUItem("B", 30)
    private val c = SKUItem("C", 20)
    private val d = SKUItem("D", 15)

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun noPromotionApplied() {
    }

}