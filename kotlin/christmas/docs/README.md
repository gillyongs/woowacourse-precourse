
## 전체 기능 구현 체크리스트

- ✔️ 사용자에게 방문 날짜를 입력 받음
- ✔️ 날짜 유효성 검증
  - [x] try catch로 숫자 값인지 체크
  - [x] 날짜가 1보다 작거나 31보다 크면 error throw
        
- ✔️ 사용자에게 메뉴 주문을 입력 받음
- ✔️ 주문 유효성 검증
  - [x] 정규표현식을 통해 올바른 형태인지 체크
 
- ✔️ 메뉴 클래스 구현
  - [x] 문제에서 주어진 데이터를 객체 형식의 클래스 변수로 지니고있음
  - [x] 메뉴명을 나열하는 메서드 구현 -> 사용자에게 입력받은 메뉴가 존재하는 메뉴인지 확일할떄 사용
  - [x] 인자로 받은 메뉴의 카테고리를 리턴하는 메서드 구현 -> 특정 카테고리 메뉴 개수를 셀때 사용 (평일/주말할인)
  - [x] 인자로 받은 메뉴의 금액을 리턴하는 getter 구현 -> 총 금액 계산시 사용

- ✔️ 주문 클래스 구현
  - [x] 주문을 문자열로 받아 Map 형태로 변환
  - [x] 주문에 같은 메뉴가 중복해서 존재하는지 체크
  - [x] 메뉴의 개수가 잘못되지 않았는지 체크
  - [x] 메뉴가 20개 초과인지 체크
  - [x] 메뉴가 음료수로만 구성되어있는지 체크
  - [x] 총 금액을 계산해 클래스 변수로 저장, getter로 사용
  - [x] 증정 여부를 boolean 값으로 저장 + getter  
        
- ✔️ discount 객체 생성
  - [x] 주어진 날짜 값으로 D-day 할인값 계산
  - [x] 주어진 날짜 값으로 스페셜 할인 여부 체크
  - [x] 총 금액으로 증정품 증정 여부 확인
  - [x] 총 할인 금액과 이에 해당하는 뱃지를 리턴하는 메서드 구현 

- ✔️ 평일 & 주말 할인 구현 
  - [x] 주어진 날짜에 해당하는 요일을 리턴하는 메서드 구현 
  - [x] 특정 카테고리의 메뉴가 몇개 존재하는지 세는 메서드 구현
  - [x] 해당 메서드들을 활용해 평일 / 주말 할인 값 계산

- ✔️ 총 결과 출력
  - [x] dicount에서 얻은 할인 값들을 하나의 객체로 묶어 전달
  - [x] 총 할인 금액, 할인 후 계산 금액, 뱃지 값을 인자로 받아 출력
  - [x] int로 받은 금액을 세자리수마다 쉼표를 더해 출력하는 함수 구현 
        

- ✔️ 주문 객체에 대한 단위 테스트 추가
- ✔️ 할인 객체에 대한 단위 테스트 추가
- ✔️ 메뉴 객체에 대한 단위 테스트 추가
<br />

## MVC 관점 분리

#### 1) Model (비즈니스 로직)
- Menu : 문제에서 주어진 데이터와 이에 대한 getter, setter로 이루어진 클래스
- Order : 사용자의 주문을 map 형태로 변환, 유효성 체크, 총 금액 + 증정품 여부 getter
- Discount : 방문 날짜, 주문 금액, 총 금액을 인자로 받아 각 할인 금액을 계산, 하나의 객체로 묶어 리턴    
  <br>

#### 2) View (입출력)
- Input: 주어진 Console로 날짜와 주문을 입력받음
- Output: 할인 내역과 금액들을 출력함

#### 3) Controller (게임 진행)
- View를 통해 사용자에게 날짜와 주문을 입력받음
- 입력값의 유효성 검증은 Controller에서 처리
 
- 주어진 입력값으로 Order 객체 생성 
- View를 통해 Order의 총 금액, 증정 여부 출력
 
- 주어진 입력값으로 Discount 객체 생성
- discount 값을 하나의 객체로 묶어 model->controller->view로 전달, OutputView로 출력
  

<br />

## 커밋메세지 컨벤션
- feat = 새로운 기능 추가
- fix = 버그 수정
- refactor = 리팩토리
- style = css, ui, design 변경
- comment = 주석
- docs = 문서 수정
- test = 테스트 관련
- rename / remove = 파일 이름 변경 / 삭제

