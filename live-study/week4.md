### 목차
- [선택문](#선택문)
- [반복문](#반복문)
- [과제1-대시보드](#과제1)
- [과제2-LinkedList](#LinkedList)
- [과제3-Stack](#Stack)
- [과제4-Queue](#Queue)

### 선택문
#### if/else statement
`if` statement는 expression과 statement로 구성된다.
expression이 `true`라면 자바 인터프리터는 statement를 실행하고, `false`라면 statement를 건너뛴다.

```java
if (username == null) // If username is null,
    username = "John Doe"; // use a default value
```

관계 없는 것처럼 보여도 `if` 뒤에 오는 괄호 `()`는 `if` statement 문법 중 하나로, 빠뜨려선 안된다.
`{}`으로 감싸진 블록도 그 자체가 하나의 statement이므로 다음과 같이 쓸 수도 있다.

 ```java
if ((address == null) || (address.equals(""))) {
    address = "[undefined]";
    System.out.println("WARNING: no address specified.");
}
```

`if` statement에서 원한다면 `else` 키워드를 사용할 수 있다. `else` 키워드를 사용한다면,
`if` statement의 expression이 `false`일 때 `else` 뒤에 오는 statement가 실행된다.

```java
if (username != null)
    System.out.println("Hello " + username);
else {
    username = askQuestion("What is your name?");
    System.out.println("Hello " + username + ". Welcome!");
}
```

`if/else`를 중첩해서 사용하는 경우 `else`를 주의해서 사용해야 한다.
자바에서 `else`는 들여쓰기와 관계없이 가장 가까운 `if` statement에 붙는다. 

```java
if (i == j)
    if (j == k)
        System.out.println("i equals k");
else // 들여쓰기를 이렇게 해도 두 번째 if statement에 붙는다.
    System.out.println("i doesn't equal j"); // 의도와 달리 이는 j와 k가 다름을 보임
```
다음과 같이 `{}`를 사용하여 명시적으로 작성해야 한다.
```java
if (i == j) {
    if (j == k)
        System.out.println("i equals k");
}
else {
    System.out.println("i doesn't equal j");
}

```
#### else if statement
둘 이상의 블록 중에 선택하여 실행하고 싶다면 `else if`를 사용할 수 있다.
```java
if (n == 1) {
    // Execute code block #1
}
else if (n == 2) {
    // Execute code block #2
}
else if (n == 3) {
    // Execute code block #3
}
else {
    // If all else fails, execute block #4
}
```

#### switch statement
여러 개의 블록 중에서 어떤 블록을 실행할지가 어떤 변수의 값에 의해 결정되는 경우
`if` statements를 사용하는 것보다 `switch` statement를 사용하는 것이 더 좋다.

```java
switch(n) {
    case 1: // Start here if n == 1
        // Execute code block #1
        break; // Stop here
    case 2: // Start here if n == 2
        // Execute code block #2
        break; // Stop here
    case 3: // Start here if n == 3
        // Execute code block #3
        break; // Stop here
    default: // If all else fails...
        // Execute code block #4
        break; // Stop here
}
```

`switch` statement를 실행할 때 인터프리터는 괄호 속 표현식의 값을 계산하고 그 값에 맞는
`case`를 찾아 그 블록을 실행한다. 찾지 못할 경우 `default` 블록이 있으면 이를 실행한다.

위의 예시코드에서 `case`마다 `break` 키워드가 쓰였음에 유의하자.
`switch` statement에서 `case`는 시작 포인트만 명시하기 때문에 `break`를 쓰지 않을 경우,
표현식 값을 통해 찾은 하나의 `case` 블록을 실행한 뒤 계속해서 다음 `case` 블록을 차례로 실행해나간다.  

주의사항
* `switch` statement 뒤에 오는 표현식의 타입은 `int`, `short`, `char`, `byte`,(또는 이들의 wrapper type) `String`, `enum`일 수 있다.
<br>(`float`, `double`, `boolean`, `long`은 지원하지 않는다.)
* `case` 뒤에 동적인 표현식(런타임에 결정되는 표현식)이 와서는 안된다. 상수 또는 정적인 표현식이 와야 한다.
* `case` 레이블 뒤에 오는 값은 유일해야 한다.  
* 두 개 이상의 `default` 레이블을 가질 수 없다.

### 반복문
#### while statement
```java
while (expression)
    statement
```
인터프리터가 `while` statement를 만나면 `while` 뒤에 오는 expression을 확인한다.
expression이 `fasle`일 경우 `while` statement를 생략하고 프로그램의 다음 statement로 넘어간다.
만약 `true`일 경우 `while` statement가 실행되며, statement가 종료되면 다시 `while` 뒤의
expression을 검사하는데 expression이 `true`인 동안에는 계속 이러한 사이클을 반복한다.

#### do statement
```java
do
    statement
while (expression);

```
`do` statement는 `while` statement와 유사하지만 expression을 확인하는 위치가 다르다.
`do` statement는 `while` statement와 달리 expression이 statement 뒤에 위치한다.
때문에 `do` statement는 적어도 한 번은 실행되며, expression으로 끝나므로 끝에 `;`를 붙여줘야 한다.

#### for statement
대부분의 반복문은 반복의 횟수를 세기 위한 변수 또는 상태 변수(이하 반복변수)를 초기화한 뒤,
반복을 제어하기 위해 이 변수를 특정값 또는 조건과 검사하고,
statement가 끝나기 전에 업데이트하여 무한 반복에 빠지지 않고 의도한 대로 코드가 동작하도록 한다.
`for` statement에서는 대부분의 반복문이 갖는 초기화, 검증, 업데이트 과정이 문법에 드러난다.
덕분에 반복문이 무엇을 하는지 이해하기 쉽고, 초기화 또는 업데이트 부분을 실수로 빼먹는 일이 없다.
```java
for(initialize; test; update) {
    statement
}

// 위의 for loop과 동일한 의미의 while loop
initialize;
while (test) {
    statement;
    update;
}
```
인터프리터는 초기화 expression과 업데이트 expression의 값을 무시하기 때문에
이들이 의미를 가지려면 이들 expression은 side effect를 반드시 가져야 한다.
```java
int count;
for(count = 0 ; count < 10 ; count++)
    System.out.println(count);
```
많은 반복문의 반복변수는 반복문 내부에서만 쓰이기 때문에 `for` statement는
초기화 부분에서 아예 선언까지 가능하도록하여 편의를 제공한다. (반복변수는 반복문 안에서만 존재한다.)
```java
for(int count = 0 ; count < 10 ; count++)
    System.out.println(count);
```
초기화와 업데이트 부분에서 두 개 이상의 변수를 사용할 수도 있다.
```java
for(int i = 0, j = 10 ; i < 10 ; i++, j--)
    sum += i * j;
```
초기화와 업데이트 표현식에 반드시 숫자가 와야하는 것은 아니다.
예를 들면 다음과 같이 linked list의 원소가 올 수도 있다.
```java
for(Node n = listHead; n != null; n = n.nextNode())
    process(n);
```
`for` statement의 초기화, 검사, 업데이트 표현식은 모두 optional하다.
단, 각각의 영역이 `;`에 의해 구분되기 때문에 `;`은 생략될 수 없다.
검사 표현식이 생략될 경우 `true`로 간주되기 때문에 무한 반복을 다음과 같이 쓸 수 있다.
`for(;;)`  

#### break
`break` 키워드는 자바 인터프리터가 즉시 `break`에서 가장 가까운
`while`, `do`, `for`, 또는 `switch` statement를 탈출하게 한다.
```java
for(int i = 0; i < data.length; i++) {
    if (data[i] == target) { // When we find what we're looking for,
        index = i; // remember where we found it
        break; // and stop looking!
    }
} // The Java interpreter goes here after executing break
```

#### continue statement
반복문을 탈출하는 `break` statement와 달리 `continue` statement는 현재의 반복을 생략하고
다음 반복으로 넘어간다. `while`, `do`, `for` statement에서만 사용될 수 있다.
```java
for(int i = 0; i < data.length; i++) { // Loop through data.
    if (data[i] == -1) // If a data value is missing,
        continue; // skip to the next iteration.
    process(data[i]); // Process the data value.
}
```
`while`과 `do`는 `continue`를 만나면 조건을 검사하고, 참일 경우 다음 반복을 실행하는데,
`for`는 `continue`를 만나면 업데이트 표현식을 거친 뒤에 조건을 검사한다.

### 과제1
[대시보드](../src/main/java/liveStudy/GithubAPIStudy.java)

### LinkedList
[LinkedList](../src/main/java/liveStudy/linkedList)

### Stack
[Stack](../src/main/java/liveStudy/stack)

### Queue
[Queue](../src/main/java/liveStudy/queue)

### 테스트 코드
- [수동 테스트](../src/main/java/liveStudy/Test.java)
- [Junit4](../src/test/java/MyCollectionTest.java)