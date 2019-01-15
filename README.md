# 회의실 예약 서비스
## 요구사항
회의실 예약


    - 30분 단위 예약
    - 반복예약 횟수 지정 가능 (해당 요일만)
    - 동일한 회의실 중첩 예약 불가능
    
## 프로젝트 구성
- Java 8
- Spring Boot 2.1.1
- H2
- Gradle
- Freemarker
- bootstrap : bootAdmin ( https://bootadmin.net/ )

## 개발환경 및 사용 툴

- Intellij
- Java 8
- Gradle
- Window 10 64bit / Mac OS
- git
- SourceTree
- Insomnia REST Client

## 프로젝트 실행 방법

> 프로젝트를 Clone 받는다.

![Alt text](/src/main/resources/static/image/step_1.PNG)

> 루트 폴더에서 프로젝트를 Build 한다.

<pre>
> ./gradlew clean
> ./gradlew build
</pre>

권한 문제가 있다면 gradlew 에 chmod 로 권한을 주고 실행한다.

> jar 파일을 실행시킨다.
<pre>
> cd build/libs/
> java -jar demo-0.0.1-SNAPSHOT.jar
</pre>

> 아래 주소로 접속한다.
<pre>
localhost:8080/view
</pre>

> UI 에서 회의실을 등록한다.

![Alt text](/src/main/resources/static/image/step_2.PNG)

> 예약할 날짜를 선택한다.

![Alt text](/src/main/resources/static/image/step_3.PNG)


## 기본 정책

반복예약의 경우 최대 4회까지만 가능하다.

예약은 오늘 날짜로부터 60일 후 까지만 가능하다.

정책에 관련된 내용은 properties 로 관리한다.

## 문제 해결
> 예약 상태는 true/false 로만 표현할 수 있다.

하루 24시간 30분 단위 예약이면 00:00\~00:30 부터 23:30\~24:00 까지 총 48개의 예약 플래그가 필요하다.

단순히 예약을 했다 안했다의 판단값만 저장하면 되기 때문에 true/false 표현만 가능하다면....OK

또한 예약 여부에 대해 판단하기에도 bit 연산을 이용하면 쉽기 때문에 bit 연산을 사용하기로 한다.

> Long 은 64 bit 이므로 48 bit 를 표현할 수 있다 

예약 상태는 default 로 0 이다.

만약 00:00 ~ 00:30 을 예약했다면 기존 예약 상태에 1을 더하면 된다

만약 좀더 복잡하게 01:00 ~ 02:00 까지 예약을 한다면 이를 binary 로 표현하면 아래와 같다.

<pre>
00:00~00:30 | 2진수 첫번째 자리 | 0
00:30~01:00 | 2진수 두번째 자리 | 0
01:00~01:30 | 2진수 세번째 자리 | 1
01:30~02:00 | 2진수 네번째 자리 | 1

1100
</pre>

기존에 예약에 대해서는 bit 의 and 연산을 통해 다음과 같이 계산한다.

<pre>
00:00~00:30 이 이미 예약되어 있다면 ?

기존 예약되어 있는 시간에 대한 2진수
0001

예약을 원하는 시간에 대한 2진수
1100

  0001
& 1100
-------
  0000
</pre>

따라서 예약 가능여부를 판단할 때 bit 연산을 통해 0이 아니면 예약이 불가능한 상태로 판단한다.

> 예약 상태를 bit array 로 표현하여 View 표현에도 장점이 있다.

일반적인 스케쥴 표와 같이 표현할 때 bit array 로 되어 있어 표현에 유리하다.

아래와 같은 화면구성을 볼 수 있다.

![Alt text](/src/main/resources/static/image/demo.PNG)

## 기타...

문제 확인 및 분석 30분 

스키마 설계 30분

깃 베이스 프로젝트 세팅 1시간

H2 DB 연동 및 CRUD 테스트 2시간 + @ 

bit 연산 로직 작성 및 테스트 2시간 + @ // 요부분 삽질 시간 좀 길었음

컨트롤러 작성하고 API 테스트 2시간 + @

View 구성 1시간 + @ // bootstrap 쓸까말까 고민하다가 기본은 좀 찜찜해서 무료 템플릿 붙임

html 및 스크립트 코드 작성 3시간 + @ // 프론트는 아직도 손이 많이간다..

마무리 테스트 및 README 정리 3시간

빡세게 했으면 버닝데이마냥 24시간 안에 얼추 구현했을듯?