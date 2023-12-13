import MenuData from './menuData.js';
const DESSERT_DISCOUNT = 2023;
const MAIN_DISCOUNT = 2023;

export default class Discount{
    constructor(visitDate,menuMap,totalPriceBeforeDC){
        this.menuMap = menuMap;
        this.visitDate = visitDate;
        this.totalPrice = totalPriceBeforeDC;
        this.menuData = new MenuData();
        this.day = this.getDayOfWeek();
        this.totalDiscount;
        this.badge;
    }

    returnDiscountObject(){
        if(this.totalPrice<10000){
            return {
                dday: 0,
                weekday: 0,
                weekend: 0,
                special: 0,  
                freebie: 0
            }
        }
        return {
            dday: this.calculateDdayDiscount(),
            weekday: this.calculatWeekDayDiscount(),
            weekend: this.calculatWeekendDiscount(),
            special: this.calculateSpecialDiscount(),  
            freebie: this.calculateFreebieDiscount()
        }
    }

    calculateTotalDiscount() {
        const discounts = this.returnDiscountObject();
        this.totalDiscount = Object.values(discounts).reduce((total, discount) => total + discount, 0);
        return this.totalDiscount;
    }

    returnBadge(){
        if(this.totalDiscount>=20000){return "산타";}
        if(this.totalDiscount>=10000){return "트리";}
        if(this.totalDiscount>=5000){return "별";}
        return "없음";
    }

    calculateDdayDiscount(){
        if(this.visitDate>25){ return 0; }
        return 900+this.visitDate*100
    }

    calculateSpecialDiscount(){
        if(this.day=='일' || this.visitDate==25){return 1000;}
        return 0;
    }

    calculateFreebieDiscount(){
        if(this.totalPrice>=120000){return 25000;}
        return 0;
    }


    getDayOfWeek() {
        const daysOfWeek = ['목', '금', '토', '일', '월', '화', '수'];
        return daysOfWeek[this.visitDate%7];
    }

    calculatWeekDayDiscount(){
        if(this.day == '금' || this.day == '토'){return 0;}
        return DESSERT_DISCOUNT * this.menuCount('디저트');
    }

    calculatWeekendDiscount(){
        if(this.day != '금' && this.day != '토'){return 0;}
        return MAIN_DISCOUNT * this.menuCount('메인');
    }

    menuCount(category) {
        let menuCount = 0;
        this.menuMap.forEach((count, menuName) => {
          if (this.isInCategory(menuName,category)) {
            menuCount += count;
          }
        });
        return menuCount;
    }

    isInCategory(menuName,category) {
        const MenuNames = this.menuData.getMenuNamesByCategory(category);
        return MenuNames.includes(menuName);
    }


}