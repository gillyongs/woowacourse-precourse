package christmas.Model;

import java.util.HashMap;
import java.util.Map;

public class Discount {
    private int visitDate;
    private Order order;
    private String day;
    private int DESSERT_DISCOUNT = 2023;
    private int MAIN_DISCOUNT = 2023;
    private Map<String, Integer> discounts;
    private int totalDiscount;
    private String badge;
    public Discount(Order order, int visitDate){
        this.visitDate = visitDate;
        this.order = order;
        this.day = getDayOfWeek();
        makeDiscountObject();
        calculateTotalDiscount();
        caculateBadge();
    }

    private void caculateBadge() {
        if(totalDiscount>=20000){
            badge = "산타";
        }
        else if(totalDiscount>=10000){
            badge = "트리";
        }
        else if(totalDiscount>=5000){
            badge = "별";
        }
        else{
            badge = "없음";
        }
    }
    public String getBadge(){
        return badge;
    }

    private void calculateTotalDiscount() {
        totalDiscount = discounts.values().stream().mapToInt(Integer::intValue).sum();
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public void makeDiscountObject() {
        discounts = new HashMap<>();
        if(order.getTotalPrice()<10000){
            discounts.put("dday", 0);
            discounts.put("weekday", 0);
            discounts.put("weekend", 0);
            discounts.put("special", 0);
            discounts.put("freebie", 0);
        }
        else{
            discounts.put("dday", calculateDdayDiscount());
            discounts.put("weekday", calculateWeekDayDiscount());
            discounts.put("weekend", calculateWeekendDiscount());
            discounts.put("special", calculateSpecialDiscount());
            discounts.put("freebie", calculateFreebieDiscount());
        }
    }

    public Map<String, Integer> getDiscountObject() {
        return discounts;
    }


    public int calculateDdayDiscount() {
        if (visitDate > 25) {
            return 0;
        }
        return 900 + visitDate * 100;
    }

    public int calculateSpecialDiscount(){
        if("일".equals(day) || visitDate==25){
            return 1000;
        }
        return 0;
    }
    public int calculateFreebieDiscount() {
        if (order.getFreebie()) {
            return 25000;
        }
        return 0;
    }

    public String getDayOfWeek() {
        String[] daysOfWeek = {"목", "금", "토", "일", "월", "화", "수"};
        return daysOfWeek[visitDate % 7];
    }

    public int calculateWeekDayDiscount() {
        if ("금".equals(day) || "토".equals(day)) {
            return 0;
        }
        return DESSERT_DISCOUNT * menuCount("디저트");
    }

    public int calculateWeekendDiscount() {
        if (!"금".equals(day) && !"토".equals(day)) {
            return 0;
        }
        return MAIN_DISCOUNT * menuCount("메인");
    }

    public int menuCount(String category) {
        return order.countMenuInCategory(category);
    }

}
