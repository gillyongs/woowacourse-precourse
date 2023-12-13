package christmas.model
import christmas.model.order

class discount(private val order: order, private val visitDate: Int) {
    private val day: String = getDayOfWeek()
    private val DESSERT_DISCOUNT: Int = 2023
    private val MAIN_DISCOUNT: Int = 2023
    private var discounts: MutableMap<String, Int> = mutableMapOf()
    private var totalDiscount: Int = 0
    private var badge: String = ""

    init {
        makeDiscountObject()
        calculateTotalDiscount()
        calculateBadge()
    }

    fun getBadge(): String {
        return badge
    }

    fun calculateBadge() {
        badge = when {
            totalDiscount >= 20000 -> "산타"
            totalDiscount >= 10000 -> "트리"
            totalDiscount >= 5000 -> "별"
            else -> "없음"
        }
    }

    fun getTotalDiscount(): Int {
        return totalDiscount
    }

    private fun calculateTotalDiscount() {
        totalDiscount = discounts.values.sum()
    }

    private fun makeDiscountObject() {
        discounts = if (order.getTotalPrice() < 10000) {
            mutableMapOf(
                    "dday" to 0,
                    "weekday" to 0,
                    "weekend" to 0,
                    "special" to 0,
                    "freebie" to 0
            )
        } else {
            mutableMapOf(
                    "dday" to calculateDdayDiscount(),
                    "weekday" to calculateWeekDayDiscount(),
                    "weekend" to calculateWeekendDiscount(),
                    "special" to calculateSpecialDiscount(),
                    "freebie" to calculateFreebieDiscount()
            )
        }
    }

    fun getDiscountObject(): Map<String, Int> {
        return discounts.toMap()
    }

    fun calculateDdayDiscount(): Int {
        return if (visitDate > 25) {
            0
        } else {
            900 + visitDate * 100
        }
    }

    fun calculateSpecialDiscount(): Int {
        return if ("일" == day || visitDate == 25) {
            1000
        } else {
            0
        }
    }

    fun calculateFreebieDiscount(): Int {
        return if (order.getFreebie()) {
            25000
        } else {
            0
        }
    }

    private fun getDayOfWeek(): String {
        val daysOfWeek = arrayOf("목", "금", "토", "일", "월", "화", "수")
        return daysOfWeek[visitDate % 7]
    }

    private fun calculateWeekDayDiscount(): Int {
        return if ("금" == day || "토" == day) {
            0
        } else {
            DESSERT_DISCOUNT * menuCount("디저트")
        }
    }

    private fun calculateWeekendDiscount(): Int {
        return if ("금" != day && "토" != day) {
            0
        } else {
            MAIN_DISCOUNT * menuCount("메인")
        }
    }

    private fun menuCount(category: String): Int {
        return order.countMenuInCategory(category)
    }
}
