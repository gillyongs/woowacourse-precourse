
import Discount from '../src/Discount.js';

describe('Discount class', () => {
    // Create a sample menuMap
    const menuMap = new Map([
      ['양송이수프', 2],
      ['타파스', 1],
      ['시저샐러드', 1],
      ['티본스테이크', 1],
      ['바비큐립', 2],
      ['해산물파스타', 1],
      ['초코케이크', 3],
      ['제로콜라', 2],
      ['레드와인', 1],
    ]);
    const totalPrice = 334500;
  
    test('calculateDdayDiscount', () => {
      const discount = new Discount(10, new Map(), 10000);
      expect(discount.calculateDdayDiscount()).toBe(1900);
    });
  
    test('calculateSpecialDiscount on Starday', () => {
      const discount = new Discount(17, new Map(), 10000);
      expect(discount.calculateSpecialDiscount()).toBe(1000);
    });
    test('calculateSpecialDiscount not on Starday', () => {
      const discount = new Discount(18, new Map(), 10000);
      expect(discount.calculateSpecialDiscount()).toBe(0);
    });
  
    test('calculateFreebieDiscount', () => {
      const discount = new Discount(15, new Map(), 130000);
      expect(discount.calculateFreebieDiscount()).toBe(25000);
    });
    test('not calculateFreebieDiscount', () => {
        const discount = new Discount(15, new Map(), 13000);
        expect(discount.calculateFreebieDiscount()).toBe(0);
    });

    test('caculate WeekendDiscount', () => {
        const discount = new Discount(16, menuMap, totalPrice);
        expect(discount.calculatWeekendDiscount()).toBe(8092);
    });
    test('caculate not WeekendDiscount', () => {
        const discount = new Discount(20, menuMap, totalPrice);
        expect(discount.calculatWeekendDiscount()).toBe(0);
    });

    test('caculate WeekdayDiscount', () => {
        const discount = new Discount(5, menuMap, totalPrice);
        expect(discount.calculatWeekDayDiscount()).toBe(6069);
    });
    test('caculate not WeekdayDiscount', () => {
        const discount = new Discount(9, menuMap, totalPrice);
        expect(discount.calculatWeekDayDiscount()).toBe(0);
    });

    test('return total discount object', () => {
        const discount = new Discount(20, menuMap, totalPrice);
        expect(discount.returnDiscountObject()).toEqual({
            dday: 2900,  // (900 + 10 * 20)
            weekday: 6069,  // 평일 * 디저트 3개 
            weekend: 0,  // 주말 아님
            special: 0,  // 특별 할인 없음
            freebie: 25000,  // 증정 이벤트 있음
          });
    });

    test('calculate total discount', () => {
        const discount = new Discount(20, menuMap, totalPrice);
        expect(discount.calculateTotalDiscount()).toBe(33969);
    });

    test('no badge', () => {
        const discount = new Discount(20, new Map(), 0);
        const totalDiscount = discount.calculateTotalDiscount();//총 할인 금액을 계산해야 배지가 생김
        expect(discount.returnBadge()).toBe("없음");
    });
    test('yes badge', () => {
        const discount = new Discount(20, menuMap, totalPrice);
        const totalDiscount = discount.calculateTotalDiscount();
        expect(discount.returnBadge()).toBe("산타");
    });
  
  });
  