## 11주차 live-study 목차
- [Enum](#Enum)
    - [java.lang.Enum](#javalangenum)
    - [Enum의 특징](#Enum의-특징)
    - [EnumSet](#EnumSet)
- [참고](#참고)

### Enum
자바는 타입 시스템에서 특정 역할을 이행하는 특별한 형태의 클래스 enumerated types(또는 enums)을 가지고 있다.
이늄은 일반 클래스와는 달리 제한적인 기능을 가지며, 오직 타입에서 허용한 값만 가질 수 있다.
값은 상수이기 때문에 이늄을 연관된 상수들의 집합으로 볼 수 있다.

예를 들어 세 개의 색깔 빨강, 초록, 파랑을 나타내는 타입을 정의하고, 오직 세 개의 색깔만
타입의 값이 될 수 있도록 만들고 싶은 경우 `enum` 키워드를 사용하여 이를 가능하게 할 수 있다.

```java
public enum PrimaryColor {
    RED, GREEN, BLUE; // 이늄 상수 리스트

    public void test() {
        // PrimaryColor 타입에는 RED, GREEN, BLUE만 담길 수 있음
        // PrimaryColor 타입의 인스턴스는 마치 `static` 필드에 접근하듯 다음과 같이 참조 가능
        PrimaryColor color1 = PrimaryColor.RED;
        PrimaryColor color2 = PrimaryColor.GREEN;
        PrimaryColor color3 = PrimaryColor.BLUE;
    }
}
``` 

위의 이늄 상수 리스트는 아래와 같이 표현할 수 있다.

```java
class PrimaryColor {
    public static final PrimaryColor RED = new PrimaryColor();
    public static final PrimaryColor GREEN = new PrimaryColor();
    public static final PrimaryColor BLUE = new PrimaryColor();
}
```

이늄에서 RED, GREEN, BLUE라고 나열하면 이름이 각각 RED, GREEN, BLUE인
PrimaryColor 타입의 객체가 생성되는 것이다.

이늄도 클래스이기 때문에 필드와 메소드를 가질 수 있다. 만약 이늄이 필드 또는 메소드로 이루어진
몸체를 가진다면 인스턴스의 리스트 끝에 세미콜론(;)을 붙여줘야하고(필드와 메소드를 갖지 않는다면 생략할 수 있다),
이늄 상수의 리스트는 메소드나 필드보다 앞에 와야 한다. 

예를 들어 정다각형을 나타내는 이늄을 만들어보자.

```java
public enum RegularPolygon {
    // 이늄 상수 뒤에 괄호와 함께 인자가 오면, 해당 인자를 받는 생성자를 이용하여 상수를 만든다는 뜻
    TRIANGLE(3), SQUARE(4), PENTAGON(5), HEXAGON(6);
    private Shape shape;
    public Shape getShape() {
        return shape;
    }
    private RegularPolygon(int sides) {
        switch (sides) {
            case 3:
                shape = new Triangle();
                break;
            case 4:
                shape = new Rectangle();
                break;
            case 5:
                shape = new Pentagon();
                break;
            case 6:
                shape = new Hexagon();
                break;
        }
    }
}
```

#### java.lang.Enum
`Enum`은 모든 이늄의 부모인 `abstract` 클래스이다. `Enum`이 제공하는 유용한 메소드는 다음과 같다.

* `public final String name()`: 이늄 상수의 이름을 리턴
* `public final int ordinal()`: 이늄 상수의 순서를 리턴
* `public final Class<E> getDeclaringClass()`: 해당 이늄 상수의 이늄 타입 클래스 객체 리턴
* `public static <T extends Enum<T>> T valueOf(<br>Class<T> enumType, String name)`
<br>: 이늄 타입과 이늄 상수의 이름을 받아 해당 이늄 상수의 타입 클래스 객체 리턴
* `public static T[] values()`: 해당 이늄 타입의 모든 이늄 상수를 배열로 리턴
<br>참고: 이 메소드는 컴파일러가 이늄을 만들 때 추가해주는 특별한 메소드라서
javadoc에는 나와있지 않음.

#### Enum의 특징
* 모든 이늄은 `java.lang.Enum`의 자식이다.
* 제네릭을 사용할 수 없다.
* 인터페이스를 구현할 수 있다.
* 상속될 수 없다.
* 모든 이늄 값이 구현을 제공한다면, 이늄은 `abstract` 메소드만 가지는 것도 가능하다.
* `new` 연산자에 의해 직접적으로 인스턴스화될 수는 없다.

#### EnumSet
`EnumSet`은 이늄 타입을 위한 `Set`으로 `Set`에 몇 가지 메소드가 추가된 것이라고 볼 수 있다.
(`EnumSet` 또한 `Set` 인터페이스를 구현하고 있는 `abstract` 클래스이기 때문이다.)
다만 원소들이 모두 이늄이기 때문에(내부적으로 비트 벡터를 이용하여 구현할 수 있으므로) `Set`을 더 효율적으로 구현했다는 점이 다르다.
(`HashSet`과 같은 다른 `Set`의 구현체에도 이늄을 담을 수 있기는 하지만 `EnumSet` 보다 덜 효율적이다.)
`EnumSet`의 사용 예시는 아래와 같다.

```java
enum days {  
  SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY  
}  
public class EnumSetExample {  
  public static void main(String[] args) {  
    // EnumSet에 담기는 이늄은 동일한 이늄타입이어야 한다
    Set<days> set = EnumSet.of(days.TUESDAY, days.WEDNESDAY);    
    Iterator<days> iter = set.iterator();  
    while (iter.hasNext())
      System.out.println(iter.next());  
  }  
} 
```

### 참고
Java in a Nutshell 7th
<br>[EnumSet 예제](https://www.javatpoint.com/java-enumset)