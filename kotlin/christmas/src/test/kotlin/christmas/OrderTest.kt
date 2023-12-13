package christmas

import christmas.model.order
import christmas.view.ErrorView
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class OrderTest {

    @Test
    fun `initialize with valid order string should not trigger error`() {
        val orderString = "양송이수프-2,타파스-1,티본스테이크-3"
        val order = order(orderString)
        assertFalse(order.getOrderErrorTrigger())
    }

    @Test
    fun `initialize with invalid order string should trigger error`() {
        val orderString = "양송이수프-2,햄버거-1,티본스테이크-3"
        val order = order(orderString)
        assertTrue(order.getOrderErrorTrigger())
    }

    @Test
    fun `getOrder should return the correct order map`() {
        val orderString = "양송이수프-2,타파스-1,티본스테이크-3"
        val order = order(orderString)
        val expectedOrderMap = mapOf("양송이수프" to 2, "타파스" to 1, "티본스테이크" to 3)
        assertEquals(expectedOrderMap, order.getOrder())
    }

    @Test
    fun `getTotalPrice should return the correct total price`() {
        val orderString = "양송이수프-2,타파스-1,티본스테이크-3"
        val order = order(orderString)
        assertEquals(182500, order.getTotalPrice())
    }

    @Test
    fun `getFreebie should return true when total price is greater than or equal to 120000`() {
        val orderString = "양송이수프-2,타파스-1,레드와인-3"
        val order = order(orderString)
        assertTrue(order.getFreebie())
    }

    @Test
    fun `getFreebie should return false when total price is less than 120000`() {
        val orderString = "양송이수프-2,타파스-1,제로콜라-3"
        val order = order(orderString)
        assertFalse(order.getFreebie())
    }

    @Test
    fun `countMenuInCategory should return the correct count for a given category`() {
        val orderString = "양송이수프-2,타파스-1,티본스테이크-3,아이스크림-2"
        val order = order(orderString)
        assertEquals(2, order.countMenuInCategory("디저트"))
    }

    @Test
    fun `initialize with only drinks in the order should trigger an error`() {
        val orderString = "제로콜라-2,레드와인-1"
        val order = order(orderString)
        assertFalse(order.getOrderErrorTrigger())
    }

    @Test
    fun `initialize with more than 20 items in the order should trigger an error`() {
        val orderString = "양송이수프-2,타파스-1,티본스테이크-3," +
                "초코케이크-2,샴페인-1," +
                "바비큐립-1,아이스크림-2," +
                "시저샐러드-1,해산물파스타-3," +
                "크리스마스파스타-2,레드와인-1," +
                "타파스-1,아이스크림-2," +
                "양송이수프-2,타파스-1,티본스테이크-3," +
                "초코케이크-2,샴페인-1," +
                "바비큐립-1,아이스크림-2," +
                "시저샐러드-1,해산물파스타-3," +
                "크리스마스파스타-2,레드와인-1"
        val order = order(orderString)
        assertTrue(order.getOrderErrorTrigger())
    }
}
