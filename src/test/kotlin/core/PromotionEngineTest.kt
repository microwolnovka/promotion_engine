package core

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import promotion.CouplePromotion
import promotion.QuantityPromotion
import types.Order
import types.SKUItem
import kotlin.test.assertEquals

internal class PromotionEngineTest {

    private val a = SKUItem("A", 50)
    private val b = SKUItem("B", 30)
    private val c = SKUItem("C", 20)
    private val d = SKUItem("D", 15)

    private val A3Promotion = QuantityPromotion(a, 3, 130)
    private val B2promotion = QuantityPromotion(b, 2, 45)
    private val CDPromotion = CouplePromotion(c, d, 30)
    private val DStupidPromotion = QuantityPromotion(b, 2, 145)

    private val promotionEngine = PromotionEngine()


    @BeforeEach
    fun setUp() {
        promotionEngine.addPromotion(A3Promotion)
        promotionEngine.addPromotion(B2promotion)
        promotionEngine.addPromotion(CDPromotion)
    }

    @Test
    fun noPromotionApplied() {
        val order = Order(mutableMapOf(a to 1,b to 1, c to 1))
        assertEquals(promotionEngine.calculateOrderPrice(order), 100)
    }

    @Test
    fun promotion2BApplied() {
        val order = Order(mutableMapOf(a to 1,b to 2, c to 1))
        assertEquals(promotionEngine.calculateOrderPrice(order), 115)
    }

    @Test
    fun promotion3AApplied() {
        val order = Order(mutableMapOf(a to 3,b to 1, c to 1))
        assertEquals(promotionEngine.calculateOrderPrice(order), 180)
    }

    @Test
    fun promotion3Aand2BApplied() {
        val order = Order(mutableMapOf(a to 3,b to 2, c to 1))
        assertEquals(promotionEngine.calculateOrderPrice(order), 195)
    }

    @Test
    fun doublePromotion3Aand2BApplied() {
        val order = Order(mutableMapOf(a to 6,b to 4, c to 1))
        assertEquals(promotionEngine.calculateOrderPrice(order), 370)
    }

    @Test
    fun promotionCDApplied() {
        val order = Order(mutableMapOf(a to 1,b to 1, c to 1, d to 1))
        assertEquals(promotionEngine.calculateOrderPrice(order), 110)
    }

    @Test
    fun promotionCDcheckApplied() {
        val order = Order(mutableMapOf(c to 4, d to 3))
        assertEquals(promotionEngine.calculateOrderPrice(order), 110)
    }
    @Test
    fun promotion3AandCDApplied() {
        val order = Order(mutableMapOf(a to 3,b to 1, c to 1, d to 1))
        assertEquals(promotionEngine.calculateOrderPrice(order), 190)
    }

    @Test
    fun testScenarioB() {
        val order = Order(mutableMapOf(a to 5,b to 5, c to 1))
        assertEquals(promotionEngine.calculateOrderPrice(order), 370)
    }

    @Test
    fun testScenarioC() {
        val order = Order(mutableMapOf(a to 3,b to 5, c to 1, d to 1))
        assertEquals(promotionEngine.calculateOrderPrice(order), 280)
    }

    @Test
    fun stupidPromotionTest() {
        val order = Order(mutableMapOf(d to 5))
        promotionEngine.removePromotion(A3Promotion)
        promotionEngine.removePromotion(B2promotion)
        promotionEngine.removePromotion(CDPromotion)
        promotionEngine.addPromotion(DStupidPromotion)

        assertEquals(promotionEngine.calculateOrderPrice(order), 75)
    }
    @Test
    fun removeAllPromotions() {
        val order = Order(mutableMapOf(a to 3,b to 1, c to 1, d to 1))
        promotionEngine.removePromotion(A3Promotion)
        promotionEngine.removePromotion(B2promotion)
        promotionEngine.removePromotion(CDPromotion)

        assertEquals(promotionEngine.calculateOrderPrice(order), 215)
    }
}