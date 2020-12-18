### 목차
- [클래스와 객체](#클래스와-객체)
- [메소드](#메소드)
- [과제](#과제)

### 클래스와 객체

클래스란 필드와 메소드의 집합이다.
클래스에서 가장 중요한 것은 클래스가 새로운 데이터 타입을 정의한다는 것이다.
즉, 클래스를 하나 만든다는 것은 데이터 타입을 하나 추가한다는 것이다.
예를 들어 2차원 좌표평면의 점을 나타내는 `Point`라는 클래스를 만든다면, `Point`라는 데이터 타입이 만들어진다.

클래스로 인해 데이터 타입이 만들어질 때, 이 데이터 타입에 해당하는 값을 object(이하 객체)라고 한다.
`int` 데이터 타입에 `-1`, `0`, `1` 등의 값이 있듯,
`Point`라는 데이터 타입에 다양한 값(객체)이 있을 수 있다.
`Point` 클래스는 2차원 좌표평면의 점을 나타내는 데이터 타입인 반면,
`Point` 객체는 2차원 좌표평면 위의 수없이 많은 점 중 하나의 점에 대응된다.

#### 클래스를 정의하는 방법
클래스는 `class`라는 키워드와 클래스 이름 그리고 괄호 `{}`가 필요하다. 다음은 클래스를 정의한 예시이다.
```java
public class Point {
    public double x, y;
    public Point(double x, double y) { // 생성자
        this.x = x; this.y = y; // 필드 초기화
    }
    public double distanceFromOrigin() {
        return Math.sqrt(x*x + y*y);
    }
}
```
`Point` 클래스는 `Point.java` 파일에 저장되며 `Point.class`로 컴파일된다.

#### 객체 만드는 방법
앞서 `Point` 클래스를 만들었으므로 `Point`라는 새로운 데이터 타입을 사용할 수 있다.
`Point` 객체를 담는 변수는 다음과 같이 선언할 수 있다.

`Point p;`

하지만 `Point` 객체를 담는 변수를 선언하는 것만으로는 객체가 생성되지 않는다. 객체를 생성하기 위해서는 `new` 연산자를 사용해야 한다.
`new` 연산자 뒤에는 생성할 객체의 타입과 괄호 `()`가 차례대로 등장하는데,
괄호 속에 들어가는 값은 해당 클래스의 생성자에 전달되는 값으로 경우에 따라 반드시 채워야할 수도, 비워둘 수도 있다.
다음은 객체를 생성하는 예시이다.
```java
// x좌표가 2이고 y좌표가 3.5인 Point 객체 생성
// 변수 p는 생성되는 Point 객체에 대한 reference를 담고 있다.
Point p = new Point(2.0, -3.5);
// 현재 시간을 나타내는 객체 생성
LocalDateTime d = new LocalDateTime();
// 문자열의 집합을 담는 HashSet 객체 생성
Set<String> words = new HashSet<>();
```

#### 생성자
생성자는 새로 생성된 객체를 초기화하는 일을 하는 코드 블록으로 객체가 생성될 때 호출된다.
생성자가 작동하는 방식은 다음과 같다. `new` 연산자는 그 클래스의 객체를 생성하라고 지시한다.
그럼 그 객체를 담을 메모리 공간이 할당되고, 생성자의 body가 호출되는데
생성자에 매개변수가 있는 경우 전달된 인자를 가지고 객체를 초기화하는데 필요한 작업을 한다.
다음은 생성자의 특징이다.

* 생성자의 signatures에는 type이 없다. `void`조차 쓰지 않는다.
* 생성자의 이름은 클래스의 이름과 같아야 한다.

자바의 모든 클래스는 적어도 하나 이상의 생성자를 갖는다. 만약 프로그래머가 생성자를 정의하지 않으면
javac 컴파일러가 자동으로 생성자(default constructor)를 만든다. 

```java
public class Circle {
    protected double r; // 반지름
    public Circle(double r) { this.r = r; } // 생성자(반지름 초기화)
}
```

#### 객체 사용하기 
객체의 필드와 메소드에 `.` 연산자를 통해 접근할 수 있다.

```java
Point p = new Point(2, 3); // 객체 생성하기
double x = p.x; // 객체의 필드(x)값 읽기
p.y = p.x * p.x; // 객체의 필드(y)에 값 설정하기
double d = p.distanceFromOrigin(); // 객체의 메소드 호출하기
```

#### this
`this`라는 키워드는 현재의 객체를 가리킨다.

`public double area() { return Circle.PI * this.r * this.r; }`

위의 메소드가 Circle이라는 클래스의 메소드라면 위의 `this`는
저 메소드를 실행할 어떤 Circle 객체를 가리킨다.

```java
Circle c1 = new Circle();
c1.area();
```

예를 들면 위와 같은 코드에서 `c1.area()`가 실행될 때의
`this`는 c1이라는 이름의 Circle 객체가 된다.

`this` 키워드를 항상 써야하는 것은 아니다.
메소드가 해당 클래스의 객체 필드에 접근하거나 객체의 메소드가
객체의 다른 메소드를 호출할 때는 `this`를 생략해도 된다.

```java
public class Circle {
    protected double r; // 반지름
    public Circle(double radius) { this.r = radius; } // 생성자(반지름 초기화)
}
```

하지만 `this` 키워드를 사용하여 현재 객체의 멤버에 접근하고 있음을
명시적으로 알려야만 하는 때가 있다. 예를 들면 아래와 같이
메소드의 매개변수 이름과 객체의 필드 이름이 같은 경우이다.
이 경우 `this` 키워드를 써서 어떤 것이 객체의 필드이고
어떤 것이 매개변수인지 명시하지 않으면 모호하기 때문에 문제가 된다.

```java
public class Circle {
    protected double r; // 반지름
    public Circle(double r) { this.r = r; } // 매개변수 r의 값을 필드 r에 대입
}
```

#### 객체 리터럴
* String literals: `""`로 표시
<br>`String name = "David";`
* Type literals: 모든 클래스는 `Class`라는 이름의 클래스 객체를 가지고 있다.
`Class`는 데이터 타입을 나타내는 클래스로, 해당 타입에 대한 메타 정보를 가지고 있다.
```java
Class<?> typeInt = int.class;
Class<?> typeIntArray = int[].class;
```
* The null reference:
`null`은 아무것도 가리키지 않음을 나타내는 특별한 literal로,
모든 레퍼런스 타입의 멤버이다. `null`은 어떤 레퍼런스 타입에든 할당될 수 있다.
```java
String s = null;
Point p = null;
```

### 메소드

메소드란 다른 자바 코드에 의해 호출될 수 있는 일련의 statement로 이름을 가지고 있다.
메소드가 호출될 때 0개 이상의 값들이 전달되는데 이를 arguments라고 한다.
메소드는 특정 계산을 수행하고 선택적으로 값을 리턴한다.
메소드 호출은 side effects를 가질 수 있기 때문에 expression statements에서 사용될 수 있다.

#### 메소드 정의하기
메소드의 body는 `{}`로 쌓인 임의의 statements로 단순하다.
중요한 것은 메소드의 signature이다. signature는 다음의 것들을 명시한다.
* 메소드의 이름
* 메소드에 의해 사용되는 매개변수의 개수, 순서, 타입, 이름
* 메소드가 던질 수 있는 checked exceptions
* 메소드에 대한 추가적인 정보를 제공하는 여러 메소드 modifiers
<br>ex) `public`, `final`, `static`, `synchronized`...

다음은 메소드의 구성이다.

`modifiers type name (paramlist) [ throws exceptions ]`

modifiers란 띄어쓰기에 의해 구분되는 0개 이상의 특별한 modifiers 키워드를 말한다.

type은 리턴 타입을 말한다. 리턴하는 게 없는 메소드일 경우 type 자리에 `void`를 넣는다.

name은 변수의 name과 마찬가지로 자바의 식별자이다. 두 개 이상의 메소드가
같은 이름을 가질 수 있는데 이 경우 메소드의 매개변수 리스트가 달라야 한다.
같은 이름을 갖는 두 개 이상의 메소드를 정의하는 것을 메소드 오버로딩이라고 한다.
메소드 오버로딩의 경우 자바 컴파일러는 메소드를 호출할 때 전달하는 인자의 타입으로부터
어떤 메소드를 호출할지 결정한다.

paramlist는 매개변수 리스트이다. 메소드로 전달되는 0개 이상의 인자를 정의한다.
각각은 타입과 name으로 이루어졌으며 `,`로 구분된다. 메소드를 호출할 때
메소드에 전달하는 인자들은 반드시 메소드 매개변수 리스트의 매개변수 개수, 순서가 정확히 같아야 한다.
하지만 인자의 타입은 정확히 일치하지 않아도 캐스팅 없이 변환이 되는 경우 가능하다.

메소드가 어떤 예외도 던지지 않는다면 throws를 생략할 수 있다.

```java
// 문자열 배열을 인자로 받고, 어떤 것도 리턴하지 않음
public static void main(String[] args) {...}
// 두 개의 double 타입 인자를 받고, double 타입을 리턴
static double distanceFromOrigin(double x, double y) {...}
// 메소드의 body가 없고, 두 개의 예외가 있을 수 있음
protected abstract String readText(File f, String encoding) throws FileNotFoundException, UnsupportedEncodingException;
```

### 과제

- [Node.java](../src/main/java/liveStudy/binaryTree/Node.java)
- [BinaryTree.java](../src/main/java/liveStudy/binaryTree/BinaryTree.java)
- [Test.java](../src/main/java/liveStudy/binaryTree/Test.java)

참고: Java in a Nutshell 7th