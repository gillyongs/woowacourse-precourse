package christmas;

import christmas.Model.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    void validOrderTest() {
        Order order = new Order("양송이수프-2,티본스테이크-1,아이스크림-3");
        assertFalse(order.getOrderErrorTrigger());
        assertEquals(82000, order.getTotalPrice());
        assertFalse(order.getFreebie());
    }

    @Test
    void invalidOrderTest() {
        Order order1 = new Order("NonExistingMenu-2");
        assertTrue(order1.getOrderErrorTrigger());

        Order order2 = new Order("양송이수프-2,디저트-1");
        assertTrue(order2.getOrderErrorTrigger());

        Order order4 = new Order("초코케이크-5,아이스크림-10");
        assertFalse(order4.getOrderErrorTrigger());
    }

    @Test
    void freebieOrderTest() {
        Order order1 = new Order("티본스테이크-1,레드와인-2");
        assertFalse(order1.getOrderErrorTrigger());
        assertTrue(order1.getFreebie());

        Order order2 = new Order("양송이수프-1,샴페인-2");
        assertFalse(order2.getOrderErrorTrigger());
        assertFalse(order2.getFreebie());

        Order order3 = new Order("타파스-1,제로콜라-2");
        assertFalse(order3.getOrderErrorTrigger());
        assertFalse(order3.getFreebie());
    }

    @Test
    void menuCountInCategoryTest() {
        Order order = new Order("양송이수프-2,티본스테이크-1,아이스크림-3");
        assertEquals(3, order.countMenuInCategory("디저트"));
        assertEquals(1, order.countMenuInCategory("메인"));
        assertEquals(2, order.countMenuInCategory("애피타이저"));
    }
}
