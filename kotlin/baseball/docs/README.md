
## 전체 기능 구현 체크리스트

- ✔️ 임의의 랜덤한 숫자 생성 (3자리 숫자, 각 자릿수는 전부 다를것.)
  
- ✔️ 게임 init 및 시작 메세지 출력 -> "숫자 야구 게임을 시작합니다."
- ✔️ 사용자에게 임의의 숫자 입력 받기
  - [x] 정규표현식으로 유효한 값인지 체크
  - [x] 숫자가 3자리 숫자가 아닐 경우 or 숫자가 아닌 글자가 존재할 경우 예외처리
- ✔️ 유효값 체크 통과시, 사용자의 입력 값과 정답을 비교하여 결과 처리
  - [x] 같은 수가 없을 경우 : 낫싱
  - [x] 같은 수가 같은 자리에 있을 경우 : 스트라이크
  - [x] 같은 수가 다른 자리에 있을 경우 : 볼
- ✔️ 사용자 정답을 맞출때까지 입출력 단계 반복 진행
- ✔️ 숫자를 모두 맞췄으면 경우 게임 종료 -> 재시작 체크
- ✔️ 사용자에게 입력값 받기 (1 = 재시작, 2 = 종료)
- ✔️ 입력값 유효성 검사 진행
  - [x] 1을 입력한 경우 -> 반복
  - [x] 2를 입력한 경우 -> 트리거를 false로 변경 = while문 종료 
  - [x] 입력값이 유효하지 않을 경우 에러 throw

<br />

## MVC 관점 분리

#### 1) Model (게임 로직)
- 랜덤 숫자 생성 (generateRandomNumber)
- 사용자 입력값과 정답 숫자의 각 자릿수 비교 (checkGuess)
- 입력값이 정답인지 체크 (isGameWon)

#### 2) View (입출력)
- 주어진 라이브러리를 활용한 입출력 함수
- 주어진 출력 결과와 동일하게 입출력 내용 작성
- 게임 시작, 입력값 받기, 비교 결과 출력, 정답 여부 출력, 종료 or 재시작 여부 출력

#### 3) Controller (코드 흐름)
- 게임 초기화 및 반복
- view를 호출해 입출력 처리
- model을 호출해 입력값 검증 + 결과 처리

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
