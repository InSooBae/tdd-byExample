# tdd-byExample
Test-Driven Development By Example

## Dollar 예제 (설계 부분)

<div>
  <img src = "https://user-images.githubusercontent.com/48831854/235196253-a6d7385c-6940-4cbc-9eaa-3fcb4ebf7c25.png" width="33%" height="33%">
  <img src = "https://user-images.githubusercontent.com/48831854/235208515-6a59c6ce-2cf2-412b-b846-920c5615a319.png" width="33%" height="33%">
  <img src = "https://user-images.githubusercontent.com/48831854/235208818-3d007b19-fb26-48fe-b5de-5dba22c0f649.png" width="33%" height="33%">
</div>

<br/>

위 구조로 **추상 클래스인 Expression**의 plus(), reduce(), times()를 상속받은 **Money객체 는 Money끼리의 계산**을 진행하고, **Operand 객체는 더하기나 곱하기 자체의 연산 행위**를 진행해준다.

---

<br/>

<div style="">
  <img src = "https://user-images.githubusercontent.com/48831854/235205486-bd3c1ff7-d40e-4e17-8c52-c2b8aa661b06.png" width="40%" height="40%">
  <img src = "https://user-images.githubusercontent.com/48831854/235208971-8a98a4f4-7390-44be-83b9-a1d1bf64b0c9.png" width="50%" height="50%">
</div>

<br/>

위 이미지(Operand의 reduce()메서드)처럼 **Operand는 빨간색 형식의 괄호와 + 연산을 하는 행위**를 진행해주고, **흰색인 ? 에 Money 객체가 들어가 실제 Value**에 해당한다.

<br/>

<div>
  <img src = "https://user-images.githubusercontent.com/48831854/235214642-25b5d53f-f569-4251-a3b8-c5186d9fe065.png" width="49%" height="49%">
  <img src = "https://user-images.githubusercontent.com/48831854/235209224-5f9a8d91-2dd6-4827-bebb-829389a23159.png" width="50%" height="50%">
</div>

<br/>

times() 메서드도 비슷하게 동작됨.

---

<br/>

Dollar 예제의 TDD를 진행하면서 저자의 설계의 관점이 한 차원 다르다는 것을 느꼈다. Expression이란 인터페이스를 만들고 더하는 행위를 Sum 객체로 만드는 것을 처음엔 받아드리기 힘들었다.

왜냐하면, 내 기준에서 더하는 행위는 객체보단 그냥 메서드로 갖는 관점이 더 강했기 때문이다. 하지만 저자는 Sum이란 객체를 생성해 풀어가고자 했다.

하지만 진행하면서 느낀 저자의 Sum 객체 목적은 피연산자 2개의 상태를 갖고 처리하는 객체였다. 어떻게 보면 + 연산이 아닌 피연산자가 해당 객체의 컨셉이라 생각이 들었다.

챕터가 끝난 후 복기하면서 Sum이란 객체의 네이밍이 직관적인 이해의 도움이 안돼서, 그냥 피연산자들을 갖는 객체인 Operand로 객체명을 바꾸니 내 기준이지만 이해에 더 큰 도움이 됐다.
