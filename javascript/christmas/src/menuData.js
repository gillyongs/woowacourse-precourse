import OutputView from "./OutputView.js";
export default class MenuData {
    constructor() {
      this.data = {
        애피타이저: {
          '양송이수프': 6000,
          '타파스': 5500,
          '시저샐러드': 8000,
        },
        메인: {
          '티본스테이크': 55000,
          '바비큐립': 54000,
          '해산물파스타': 35000,
          '크리스마스파스타': 25000,
        },
        디저트: {
          '초코케이크': 15000,
          '아이스크림': 5000,
        },
        음료: {
          '제로콜라': 3000,
          '레드와인': 60000,
          '샴페인': 25000,
        },
      };
    }
  
    extractMenuNames() {
      const menuNames = [];
      for (const category in this.data) {
        if (Object.prototype.hasOwnProperty.call(this.data, category)) {
          const categoryMenu = this.data[category];
          const categoryMenuNames = Object.keys(categoryMenu);
          menuNames.push(...categoryMenuNames);
        }
      }
      return menuNames;
    }

    findCategory(menuName) {
        for (const category in this.data) {
          if (menuName in this.data[category]) {
            return category;
          }
        }
        OutputView.printError(`메뉴 "${menuName}"의 카테고리를 찾을 수 없습니다.`)
    }

    getMenuPrice(menuName) {
        const category = this.findCategory(menuName);
        return this.data[category][menuName];
    }

    getMenuNamesByCategory(category) {
        if (!(category in this.data)) {
            OutputView.printError(`카테고리 "${category}"를 찾을 수 없습니다.`)
        }
        return Object.keys(this.data[category]);
    }
}
  