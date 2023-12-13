package christmas
import christmas.model.discount
import christmas.model.order
import christmas.view.ErrorView
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DiscountTest {

    @Test
    fun `calculateDdayDiscount should return correct discount for D-day`() {
        val order = order("양송이수프-2,타파스-1,티본스테이크-3")
        val discount = discount(order, 25) // D-day is 25th
        assertEquals(3400, discount.calculateDdayDiscount())
    }

    @Test
    fun `calculateDdayDiscount should return 0 discount after D-day`() {
        val order = order("양송이수프-2,타파스-1,티본스테이크-3")
        val discount = discount(order, 26) // After D-day
        assertEquals(0, discount.calculateDdayDiscount())
    }

    @Test
    fun `calculateSpecialDiscount should return 1000 discount on special day`() {
        val order = order("양송이수프-2,타파스-1,티본스테이크-3")
        val discount = discount(order, 25) // Special day
        assertEquals(1000, discount.calculateSpecialDiscount())
    }

    @Test
    fun `calculateSpecialDiscount should return 0 discount on non-special day`() {
        val order = order("양송이수프-2,타파스-1,티본스테이크-3")
        val discount = discount(order, 26) // Non-special day
        assertEquals(0, discount.calculateSpecialDiscount())
    }

    @Test
    fun `calculateFreebieDiscount should return 25000 discount when freebie is true`() {
        val order = order("양송이수프-2,타파스-1,티본스테이크-3")
        val discount = discount(order, 25)
        assertTrue(discount.calculateFreebieDiscount() == 25000)
    }

    @Test
    fun `calculateFreebieDiscount should return 0 discount when freebie is false`() {
        val order = order("양송이수프-2,타파스-1,제로콜라-3")
        val discount = discount(order, 25)
        assertTrue(discount.calculateFreebieDiscount() == 0)
    }

    // Add more tests for other methods in the Discount class as needed.
}
