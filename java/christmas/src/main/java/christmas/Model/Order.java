package christmas.Model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import christmas.View.ErrorView;

public class Order {
    Menu menu = new Menu();
    ErrorView errorView = new ErrorView();
    Map<String, Integer> orderMap = new HashMap<>();
    private int totalPrice;
    private boolean freebie;
    private int menuNums=0;
    private boolean orderErrorTrigger = false;

    public Order(String orderString){
        intializeStringtoMap(orderString);
        validateOrder();
        calculateTotalPrice();
        freebieCheck();
    }

    private void intializeStringtoMap(String orderString) {
        List<String> orderArray = Arrays.asList(orderString.split(","));
        List<String> menuNames = Arrays.asList(menu.extractMenuNames());

        orderArray.forEach(order -> {
            String[] parts = order.split("-");
            String menuName = parts[0];
            int menuCount = Integer.parseInt(parts[1]);
            menuNums += menuCount;
            if (isInvalidOrder(orderMap, menuNames, menuName, menuCount)) {
                errorView.not_valid_order();
                orderErrorTrigger = true;
            }

            orderMap.put(menuName, menuCount);
        });
    }

    private boolean isInvalidOrder(Map<String, Integer> menuMap, List<String> menuNames, String menuName, int menuCount) {
        return menuMap.containsKey(menuName) || !menuNames.contains(menuName) || menuCount <= 0;
    }

    private void validateOrder() {
        if (orderMap.isEmpty()) {
            errorView.not_valid_order();
            orderErrorTrigger = true;
        }

        long drinkCount = orderMap.keySet().stream()
                .filter(menuName -> menu.getMenuNamesByCategory("음료").contains(menuName))
                .count();
        if (drinkCount == menuNums) {
            errorView.not_valid_order();
            orderErrorTrigger = true;
        }

        if (menuNums > 20) {
            errorView.not_valid_order();
            orderErrorTrigger = true;
        }
    }
    public Map<String, Integer> getOrder() {
        return orderMap;
    }
    public void calculateTotalPrice() {
        for (Map.Entry<String, Integer> entry : orderMap.entrySet()) {
            String menuName = entry.getKey();
            int menuCount = entry.getValue();
            int menuPrice = menu.getMenuPrice(menuName);
            totalPrice += menuCount * menuPrice;
        }
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void freebieCheck(){
        if(totalPrice>=120000){
            freebie =  true;
        }
        else{
            freebie = false;
        }
    }

    public boolean getFreebie(){
        return freebie;
    }

    public int countMenuInCategory(String category) {
        int count = 0;
        for (Map.Entry<String, Integer> entry : orderMap.entrySet()) {
            String menuName = entry.getKey();
            if (menu.isInCategory(menuName, category)) {
                count += entry.getValue();
            }
        }
        return count;
    }

    public boolean getOrderErrorTrigger() {
        return orderErrorTrigger;
    }
}
