package promotion

import types.Order
import types.SKUItem

class CouplePromotion(private val firstItem: SKUItem, private val secondItem: SKUItem, private val cost: Int): Promotion {
    override fun isApplicable(order: Order): Boolean {
        if (order.items.getOrDefault(firstItem, 0) > 0 &&
            order.items.getOrDefault(secondItem, 0) > 0)
            return true
        return false
    }

    override fun applyPromotion(order: Order): Pair<Int, Order> {
        if (isApplicable(order)) {
            val newOrder: Order = Order(order.items)
            newOrder.items[firstItem] = newOrder.items[firstItem]!! - 1
            newOrder.items[secondItem] = newOrder.items[secondItem]!! - 1
            return Pair(cost, newOrder)
        }
        return Pair(0, order)
    }
}