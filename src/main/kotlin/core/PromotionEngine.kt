package core

import promotion.Promotion
import types.Order
import java.lang.Integer.min

class PromotionEngine {
    private var promotions : MutableList<Promotion> = mutableListOf()

    fun addPromotion(promotion: Promotion) {
        promotions.add(promotion)
    }

    fun removePromotion(promotion: Promotion) {
        promotions.remove(promotion)
    }

    private fun calcOrderPriceWithoutPromotions(order: Order) : Int {
        var price: Int = 0
        for (item in order.items)
            price += item.value * item.key.cost
        return price
    }

    private fun findLowestPrice(order: Order, cost: Int):Int {
        var price = calcOrderPriceWithoutPromotions(order) + cost

        for (promotion in promotions) {
            if (promotion.isApplicable(order)) {
                val pair = promotion.applyPromotion(order)
                price = min(price, findLowestPrice(pair.second, cost + pair.first))
            }
        }

        return price
    }

    fun calculateOrderPrice(order: Order): Int  {
        return findLowestPrice(order, 0)
    }

}