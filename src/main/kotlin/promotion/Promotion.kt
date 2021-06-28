package promotion

import types.Order

interface Promotion {
    fun isApplicable(order: Order): Boolean
    fun applyPromotion(order: Order): Pair<Int, Order>
}
