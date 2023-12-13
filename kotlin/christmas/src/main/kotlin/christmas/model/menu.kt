package christmas.model

class menu {
    private val data: MutableMap<String, MutableMap<String, Int>> = HashMap()

    init {
        initializeData()
    }

    private fun initializeData() {
        val appetizerMenu = hashMapOf(
                "양송이수프" to 6000,
                "타파스" to 5500,
                "시저샐러드" to 8000
        )

        val mainMenu = hashMapOf(
                "티본스테이크" to 55000,
                "바비큐립" to 54000,
                "해산물파스타" to 35000,
                "크리스마스파스타" to 25000
        )

        val dessertMenu = hashMapOf(
                "초코케이크" to 15000,
                "아이스크림" to 5000
        )

        val beverageMenu = hashMapOf(
                "제로콜라" to 3000,
                "레드와인" to 60000,
                "샴페인" to 25000
        )

        data["애피타이저"] = appetizerMenu
        data["메인"] = mainMenu
        data["디저트"] = dessertMenu
        data["음료"] = beverageMenu
    }

    fun extractMenuNames(): Array<String> {
        return data.values
                .flatMap { categoryMenu -> categoryMenu.keys }
                .toTypedArray()
    }

    fun findCategory(menuName: String): String? {
        for ((category, categoryMenu) in data) {
            if (categoryMenu.containsKey(menuName)) {
                return category
            }
        }
        println("메뉴 \"$menuName\"의 카테고리를 찾을 수 없습니다.")
        return null
    }

    fun getMenuPrice(menuName: String): Int {
        val category = findCategory(menuName)
        return category?.let { data[it]?.get(menuName) } ?: 0
    }

    fun isInCategory(menuName: String, category: String): Boolean {
        val menuCategory = findCategory(menuName)
        return menuCategory != null && menuCategory == category
    }

    fun getMenuNamesByCategory(category: String): List<String> {
        return data[category]?.keys?.toList() ?: run {
            println("카테고리 \"$category\"를 찾을 수 없습니다.")
            emptyList()
        }
    }
}
