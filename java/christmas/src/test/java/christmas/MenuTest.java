package christmas;
import christmas.Model.Menu;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    @Test
    void extractMenuNames() {
        Menu menu = new Menu();
        String[] menuNames = menu.extractMenuNames();
        assertNotNull(menuNames);
        assertTrue(menuNames.length > 0);
    }

    @Test
    void findCategory() {
        Menu menu = new Menu();
        String category = menu.findCategory("티본스테이크");
        assertNotNull(category);
        assertEquals("메인", category);

        // Test for a menu that doesn't exist
        String nonExistingCategory = menu.findCategory("NonExistingMenu");
        assertNull(nonExistingCategory);
    }

    @Test
    void getMenuPrice() {
        Menu menu = new Menu();
        int price = menu.getMenuPrice("아이스크림");
        assertEquals(5000, price);

        // Test for a menu that doesn't exist
        int nonExistingPrice = menu.getMenuPrice("NonExistingMenu");
        assertEquals(0, nonExistingPrice);
    }

    @Test
    void isInCategory() {
        Menu menu = new Menu();
        assertTrue(menu.isInCategory("아이스크림", "디저트"));
        assertFalse(menu.isInCategory("티본스테이크", "디저트"));
    }

    @Test
    void getMenuNamesByCategory() {
        Menu menu = new Menu();
        List<String> dessertMenuNames = menu.getMenuNamesByCategory("디저트");
        assertNotNull(dessertMenuNames);
        assertTrue(dessertMenuNames.size() > 0);
    }
}