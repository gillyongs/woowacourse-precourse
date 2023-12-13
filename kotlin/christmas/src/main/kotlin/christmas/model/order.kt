package christmas.model

import christmas.view.ErrorView
import christmas.model.menu


class order(orderString: String) {
    private val menu = menu()
    private val errorView = ErrorView()
    private val orderMap = mutableMapOf<String, Int>()
    private var totalPrice = 0
    private var freebie = false
    private var menuNums = 0
    private var orderErrorTrigger = false

    init {
        initializeStringToMap(orderString)
        validateOrder()
        calculateTotalPrice()
        freebieCheck()
    }

    private fun initializeStringToMap(orderString: String) {
        val orderArray = orderString.split(",")
        val menuNames = menu.extractMenuNames()

        orderArray.forEach { order ->
            val parts = order.split("-")
            val menuName = parts[0]
            val menuCount = parts[1].toInt()
            menuNums += menuCount
            if (isInvalidOrder(orderMap, menuNames, menuName, menuCount)) {
                errorView.notValidOrder()
                orderErrorTrigger = true
            }

            orderMap[menuName] = menuCount
        }
    }

    private fun isInvalidOrder(orderMap: Map<String, Int>, menuNames: Array<String>, menuName: String, menuCount: Int): Boolean {
        return orderMap.containsKey(menuName) || !menuNames.contains(menuName) || menuCount <= 0
    }

    private fun validateOrder() {
        if (orderMap.isEmpty()) {
            errorView.notValidOrder()
            orderErrorTrigger = true
        }

        val drinkCount = orderMap.keys.count { menuName -> menu.getMenuNamesByCategory("음료").contains(menuName) }
        if (drinkCount == menuNums) {
            errorView.notValidOrder()
            orderErrorTrigger = true
        }

        if (menuNums > 20) {
            errorView.notValidOrder()
            orderErrorTrigger = true
        }
    }

    fun getOrder(): Map<String, Int> {
        return orderMap.toMap()
    }

    private fun calculateTotalPrice() {
        for ((menuName, menuCount) in orderMap) {
            val menuPrice = menu.getMenuPrice(menuName)
            totalPrice += menuCount * menuPrice
        }
    }

    fun getTotalPrice(): Int {
        return totalPrice
    }

    private fun freebieCheck() {
        freebie = totalPrice >= 120000
    }

    fun getFreebie(): Boolean {
        return freebie
    }

    fun countMenuInCategory(category: String): Int {
        var count = 0
        for ((menuName, menuCount) in orderMap) {
            if (menu.isInCategory(menuName, category)) {
                count += menuCount
            }
        }
        return count
    }

    fun getOrderErrorTrigger(): Boolean {
        return orderErrorTrigger
    }
}
