## 15주차 live-study 목차
- [람다 표현식](#람다-표현식)
  - [람다 표현식 문법](#람다-표현식-문법)
  - [함수형 인터페이스](#함수형-인터페이스)
  - [람다의 쓰임](#람다의-쓰임)
  - [Variable capture](#Variable-capture)
- [메소드 레퍼런스](#메소드-레퍼런스)    
- [참고](#참고)
  
### 람다 표현식
자바 8에 도입된 람다는
* 크기가 작은 코드가 프로그램에서 리터럴로써 인라인 안에 쓰일 수 있도록 도와준다.
* 타입 추론을 사용함으로써 자바 코드의 깐깐한 네이밍 규칙을 완화시켰다.
* 함수형 프로그래밍을 보다 수월하도록 할 수 있도록 도와준다.

#### 람다 표현식 문법
람다 표현식은 다음과 같이 작성한다.

`(p, q) -> { /* method body */ }`

* 매개변수 리스트: (p, q)는 매개변수 리스트이다. 가장 먼저 매개변수 리스트를 써주어야 하는데
  보통 `()` 안에 담아야 한다. 하지만 매개변수가 하나일 경우 `()`를 생략할 수 있다.
* arrow token: 람다임을 표현하기 위한 약속일 뿐 별 뜻은 없다.
* method body: 마지막으로 메소드 바디가 온다. 메소드 바디는 단일 표현식일 수도 있고, `{}`로 감싸진 statement 블럭일 수도 있다.

#### 함수형 인터페이스
람다의 타겟 타입(람다가 생성할 객체의 타입)이 되기 위해서는 조건이 필요하다. 인터페이스여야하며, 단 하나의 nondefault 메소드를 가져야 한다.
(`default` 메소드는 여러개 가져도 상관없다.) 이러한 조건을 만족하는 인터페이스를 함수형 인터페이스라고 부른다.

함수형 인터페이스를 위한 어노테이션이 있다.

```java
@FunctionalInterface
public interface Add {
    int add(int a1, int a2);
    int sub(int a1, int a2); // nondefault 메소드가 두 개 이상이므로 컴파일 에러
}
```

`@FunctionalInterface`는 인터페이스에 nondefault 메소드가 두 개 이상일 경우 컴파일 에러를 발생시킨다.
함수형 인터페이스를 위해 반드시 사용해야 하는 어노테이션은 아니지만 실수를 예방할 수 있다.
강제는 아니지만 실수를 예방할 수 있고, 명시적이라는 점에서 `@Override`와 용도가 유사하다.

#### 람다의 쓰임
이름이 없는 익명 클래스가 이름 있는 클래스보다 더 간결하지만
함수형 인터페이스의 구현체의 경우(하나의 메소드만 갖는 클래스의 경우) 익명 클래스조차 과하다.
람다 표현식을 이용하면 하나의 메소드만을 갖는 클래스의 객체를 익명 클래스보다 더 간결하고 분명하게 표현할 수 있다.
즉, 람다 표현식은 특정 타입(람다의 타겟 타입)의 객체의 생성을 나타낸다.

```java
// -> 뒤에 오는 메소드 바디를 갖는 Runnable 타입 객체가 생성되어 변수 r에 할당된다.
Runnable r = () -> System.out.println("Hello");
```

#### Variable Capture
람다 표현식은 특정 조건 하에 람다 밖에서 정의된 지역 변수에 접근할 수 있다. 람다가 변수를 capture한다고 표현한다.
지역 변수에 접근할 수 있기 위해서는 해당 지역 변수가 `final` 또는 effectively final이어야 한다. 
effectively final이란 `final`로 선언되어 있지 않더라도 초기화된 이후 값이 변하지 않아 유사 `final`로 볼 수 있는 것을 말한다.

```java
interface IntHolder {
    public int getValue();
}

public class Weird {
    public static void main(String[] args) {
        IntHolder[] holders = new IntHolder[10];
        for (int i = 0; i < 10; i++) {
            int fi = i;
            holders[i] = () -> {
                return fi; // 컴파일 에러: variable used in lamda expression should be final or effectively final
            };
            fi = 1; // fi는 더이상 effectively final이 아님
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(holders[i].getValue());
        }
    }
}
```

위의 코드에서 fi는 `final`이 붙어있지 않지만 fi가 초기화된 이후로
(람다식의 앞에서든 뒤에서든) 값이 변하지 않는다면 effectively final이 되어 람다식에서도 사용가능하다.
하지만 초기화된 이후에 값이 변경되었기 때문에 effectively final이 아니므로 람다식에서 사용할 수 없다는 컴파일 에러가 발생하였다.

### 메소드 레퍼런스
다음의 람다 표현식을 보자.

````java
// 실제 코드에서는 타입 추론 덕분에 코드가 더 간결할 것이다
(MyObject myObj) -> myObj.toString()
````

위의 람다에서 하는 일이라곤 myObj 객체의 toString() 메소드를 호출하는 것뿐이다.
이렇게 단순히 메소드를 호출하는 일이 전부인 경우는 람다 표현식 조차 과분하기 때문에
자바 8에서는 이런 코드를 더 간결하게 쓸 수 있는 문법을 제공한다.
이를 메소드 레퍼런스라고 하며 아래와 같이 사용한다. 아래의 코드는 위의 람다 표현식과 완전히 동일하다.

`MyObject::toString`

다음은 람다 표현식의 유형에 따른 메소드 레퍼런스 유형이다.

| Name        | Method reference    | Equivalent lambda              |
| ----------- | ------------------- | ------------------------------ |
| Unbound     | Trade::getPrice     | trade -> trade.getPrice()      |
| Bound       | System.out::println | s -> System.out.println(s)     |
| Static      | System::getProperty | key -> System.getProperty(key) |
| Constructor | Trade::new          | price -> new Trade(price)      |

### 참고
Java in a Nutshell 7th
<br>[variable capture](http://tutorials.jenkov.com/java/lambda-expressions.html)
