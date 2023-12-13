import MenuData from './menuData.js';

export default class Event{
    constructor(menuMap){
        this.menuMap = menuMap;
        this.menuData = new MenuData();
        this.totalMenuPrice=0;

    }

    calculateTotalPriceBeforeDC(){
        this.menuMap.forEach((count, menuName) => {
            const menuPrice = this.menuData.getMenuPrice(menuName);
            this.totalMenuPrice += menuPrice * count;
        });
        return this.totalMenuPrice;
    }

    freebieEvent(){
        if(this.totalMenuPrice>=120000){return true;}
        return false;
    }
}