### 목차
- [상속](#상속)
- [추상 클래스](#추상-클래스)

### 상속
다음과 같이 사람을 나타내는 Person 클래스가 있을 때,
```java
public class Person {
    String name;
    int age;
}
```
학번을 필드로 갖는 학생을 나타내는 클래스가 필요하다고 가정해보자.
Person 클래스에 학번 필드를 추가할 수도 있겠지만 이렇게 할 경우
학생이 아닌 사람은 불필요한 필드를 갖게 되므로 문제가 된다.
name과 age 뿐만 아니라 특정 필드를 반드시 가져야 하는 클래스가 생길 때마다
불필요한 필드는 점점 늘어날 것이고, Person이라는 클래스는 학생도, 선생님도 어떤 것도
나타내지 못하는 클래스가 될 것이다.

자바의 상속을 이용하면 이 문제를 해결할 수 있다. 상속이란 부모의 것을 물려받는 것이다.
학생 클래스가 필요할 때 Person 클래스에 학번 필드를 추가하는 것이 아니라,
Student라는 클래스를 만들어 Person 클래스를 상속받게 하고, 학번 필드만 추가해주는 방식이다.
```java
public class Student extends Person {
    String studentId;
}
```
상속은 `extends` 키워드를 통해 나타낸다.
Student 클래스 안에는 name과 age가 보이지 않지만 이들을 갖는 Person 클래스를 상속받고 있으므로
name과 age라는 필드를 갖고 있다. 이는 메소드에도 적용된다. 자식 클래스는 부모 클래스의
메소드 또한 물려받아 사용할 수 있다.

자식 클래스의 객체를 생성하려고 하면 반드시 부모 클래스의 객체가 먼저 생성된 뒤에 자식 클래스의 객체가 생성되는데
이는 `super` 키워드를 다룰 때 더 살펴보도록 한다.

#### Object 클래스
자바에서 상속의 특징은 한 클래스를 제외한 모든 클래스는 반드시 하나의 부모를 갖는다는 점이다. 
(둘 이상의 부모 클래스를 가질 수 없고, 부모 클래스를 갖지 않을 수도 없다.)
유일한 예외는 바로 Object 클래스로 이는 모든 클래스의 부모이다.
(`extends` 키워드를 사용해 명시적인 상속 관계를 표현하지 않은
클래스의 경우 Object 클래스를 직접 부모로 삼는다. 어떤 클래스 A에 명시적인 상속 관계가 있는 경우
부모의 부모를 끝까지 따라가다 보면 더이상 상속받지 않는 마지막 클래스가 등장하는데
그 클래스의 부모가 Object 클래스이므로 A의 바로 위 부모가 Object 클래스가 아닐 뿐
결국 A 클래스는 Object 클래스의 자식 클래스에 해당한다.)

#### 메소드 오버라이딩
자식 클래스에서 부모 클래스의 메소드와 같은 이름, 같은 리턴 타입, 같은 매개변수의
메소드를 정의했을 때 자식 클래스의 메소드가 부모 클래스의 메소드를 오버라이드한다고 한다.
다음은 오버라이드의 예시이다. SportsCar라는 자식 클래스가 range() 메소드를 오버라이드 하고 있다.
```java
public class Car { 
    public static final double LITRE_PER_100KM = 8.9;
    protected double fuelTankCapacity;
    public double range() {
        return 100 * fuelTankCapacity / LITRE_PER_100KM;
    }
}

public class SportsCar extends Car {
    private double efficiency;
    @Override
    public double range() {
    return 100 * fuelTankCapacity * efficiency / LITRE_PER_100KM;
    }
}
```
range() 메소드 위에 `@Override`라는 어노테이션이 붙어있는데 이는 필수는 아니다.
이 어노테이션을 붙이지 않은 채로 오버라이드한다고 해서 컴파일 에러가 발생하지는 않는다.
다만 오버라이드하지 않는 메소드에 `@Override` 어노테이션이 붙으면 컴파일 에러가 발생한다.
`@Override`를 잘 사용하면 보다 명시적인 프로그램이 되고, 실수가 줄어든다.

메소드 오버라이딩은 객체 메소드에만 해당된다. 클래스 메소드 즉, `static` 메소드에는
오버라이딩 개념이 없다. 필드와 마찬가지로 자식 클래스에 의해 가려질 수는 있어도
오버라이딩되지는 않는다.

#### 다형성
자식 클래스는 부모 클래스의 플러스 알파로, 부모 클래스는 자식 클래스에 완전히 속한다.
따라서 부모 클래스의 객체가 있는 자리에 자식 클래스의 객체가 올 수 있으나,
자식 클래스의 객체가 있는 자리에 부모 클래스의 객체가 올 수는 없다.
즉, Student 클래스의 객체는 Person 클래스의 객체로 바라볼 수 있으나,
Person 클래스의 객체는 Student 클래스의 객체로 바라볼 수 없다.
 ```java
Person p = new Person();
Student s = new Student();
p = s; // 가능
p.name = "Curry"; // 가능
s = p; // 불가능(캐스팅 필요)
// s.studentId = "202100000";
```

#### 다이나믹 메소드 디스패치
부모 객체 자리에 자식 객체가 올 수 있다면,
부모 객체 자리에 자식 객체를 두고 자식 객체가 오버라이드한 메소드를 호출했을 때
누구의 메소드가 호출될까?
```java
Person p = new Student();
p.anyMethod(); // Student 클래스에서 오버라이드한 메소드
```
p의 타입이 Person이지만 실제로 담긴 것은 Student 객체이므로
p.anyMethod()는 Student 객체의 anyMethod()가 호출된다.
메소드가 정적으로 디스패치된다면 참조변수 타입에 따라 Person의 메소드가 호출되겠지만,
동적으로 디스패치되기 때문에 Person의 메소드가 아니라 p에 담겨있는 실제 객체인 Student의 메소드가 호출된다.
(p로 호출할 수 있는 메소드는 오직 Person 클래스에서 정의한 메소드 뿐이다.
Student에서 추가한 메소드는 p로 호출할 수 없다. p의 타입이 Person이기 때문이다.)

#### super
`super`는 자바의 예약어 중 하나로 주된 쓰임 중 하나는 자식 클래스의 생성자에서
부모 클래스의 생성자를 호출하는 것이다. 부모 클래스의 생성자는 자식 클래스의
생성자에서 `super()`를 통해 호출할 수 있으며 이는 반드시 생성자의 첫 줄에 등장해야 한다.
(`super()`를 생략하면 자바가 알아서 맨 윗 줄에서 매개변수가 없는 부모 클래스의 생성자 호출을 시도한다.
그런 생성자가 존재하지 않는 경우 컴파일 에러가 발생한다. 매개변수가 없는 생성자를 부모 클래스에 추가하든,
자식 클래스의 생성자의 맨 윗 줄에서 명시적으로 부모 클래스의 어떤 생성자를 호출해주어야 한다.)
```java
public PlaneCircle(double r, double x, double y) {
    super(r); // 부모 클래스 Circle의 생성자 호출
    this.cx = x;
    this.cy = y;
}
```
그 외에 `super`의 쓰임은 `this`와 비슷하다.
자식 클래스에서 `super`는 부모 클래스의 멤버를 참조할 때 사용하고,
일반적으로 `super`는 생략 가능 하지만 다음과 같이 이름이 같아서
누구의 멤버인지 명시해야 하는 경우 `super`를 사용한다.
```java
public class Person {
    String name;
}
class Student extends Person {
    String name;
    void setName(String name) {
        // 아무것도 안 붙은 name은 매개변수 name
        this.name = name; // Student의 name에 input 대입
        super.name = name; // Person의 name에 input 대입
    }
}
```

#### final
`final`이라는 키워드가 붙은 대상은 상수로 여겨진다.
필드에 붙으면 그 필드는 초기화값 이외의 다른 값을 가질 수 없고,
메소드에 붙으면 그 메소드는 자식 클래스에서 오버라이드 할 수 없으며,
클래스에 붙으면 그 클래스는 부모 클래스가 될 수 없다.

### 추상 클래스
Circle, Rectangle, Square, Haxagon, Triangle 등의 다양한 도형 클래스가 있을 때
Shape이라는 클래스를 이들의 부모 클래스로 설정한다면 Circle이든 Triangle이든 모두 Shape
타입의 변수에 넣어 사용할 수 있으므로 다루기 편리해진다.

만약 도형 클래스 모두 area()라는 메소드를 가지고 있다면 어떻게 해야할까?
도형 객체를 모두 Shape 타입의 참조변수에 담아 편리하게 사용하기 위해서는
Shape 클래스에도 area() 메소드를 추가해야 한다.
하지만 Shape 클래스는 각 도형 클래스을 묶기 위한 개념적인 클래스로,
area()라는 메소드를 구현하기 애매하다. 자바는 이런 상황을 `abstarct` 메소드를 이용하여 처리한다.

메소드에 `abstract`라는 modifier를 붙이면 구현을 생략할 수 있다.
```java
public abstract class Shape {
    public abstract double area();
}
```
* `abstract` 메소드를 하나라도 가지고 있다면 그 클래스는 `abstract` 클래스이다.
* `abstract` 메소드는 구현이 없다.
* `abstract` 클래스의 객체는 생성될 수 없다.
* `abstract` 클래스를 상속받은 클래스에서 모든 `abstract` 메소드를 오버라이드하여 구현하지 않으면
상속받은 자식 클래스 역시 `abstract` 클래스가 된다.
* `static`, `private`, `final` 메소드는 `abstract`일 수 없다. 이들은 자식 클래스에 의해 오버라이드 될 수 없기 때문이다.
같은 맥락에서 `final` 클래스는 `abstarct` 메소드를 가질 수 없다. `final` 클래스는 상속이 불가능하기 때문이다.
* `abstract` 메소드를 갖지 않는 클래스도 `abstract` 클래스로 선언될 수는 있다. 

참고: Java in a Nutshell 7th