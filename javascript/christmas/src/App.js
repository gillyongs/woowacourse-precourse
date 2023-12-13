import InputView from './InputView.js';
import OutputView from './OutputView.js';
import MenuData from './menuData.js';
import Event from './Event.js';
import Discount from './Discount.js';
let ERROR_TRIGGER = false;
class App {
  async run() {

    const visitDate =  await this.validVisitDate();
    const menuMap = await this.menuInput();
    if(ERROR_TRIGGER){return;}
    OutputView.printDate(visitDate);
    OutputView.printMenu(menuMap);

    this.calculateResult(visitDate, menuMap);

  }

  async menuInput(){
    const menuString = await this.validMenuString();
    const menuDataInstance = new MenuData();
    const menuMap = this.validMenuMap(menuString, menuDataInstance);
    return menuMap;
  }

  calculateResult(visitDate, menuMap){
    const eventInstance = new Event(menuMap);
    const totalPriceBeforeDC = eventInstance.calculateTotalPriceBeforeDC();
    OutputView.printTotalPriceBeforDC(totalPriceBeforeDC);

    const freebie = eventInstance.freebieEvent();
    OutputView.printFreebie(freebie);

    const discountInstance = new Discount(visitDate,menuMap,totalPriceBeforeDC);
    OutputView.printDiscount(discountInstance.returnDiscountObject());

    const totalDiscount = discountInstance.calculateTotalDiscount();
    OutputView.printTotalDiscount(totalDiscount);

    const finalPrice = totalPriceBeforeDC - totalDiscount;
    OutputView.printFinalPrice(finalPrice);

    const badge = discountInstance.returnBadge();
    OutputView.printBadge(badge);
  }

  

  async validVisitDate(){
    const visitDate = await InputView.readDate();
    if (isNaN(visitDate) || visitDate < 1 || visitDate > 31) {
      OutputView.printError("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
      ERROR_TRIGGER = 1;
    }
    return visitDate;
  }

  async validMenuString(){
    const menuString = await InputView.readMenu();
    const regex = /^([\p{L}\d]+-\d+,)*[\p{L}\d]+-\d+$/u;  //그냥 w 쓰면 한글 인식 못함
    const menuStringT = menuString.trim();
    const isValidMenu = regex.test(menuStringT);
    if(!isValidMenu){
      OutputView.printError("유효하지 않은 주문입니다. 다시 입력해 주세요.");
      ERROR_TRIGGER = 1;
    }
    return menuStringT;
  }

  validMenuMap(menuString, menuDataInstance) {
    const menuArray = menuString.split(',');
    const menuNames = menuDataInstance.extractMenuNames();
    const menuMap = new Map();
    const counts = { countSum: 0, drinkCount: 0 };

    menuArray.forEach(menu => {
      this.processMenu(menu, menuMap, menuNames, menuDataInstance, counts);
    });

    this.validateMenuCounts(counts);

    return menuMap;
  }

  processMenu(menu, menuMap, menuNames, menuDataInstance, counts) {
    const [menuName, menuCountStr] = menu.split('-');
    const menuCount = parseInt(menuCountStr, 10);
    counts.countSum += menuCount;

    if (menuDataInstance.findCategory(menuName) === "음료") {
      counts.drinkCount += menuCount;
    }

    if (isInvalidOrder(menuMap, menuNames, menuName, menuCount)) {
      this.printOrderError()
    }

    menuMap.set(menuName, menuCount);
  }

  validateMenuCounts(counts) {
    if (counts.countSum > 20 || counts.drinkCount === counts.countSum) {
      this.printOrderError()
    }
  }

  printOrderError() {
    OutputView.printError("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    ERROR_TRIGGER = 1;
  }

}

function isInvalidOrder(menuMap, menuNames, menuName, menuCount) {
  return menuMap.has(menuName) || !menuNames.includes(menuName) || menuCount <= 0;
}

export default App;
