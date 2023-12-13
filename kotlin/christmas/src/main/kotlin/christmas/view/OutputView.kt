package christmas.view

import christmas.model.order
import christmas.model.discount
class OutputView {
    fun printDate(visitDate: Int) {
        println("12월 ${visitDate}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n")
    }

    fun printOrder(order: order) {
        println("<주문 메뉴>")
        val orderMap = order.getOrder()
        orderMap.forEach { (menuName, count) ->
            println("${menuName} ${count}개")
        }
        println()
    }

    fun printTotalPrice(order: order) {
        println("<할인 전 총주문 금액>")
        println(addCommasToNumber(order.getTotalPrice()))
        println()
    }

    private fun addCommasToNumber(number: Int): String {
        return String.format("%,d원", number)
    }

    fun printFreebie(order: order) {
        println("<증정 메뉴>")
        if (order.getFreebie()) {
            println("샴페인 1개")
        } else {
            println("없음")
        }
        println("")
    }
    fun printDiscount(discount: discount) {
        println("<혜택 내역>")
        val benefitObject = discount.getDiscountObject()

        if (benefitObject["dday"] ?: 0 > 0) {
            println("크리스마스 디데이 할인: -${addCommasToNumber(benefitObject["dday"] ?: 0)}")
        }

        if (benefitObject["weekday"] ?: 0 > 0) {
            println("평일 할인: -${addCommasToNumber(benefitObject["weekday"] ?: 0)}")
        }

        if (benefitObject["weekend"] ?: 0 > 0) {
            println("주말 할인: -${addCommasToNumber(benefitObject["weekend"] ?: 0)}")
        }

        if (benefitObject["special"] ?: 0 > 0) {
            println("특별 할인: -${addCommasToNumber(benefitObject["special"] ?: 0)}")
        }

        if (benefitObject["freebie"] ?: 0 > 0) {
            println("증정 이벤트: -${addCommasToNumber(benefitObject["freebie"] ?: 0)}")
        }

        if (benefitObject["dday"] == 0 &&
                benefitObject["weekday"] == 0 &&
                benefitObject["weekend"] == 0 &&
                benefitObject["special"] == 0 &&
                benefitObject["freebie"] == 0
        ) {
            println("없음")
        }

        println()
    }

    fun printTotalDiscount(discount: discount) {
        println("<총혜택 금액>")
        println("-${addCommasToNumber(discount.getTotalDiscount())}")
        println()
    }

    fun printAfterDCprice(order: order, discount: discount) {
        println("<할인 후 예상 결제 금액>")
        val price = order.getTotalPrice() - discount.getTotalDiscount()
        println(addCommasToNumber(price))
        println()
    }

    fun printBadge(discount: discount) {
        println("<12월 이벤트 배지>")
        println(discount.getBadge())
    }
}
