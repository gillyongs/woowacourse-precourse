package christmas
import christmas.model.menu
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class MenuTest {

    @Test
    fun `extractMenuNames should return all menu names`() {
        val menu = menu()
        val menuNames = menu.extractMenuNames()

        assertNotNull(menuNames)
        assertTrue(menuNames.isNotEmpty())
    }

    @Test
    fun `findCategory existing menu should return category`() {
        val menu = menu()
        val category = menu.findCategory("티본스테이크")

        assertNotNull(category)
        assertEquals("메인", category)
    }

    @Test
    fun `findCategory non-existing menu should return null`() {
        val menu = menu()
        val category = menu.findCategory("치킨")

        assertNull(category)
    }

    @Test
    fun `getMenuPrice existing menu should return price`() {
        val menu = menu()
        val price = menu.getMenuPrice("제로콜라")

        assertTrue(price > 0)
    }

    @Test
    fun `getMenuPrice non-existing menu should return zero`() {
        val menu = menu()
        val price = menu.getMenuPrice("햄버거")

        assertEquals(0, price)
    }

    @Test
    fun `isInCategory menu in category should return true`() {
        val menu = menu()
        val isInCategory = menu.isInCategory("초코케이크", "디저트")

        assertTrue(isInCategory)
    }

    @Test
    fun `isInCategory menu not in category should return false`() {
        val menu = menu()
        val isInCategory = menu.isInCategory("레드와인", "메인")

        assertFalse(isInCategory)
    }

    @Test
    fun `getMenuNamesByCategory existing category should return menu names`() {
        val menu = menu()
        val category = "애피타이저"
        val menuNames = menu.getMenuNamesByCategory(category)

        assertNotNull(menuNames)
        assertTrue(menuNames.isNotEmpty())
    }

    @Test
    fun `getMenuNamesByCategory non-existing category should return empty list`() {
        val menu = menu()
        val category = "스낵"
        val menuNames = menu.getMenuNamesByCategory(category)

        assertNotNull(menuNames)
        assertTrue(menuNames.isEmpty())
    }
}
