import Event from '../src/Event.js';

describe('Event 클래스', () => {
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
  test('calculateTotalPriceBeforeDC 테스트', () => {
    const event = new Event(menuMap);
    const totalPriceBeforeDC = event.calculateTotalPriceBeforeDC();
    expect(totalPriceBeforeDC).toBe(334500); 
  });

  test('freebieEvent 테스트 - 할인 대상인 경우', () => {
    const event = new Event(menuMap);
    const totalPriceBeforeDC = event.calculateTotalPriceBeforeDC();
    const isFreebieEvent = event.freebieEvent();
    expect(isFreebieEvent).toBe(true);  // 할인 대상이므로 true
  });

  test('freebieEvent 테스트 - 할인 대상이 아닌 경우', () => {
    const event = new Event(new Map());
    const totalPriceBeforeDC = event.calculateTotalPriceBeforeDC();
    const isFreebieEvent = event.freebieEvent();
    expect(isFreebieEvent).toBe(false);  // 할인 대상이 아니므로 false
  });
});
