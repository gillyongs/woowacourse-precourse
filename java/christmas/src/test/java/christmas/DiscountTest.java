package christmas;
import christmas.Model.Discount;
import christmas.Model.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountTest {

    @Test
    void calculateDdayDiscount() {
        Order mockOrder = new Order("티본스테이크-2,제로콜라-1,양송이수프-1");
        Discount discount = new Discount(mockOrder, 20);

        int ddayDiscount = discount.calculateDdayDiscount();

        assertEquals(2900, ddayDiscount);
    }

    @Test
    void calculateSpecialDiscount() {
        Order mockOrder = new Order("티본스테이크-2,제로콜라-1,양송이수프-1");
        Discount discount = new Discount(mockOrder, 25);

        int specialDiscount = discount.calculateSpecialDiscount();

        assertEquals(1000, specialDiscount);
    }

    @Test
    void calculateFreebieDiscount() {
        Order mockOrder = new Order("티본스테이크-2,제로콜라-1,양송이수프-1");
        Discount discount = new Discount(mockOrder, 20);

        int freebieDiscount = discount.calculateFreebieDiscount();

        assertEquals(0, freebieDiscount);
    }

    @Test
    void calculateWeekDayDiscount() {
        Order mockOrder = new Order("티본스테이크-2,제로콜라-1,양송이수프-1");
        Discount discount = new Discount(mockOrder, 19);

        int weekDayDiscount = discount.calculateWeekDayDiscount();

        assertEquals(0, weekDayDiscount);
    }

    @Test
    void calculateWeekendDiscount() {
        Order mockOrder = new Order("티본스테이크-2,제로콜라-1,양송이수프-1");
        Discount discount = new Discount(mockOrder, 20);

        int weekendDiscount = discount.calculateWeekendDiscount();

        assertEquals(0, weekendDiscount);
    }

    @Test
    void menuCount() {
        Order mockOrder = new Order("티본스테이크-2,제로콜라-1,양송이수프-1");
        Discount discount = new Discount(mockOrder, 20);

        int dessertCount = discount.menuCount("디저트");

        assertEquals(0, dessertCount);
    }
}
