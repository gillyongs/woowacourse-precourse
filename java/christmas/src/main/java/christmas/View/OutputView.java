package christmas.View;

import christmas.Model.Discount;
import christmas.Model.Order;

import java.util.Map;

public class OutputView {
    public static void printDate(int visitDate) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n\n", visitDate);
    }

    public static void printOrder(Order order) {
        System.out.println("<주문 메뉴>");
        Map<String, Integer> orderMap = order.getOrder();
        orderMap.forEach((menuName, count) -> {
            System.out.printf("%s %d개%n", menuName, count);
        });
        System.out.println();
    }

    public static void printTotalPrice(Order order) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(addCommasToNumber(order.getTotalPrice()));
        System.out.println();
    }

    private static String addCommasToNumber(int number) {
        return String.format("%,d원", number);
    }

    public static void printFreebie(Order order) {
        System.out.println("<증정 메뉴>");
        if(order.getFreebie()){
            System.out.println("샴페인 1개");
        }
        else{
            System.out.println("없음");
        }
        System.out.println("");
    }

    public static void printDiscount(Discount discount) {
        System.out.println("<혜택 내역>");
        Map<String, Integer> benefitObject = discount.getDiscountObject();
        if (benefitObject.get("dday") > 0) {
            System.out.println("크리스마스 디데이 할인: -" + addCommasToNumber(benefitObject.get("dday")));
        }

        if (benefitObject.get("weekday") > 0) {
            System.out.println("평일 할인: -" + addCommasToNumber(benefitObject.get("weekday")));
        }

        if (benefitObject.get("weekend") > 0) {
            System.out.println("주말 할인: -" + addCommasToNumber(benefitObject.get("weekend")));
        }

        if (benefitObject.get("special") > 0) {
            System.out.println("특별 할인: -" + addCommasToNumber(benefitObject.get("special")));
        }

        if (benefitObject.get("freebie") > 0) {
            System.out.println("증정 이벤트: -" + addCommasToNumber(benefitObject.get("freebie")));
        }

        if (benefitObject.get("dday") == 0 && benefitObject.get("weekday") == 0 &&
                benefitObject.get("weekend") == 0 && benefitObject.get("special") == 0 &&
                benefitObject.get("freebie") == 0) {
            System.out.println("없음");
        }

        System.out.println("");

    }

    public static void printTotalDiscount(Discount discount) {
        System.out.println("<총혜택 금액>");
        System.out.println("-"+addCommasToNumber(discount.getTotalDiscount()));
        System.out.println();
    }

    public static void printAfterDCprice(Order order, Discount discount) {
        System.out.println("<할인 후 예상 결제 금액>");
        int price = order.getTotalPrice() - discount.getTotalDiscount();
        System.out.println(addCommasToNumber(price));
        System.out.println();
    }

    public static void printBadge(Discount discount) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(discount.getBadge());
    }
}
