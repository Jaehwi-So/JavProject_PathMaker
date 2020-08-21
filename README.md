# Path Maker
## 최단 일주경로 도출 어플리케이션
--------------------
### 프로젝트 기간
2020/04/15 ~ 2020/04/24
* 2020/04/15 : 아이디어 구성
* 2020/04/15 - 2020/04/17 : TSP 알고리즘 로직 완성, 로그인 로직 완성
* 2020/04/20 - 2020/04/22 : 파일 입출력 로직 완성, 장소목록 저장 기능 완성
* 2020/04/23 - 2020/04-24 : 장소목록 불러오기, 길찾기 결과 표시, 사용자 인터페이스 구현 완료. 최종 완성

### 팀원
- 소재휘 : 프로젝트 총괄, TSP 알고리즘 구현, 간이 데이터베이스 인터페이스 구현
- 강봉국 : 사용자 인터페이스 구현, UX/UI 디자인
- 김진렬 : 로그인 로직 구현, 사용자 인터페이스 구현
- 이지환 : 데이터 처리 구현
- 이대규 : 데이터 처리 구현

### 사용 프로그램
오직 Java만을 이용해 만든 프로그램
- jdk 1.8.0_251
- eclipse IDE

-------------------
- [1. 프로그램 소개](#1-프로그램-소개)
- [2. 제공하는 시스템 목록](#2-제공하는-시스템-목록)
- [3. 프로젝트 분석](#3-프로젝트-분석)
- [4. 사용 사례](#4-사용-사례)
- [5. 평가와 보완점](#5-평가와-보완점)
-------------------
## 1. 프로그램 소개

- ### Path Maker?
	* 여러 장소 경유 후 되돌아오는 경우 알고리즘을 사용하여 최단 시간이 걸리는 경로를 도출해내는 프로그램
- ### Who?
	* 출장을 나가는 회사원 / 배낭 여행을 계획하는 여행자 등 일정을 효율적으로 계획하고 관리하고 싶은 모든 사람들
- ### Why?
	* 효율적인 스케줄 관리를 통한 시간 절약과 업무 능률 상승.

--------------

## 2. 제공하는 시스템 목록
- 사용자 정의에 맞추어 장소들 간의 소요시간을 기입한 장소 목록을 PC에 파일 형태로 저장하는 간이 데이터베이스를 구축할 수 있다.

- 로그인 , 회원가입, 비밀번호 확인, 중복 확인이 가능하다.

- 초기화면 에서 새 목록 만들기를 통하여 새로운 데이터베이스를 구축하거나 불러오기를 통하여 이미 있던 데이터를 가져올 수 있다. 장소 추가, 장소 삭제를 통해 사용자 정의에 맞게 편집할 수 있다.

- 길 찾기 시작 시 사용자가 지정한 방문장소를 선택하고 출발 위치를 선택 할 수 있으며 , 또한 머물 장소에서 소요시간을 예측하여 사용자가 지정하고 이를 기반으로 한 결과를 얻을 수 있다.

---------------

## 3. 프로젝트 분석
![프로젝트 분석](/img_readme/analyze.jpg)

- TSP 순회 알고리즘 패키지
	* 최단 일주경로 도출 알고리즘으로는 TSP를 선택했다. 기본적으로 이차원 배열 형태의 인접 행렬을 기반으로 최단 일주 경로를 도출한다.   
	<img src="/img_readme/tsp_logic.jpg" width="400" height="400"></img>
	* 알고리즘의 구현 방법으로는 최고우선 탐색을 택했다. 이는 백트래킹 기법을 사용하면서 현재 노드의 가중치를 계산하여 가장 유망한 노드를 우선적으로 탐색하고 유망하지 않다고 판단되면 백트래킹 하는 방법이다.   
	<img src="/img_readme/tsp_java.jpg" width="400" height="400"></img>

- 데이터 관리 패키지
	* UI 패키지에서 받은 정보를 2차원 ArrayList로 변환
	* 파일을 통해 길찾기 목록, 저장/불러오기 로직 수행
	* 장소 추가, 삭제 로직 수행
	* 목록에서 길찾기 후보 선택, TSP 실행   
	<img src="/img_readme/data_java.jpg" width="400" height="400"></img>

- 데이터 입출력 패키지
	* ObjectStream을 통해 장소 목록 객체를 파일 형태로 저장, 불러오기 기능

- 로그인 패키지
	* 회원가입, 로그인 기능, 중복체크
	* ObjectStream을 통해 회원정보 저장

- UI 패키지
	* Java AWT 기반으로 구성. Frame을 이용하여 화면 구성

----------------

## 4. 사용 사례
<img src="/img_readme/ex_1.jpg" width="800" height="600"></img>        

<img src="/img_readme/ex_3.jpg" width="600" height="400"></img>
- 로그인 화면이다.
- 회원가입을 통해 유저등록을 할 수 있다. 이 때 아이디 중복체크를 해야 로그인이 된다.

<img src="/img_readme/ex_2.jpg" width="800" height="600"></img>
- 메인 화면이다.
- 새 목록 만들기 : 현재 목록창을 끄고 새로운 목록창을 불러온다. 
- 불러오기 : PC에 저장된 목록창 파일을 가져온다.
- 장소추가 : 현재 목록창에 새로운 장소를 추가한다. 이 때 현재 목록창에 있는 다른 장소와의 이동시간을 직접 입력해야 한다.
- 장소삭제 : 목록에서 해당되는 이름의 장소를 삭제한다.
- 길찾기 시작 : 최단 소요시간 경로를 찾는다.

<img src="/img_readme/ex_4.jpg" width="800" height="600"></img>
- 길찾기 시작을 눌렀을 때 체크목록들이다.
1. 현재 장소목록에서 지금 방문할 장소들을 체크한다.
2. 출발지를 선택한다.
3. 해당 장소에서 머물 시간을 기입한다.    

<img src="/img_readme/ex_5.jpg" width="600" height="400"></img>
- 길찾기 결과창이 출력된다.
- 경과 시간과 해당 장소들의 도착 시간, 소요 시간이 표시된다.

-------------

## 5. 평가와 보완점
- 사용자의 기호에 맞추어 자료를 구성하고 관리할 수 있는 기능, 그리고 저장과 불러오기를 통한 편의 기능 등을 통해 사용자 요구사항을 만족시켰을 것으로 봄
- 프로그램의 목적인 최단 시간 안에 모든 장소를 경유하는 최적의 경로를 도출해 내는 목적을 부합 시켰다.
- 순수 Java를 통해 구현하여 라이브러리나 Open API, DBMS를 사용하지 않았다. 장소 목록에 데이터가 많을 시 관리의 어려움이 있을 수 있다. 저장, 불러오기 기능은 데이터베이스와의 연동으로, 장소 등록 시 시간을 일일이 기입하는 점은 오픈 API를 통해 보완할 수 있겠다.






