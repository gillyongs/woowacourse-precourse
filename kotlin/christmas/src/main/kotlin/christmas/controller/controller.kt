package christmas.controller

import christmas.model.discount
import christmas.model.order
import christmas.view.InputView
import christmas.view.ErrorView
import christmas.view.OutputView

class controller {
    private val inputView = InputView()
    private val outputView = OutputView()
    private val errorView = ErrorView()
    private var errorTrigger = false
    fun run() {
        val visitDate = validVisitDate()
        val orderString = validOrderString()
        if (errorTrigger) return

        val order = order(orderString)
        if (order.getOrderErrorTrigger()) return
        printOrders(orderString,visitDate,order)

        val discount = discount(order,visitDate)
        printDiscounts(order,discount)

    }
    private fun printOrders(orderString: String, visitDate: Int, order: order) {
        outputView.printDate(visitDate)
        outputView.printOrder(order)
        outputView.printTotalPrice(order)
        outputView.printFreebie(order)
    }
    private fun printDiscounts(order: order, discount: discount) {
        outputView.printDiscount(discount)
        outputView.printTotalDiscount(discount)
        outputView.printAfterDCprice(order,discount)
        outputView.printBadge(discount)
    }

    private fun validVisitDate(): Int {
        return try {
            val visitDate = Integer.parseInt(inputView.readDate())
            if (visitDate < 1 || visitDate > 31) {
                errorView.notValidDate()
                errorTrigger = true
            }
            visitDate
        } catch (e: Exception) {
            errorView.notValidDate()
            errorTrigger = true
            0
        }
    }
    private fun validOrderString(): String {
        return try {
            val menuString = inputView.readMenu()
            val regex = "^([\\p{L}\\d]+-\\d+,)*[\\p{L}\\d]+-\\d+$"
            val menuStringT = menuString.trim()
            val isValidMenu = menuStringT.matches(Regex(regex))
            if (!isValidMenu) {
                errorView.notValidOrder()
                errorTrigger = true
            }
            menuStringT
        } catch (e: Exception) {
            errorView.notValidOrder()
            errorTrigger = true
            ""
        }
    }
}