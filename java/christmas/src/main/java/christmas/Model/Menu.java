package christmas.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Menu {
    private final Map<String, Map<String, Integer>> data;

    public Menu() {
        this.data = new HashMap<>();
        initializeData();
    }

    private void initializeData() {
        Map<String, Integer> appetizerMenu = new HashMap<>();
        appetizerMenu.put("양송이수프", 6000);
        appetizerMenu.put("타파스", 5500);
        appetizerMenu.put("시저샐러드", 8000);

        Map<String, Integer> mainMenu = new HashMap<>();
        mainMenu.put("티본스테이크", 55000);
        mainMenu.put("바비큐립", 54000);
        mainMenu.put("해산물파스타", 35000);
        mainMenu.put("크리스마스파스타", 25000);

        Map<String, Integer> dessertMenu = new HashMap<>();
        dessertMenu.put("초코케이크", 15000);
        dessertMenu.put("아이스크림", 5000);

        Map<String, Integer> beverageMenu = new HashMap<>();
        beverageMenu.put("제로콜라", 3000);
        beverageMenu.put("레드와인", 60000);
        beverageMenu.put("샴페인", 25000);

        data.put("애피타이저", appetizerMenu);
        data.put("메인", mainMenu);
        data.put("디저트", dessertMenu);
        data.put("음료", beverageMenu);
    }

    public String[] extractMenuNames() {
        return data.values().stream()
                .flatMap(categoryMenu -> categoryMenu.keySet().stream())
                .toArray(String[]::new);
    }

    public String findCategory(String menuName) {
        for (Map.Entry<String, Map<String, Integer>> entry : data.entrySet()) {
            if (entry.getValue().containsKey(menuName)) {
                return entry.getKey();
            }
        }
        System.out.println("메뉴 \"" + menuName + "\"의 카테고리를 찾을 수 없습니다.");
        return null;
    }

    public int getMenuPrice(String menuName) {
        String category = findCategory(menuName);
        if (category != null) {
            return data.get(category).get(menuName);
        }
        return 0;
    }

    public boolean isInCategory(String menuName, String category) {
        String menuCategory = findCategory(menuName);
        return menuCategory != null && menuCategory.equals(category);
    }

    public List<String> getMenuNamesByCategory(String category) {
        if (data.containsKey(category)) {
            return data.get(category).keySet().stream().collect(Collectors.toList());
        } else {
            System.out.println("카테고리 \"" + category + "\"를 찾을 수 없습니다.");
            return List.of(); // Return an empty list if the category is not found
        }
    }
}