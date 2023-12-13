import { Console } from '@woowacourse/mission-utils';
const OutputView = {

    printDate(visitDate){
        Console.print(`12월 ${visitDate}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n`);
    },

    printMenu(menuMap) {
        Console.print("<주문 메뉴>");
        menuMap.forEach((count, menuName) => {
            Console.print(`${menuName} ${count}개`);
        });
        Console.print("");
    },

    printTotalPriceBeforDC(totalPriceBeforeDC){
        Console.print("<할인 전 총주문 금액>");
        Console.print(addCommasToNumber(totalPriceBeforeDC));
        Console.print("");
    },

    printFreebie(freebie){
        Console.print("<증정 메뉴>");
        if(freebie){
            Console.print("샴페인 1개");
        }
        else{
            Console.print("없음");
        }
        Console.print("");
    },

    printDiscount(benefitObject){
        Console.print("<혜택 내역>");
        if(benefitObject.dday) {
            Console.print(`크리스마스 디데이 할인: -${addCommasToNumber(benefitObject.dday)}`);
        }
      
        if(benefitObject.weekday) {
            Console.print(`평일 할인: -${addCommasToNumber(benefitObject.weekday)}`);
        }

        if(benefitObject.weekend) {
            Console.print(`주말 할인: -${addCommasToNumber(benefitObject.weekend)}`);
        }
      
        if(benefitObject.special) {
            Console.print(`특별 할인: -${addCommasToNumber(benefitObject.special)}`);
        }
      
        if(benefitObject.freebie) {
            Console.print(`증정 이벤트: -${addCommasToNumber(benefitObject.freebie)}`);
        }
        if(!benefitObject.dday && !benefitObject.weekday && !benefitObject.weekend && !benefitObject.special && !benefitObject.freebie){
            Console.print("없음");
        }
        Console.print("");
    },

    printTotalDiscount(totalDiscount){
        Console.print("<총혜택 금액>");
        Console.print("-"+addCommasToNumber(totalDiscount));
        Console.print("");
    },

    printFinalPrice(finalPrice){
        Console.print("<할인 후 예상 결제 금액>");
        Console.print(addCommasToNumber(finalPrice));
        Console.print("");
    },

    printBadge(badge){
        Console.print("<12월 이벤트 배지>");
        Console.print(badge);
    },

    printError(errorMessage) {
        Console.print(`[ERROR] ${errorMessage}`)
    },

}


function addCommasToNumber(number) {
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+"원";
}

export default OutputView;