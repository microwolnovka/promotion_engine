package promotion

import types.Order
import types.SKUItem

class QuantityPromotion (private val item: SKUItem, private val quantity: Int, private val cost: Int): Promotion {

    override fun isApplicable(order: Order): Boolean {
        if (order.items.getOrDefault(item, 0) >= quantity)
            return true
        return false
    }

    override fun applyPromotion(order: Order): Pair<Int, Order> {
        if (isApplicable(order)) {
            val newOrder: Order = Order(order.items)
            newOrder.items[item] = newOrder.items[item]!! - quantity
            return Pair(cost, newOrder)
        }
        return Pair(0, order)
    }
}