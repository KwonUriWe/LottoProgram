# LottoProgram
## 자동 또는 수동으로 입력한 응모 번호를 당첨 번호와 비교하여 당첨 여부를 알려주는 프로그램.

### 화면 구현 및 상세 내용
![그림1](https://user-images.githubusercontent.com/59382990/85106087-924a0200-b246-11ea-94cd-3d9d58f9519d.png)
- lottoN  //로또 당첨 번호를 표시할 버튼 배열  
- myN  //응모할 번호를 입렬할 창 배열  
- search  //검색 버튼
- random  //응모할 번호 자동 선택할 버튼
- titleLabel  //로또 라는 프로그램 이름 띄울 라벨
- turnTxt  //회차 입력할 창
- turnLabel  //회차 정보, 경고 문구 표시할 라벨
- rankLabel  //순위 문구 표시할 라벨
- rankImg  //순위 이미지 표시할 라벨

### 테스트

####	초기 화면 확인
-	당첨 번호 표시 버튼은 00, 나머지는 공백으로 설정했던 위치에 출력된다.
- 당첨 번호 표시 버튼은 동그라미 모양으로 1~6은 검정색 바탕에 흰색 글씨, 보너스 번호는 파란색 바탕에 노란색 글씨로 출력된다.
![01](https://user-images.githubusercontent.com/59382990/83210289-6ed6ee80-a195-11ea-8ab2-8eba92c24411.png)

####	랜덤 버튼 클릭 시 응모 번호가 잘 출력 되는지 확인
-	1~45 사이의 중복되지 않는 숫자들이 응모 번호 입력창에 출력된다.
![02랜덤](https://user-images.githubusercontent.com/59382990/83210304-77c7c000-a195-11ea-8694-ce1ae931c395.png)

####	결과값이 바르게 불러와 지는지 확인
-	ENTER키 또는 돋보기(search) 버튼을 누르면 결과값이 출력된다.
-	회차 번호에 대한 당첨 번호들이 순서에 맞게 출력된다. 
![json](https://user-images.githubusercontent.com/59382990/83210499-fc1a4300-a195-11ea-9bf7-9f019b7e4f70.png)
![03](https://user-images.githubusercontent.com/59382990/83210514-05a3ab00-a196-11ea-8eaf-e6ba3487d4ac.png)

#### 오류 검출 여부 확인
- url 접속에 실패할 경우 콘솔창에 접속 실패라 출력된다
![041](https://user-images.githubusercontent.com/59382990/83210541-1e13c580-a196-11ea-9b47-ee767f45702e.png)

- 응모 번호 입력란에 공백 혹은 문자 입력 시 경고 문구가 출력된다.
![051](https://user-images.githubusercontent.com/59382990/83210575-3388ef80-a196-11ea-824a-d5076a26c012.png)
![052](https://user-images.githubusercontent.com/59382990/83210571-31bf2c00-a196-11ea-88eb-578f247bb853.png)

-	회차 번호 입력란에 공백 혹은 문자 입력 시 경고 문구가 출력된다.
![061](https://user-images.githubusercontent.com/59382990/83210588-3b489400-a196-11ea-8736-9d8060878322.png)
![062](https://user-images.githubusercontent.com/59382990/83210590-3c79c100-a196-11ea-9af2-6bdd2d331332.png)

-	입력된 응모 번호가 1 이상 45 이하의 숫자가 아닐 경우 경고 문구가 출력된다
![07](https://user-images.githubusercontent.com/59382990/83210600-426fa200-a196-11ea-976a-b19c38aa90e2.png)

-	입력된 응모 번호 중 중복되는 값이 있을 경우 경고 문구가 출력된다
![08](https://user-images.githubusercontent.com/59382990/83210605-456a9280-a196-11ea-8aa2-e685edce27c3.png)

-	당첨 번호 값이 없는 회차 번호 입력 시 경고 문구가 출력된다
![09](https://user-images.githubusercontent.com/59382990/83210615-4bf90a00-a196-11ea-8294-90f2d8d41eb2.png)

#### 	1등부터 5등 이하까지 등수에 따른 결과 확인
-	등수에 맞는 이미지로 random버튼이 변경됨. 
-	하단에 등수에 맞는 축하 문구가 출력됨.
-	응모 번호와 일치하는 당첨 번호가 노란색 배경에 파란색 글씨로 변경됨 
![1등](https://user-images.githubusercontent.com/59382990/83210336-8d3cea00-a195-11ea-8925-d22fc8ade5c7.png)
![2등](https://user-images.githubusercontent.com/59382990/83210338-8e6e1700-a195-11ea-9f46-942c42693703.png)
![3등](https://user-images.githubusercontent.com/59382990/83210340-8f9f4400-a195-11ea-940a-be4a2bee7c14.png)
![4등](https://user-images.githubusercontent.com/59382990/83210342-90d07100-a195-11ea-9017-0c63f1bdb221.png)
![5등](https://user-images.githubusercontent.com/59382990/83210346-929a3480-a195-11ea-8f18-4bfa5da78c11.png)
![그 외](https://user-images.githubusercontent.com/59382990/83210431-cd9c6800-a195-11ea-8c2e-470dd2b76527.png)

### 추후 개선해야 할 사항
- 한 번 결과를 확인한 후 재입력 및 확인시 rankLabel과 turnLabel 등 일부 기능들이 작동하지 않아 프로세스 종료 후 재실행을 해야하는 번거로움이 있다. 몇번이고 재확인 가능하도록 수정해야 한다.
