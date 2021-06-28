package types

data class Order(var items: MutableMap<SKUItem, Int>)
