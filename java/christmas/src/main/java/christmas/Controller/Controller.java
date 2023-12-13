package christmas.Controller;

import christmas.View.*;
import christmas.Model.*;

public class Controller {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    ErrorView errorView = new ErrorView();
    boolean errorTrigger = false;

    public void run() {
        int visitDate = validVisitDate();
        String orderString = validOrderString();
        if(errorTrigger){return;}

        Order order = new Order(orderString);
        if(order.getOrderErrorTrigger()){return;}
        printOrders(orderString,visitDate,order);

        Discount discount = new Discount(order,visitDate);
        printDiscounts(order,discount);

    }
    private void printOrders(String orderString, int visitDate, Order order) {
        OutputView.printDate(visitDate);
        OutputView.printOrder(order);
        OutputView.printTotalPrice(order);
        OutputView.printFreebie(order);

    }
    private void printDiscounts(Order order, Discount discount) {
        OutputView.printDiscount(discount);
        OutputView.printTotalDiscount(discount);
        OutputView.printAfterDCprice(order,discount);
        OutputView.printBadge(discount);
    }

    private String validOrderString() {
        try {
            String menuString = InputView.readMenu();
            String regex = "^([\\p{L}\\d]+-\\d+,)*[\\p{L}\\d]+-\\d+$";
            String menuStringT = menuString.trim();
            boolean isValidMenu = menuStringT.matches(regex);
            if (!isValidMenu) {
                errorView.not_valid_order();
                errorTrigger = true;
            }
            return menuStringT;
        } catch (Exception e) {
            errorView.not_valid_order();
            errorTrigger = true;
            return "";
        }
    }

    private int validVisitDate() {
        try {
            int visitDate = Integer.parseInt(inputView.readDate());
            if (visitDate < 1 || visitDate > 31) {
                errorView.not_valid_date();
                errorTrigger = true;
            }
            return visitDate;
        } catch (Exception e) {
            errorView.not_valid_date();
            errorTrigger = true;
            return 0;
        }
    }
}
