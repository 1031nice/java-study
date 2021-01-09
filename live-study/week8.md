### 목차
- [인터페이스란](#인터페이스란)
- [인터페이스 정의](#인터페이스-정의)
- [인터페이스 구현](#인터페이스-구현)
- [인터페이스 상속](#인터페이스-상속)
- [default method](#default-method)
- [backward compatibility](#backward-compatibility)
- [Liskov substitution principle](#Liskov-substitution-principle)
- [static method](#static-method)
- [private method](#private-method)
- [참고](#참고)

#### 인터페이스란
인터페이스는 API를 나타내기(represent) 위해 고안되었다.
따라서 인터페이스는 타입과 해당 API를 구현하는 클래스가 반드시 제공해야 하는
메소드에 대해 기술(describe)한다. 하지만 일반적으로 인터페이스는 자신이
기술하는 메소드에 대한 구현 코드는 제공하지 않는다. 이와 같은 메소드는
인터페이스를 구현하는 클래스에서 반드시 구현해야만 한다.

#### 인터페이스 정의
인터페이스를 정의하는 방법은 클래스를 정의하는 것과 매우 유사하다.
`class`라는 키워드는 `interface`라는 키워드로 바꿔주면 된다.

```java
interface Centered {
    // method body가 없음(클래스에서 반드시 구현해야 함)
    // 인터페이스에서는 abstract 키워드가 없어도 암묵적으로 abstract
    void setCenter(double x, double y);
    double getCenterX();
    double getCenterY();
}
```

인터페이스의 멤버에는 다음과 같은 제약사항이 존재한다.
* 별도의 modifier(private, static, default)가 없는 메소드는 암묵적으로 `abstract`이며 메소드 body 자리에 세미콜론이 와야한다.
* 인터페이스는 public API를 정의한다. 관례를 따라 인터페이스의 멤버는 암묵적으로 `public`하며
불필요하게 `public` modifier를 붙이지 않는 것 역시 관례이다.
* 인터페이스는 인스턴스 필드를 정의하지 않는다. 필드란 구현체의 상세항목에 해당하는데,
인터페이스는 하나의 스펙이지 구현체가 아니기 때문이다. 인터페이스에서 허락되는 유일한 필드는
`static`과 `final`이 모두 붙은 상수뿐이다.
* 인터페이스 타입의 객체를 생성할 수 없으므로 인터페이스에서는 생성자를 정의하지 않는다.
* 자바 8부터 인터페이스는 `static` 메소드를 가질 수 있다.
* 자바 9부터 인터페이스는 `private` 메소드를 가질 수 있다.

#### 인터페이스 구현
클래스가 인터페이스를 구현한다는 뜻은 인터페이스의 `abstract` 메소드에 대한 구현(메소드 body)을 제공한다는 뜻이다.
만약 클래스가 인터페이스의 모든 `abstract` 메소드가 아닌 일부만 구현했다면,
해당 클래스는 인터페이스의 `abstract` 메소드를 그대로 상속받으므로 `abstract` 클래스여야만 한다.

* 클래스가 인터페이스를 구현할 때는 `implements` 키워드를 사용한다.
<br>`public class MyUserDao implements UserDao {}`
* `implements` 키워드는 `extends` 절 뒤에 올 수 있다.
<br>`public class CenteredRectangle extends Rectangle implements Centered {}`
* 여러 클래스를 상속받는 것은 안되지만 여러 인터페이스를 구현하는 것은 가능한데, 이 경우 인터페이스들을 콤마로 구분해야 한다.
<br>`interface Transformable extends Scalable, Translatable, Rotatable {}`

#### 인터페이스 상속
클래스 상속과 차이는 둘 이상의 부모를 가질 수 있다는 점이다.
```java
interface Transformable extends Scalable, Translatable, Rotatable {}
interface SuperShape extends Positionable, Transformable {}
```
인터페이스를 구현하는 클래스는 해당 인터페이스뿐 아니라 그 인터페이스가 상속받는 부모 인터페이스의 abstract 메소드까지 모두 구현해야 한다.

#### default method
특정 API 메소드는 반드시 구현하지 않아도 되는 경우, 다시 말해
인터페이스를 구현하는 클래스에서 인터페이스의 특정 메소드를 구현할 필요가 없는 경우
자바 8부터 도입된 `default`를 사용하면 된다.
default method는 반드시 인터페이스에서 구현되어야하며, 인터페이스를 구현하는 어떤 클래스에서든 사용될 수 있다.

#### backward compatibility
이전 버전에서 작성되거나 심지어는 컴파일된 코드가 이후 버전에서도 동작하는 것을 의미한다.
예를 들어보자. 현재 버전에서 MyUserDao라는 클래스가 UserDao라는 인터페이스를 구현하고 있는데,
다음 버전에서 UserDao 인터페이스에 새로운 메소드를 추가하였다.
인터페이스를 구현하는 클래스는 `abstract` 클래스가 아닌 이상
인터페이스의 모든 `abstract` 메소드를 구현해야하는데, 현재 인터페이스에 새로운 메소드만 추가했을 뿐
이 인터페이스를 구현하는 클래스에서는 새롭게 추가된 메소드의 구현이 없으므로 에러가 발생한다(호환되지 않는다).

자바 8 디자이너들은 자바 8에서 Collections 라이브러리를 업그레이드하는 것이 목표 중 하나였는데
Collections 라이브러리를 수정함에 따라 backward compatibility가 깨지는 것이 문제였다.
이 문제를 해결하기 위해 인터페이스에 새로운 메소드를 추가하더라도 backward compatibility가
깨지지 않아서 인터페이스가 버전에 따라 계속 진화해갈 수 있도록 하는 메커니즘이 필요했다.

default methods가 바로 그 메커니즘이다. 구버전의 인터페이스 구현체에게 신버전에서
인터페이스에 새로 추가하는 메소드의 구현을 제공해야 backward compatibility를 지킬 수 있는데,
그러기 위해 인터페이스에 구현이 있는 메소드를 둘 수 있게 만든 것이다. 이 메소드가 default methods이며
`default` 키워드를 통해 나타낸다.

#### Liskov substitution principle
잠시 상속으로 다시 돌아가보자. 자식 클래스 객체는 부모 클래스 객체를 100% 대체할 수 있다.
다시 말해, 부모 클래스 타입의 참조변수에 자식 클래스 객체가 담길 수 있다.
이를 Liskov substitution principle(이하 LSP)라고 한다.

인터페이스도 클래스처럼 새로운 레퍼런스 타입을 정의하며 LSP가 적용된다.
두 개의 클래스가 같은 인터페이스를 구현한다면, 해당 인터페이스 레퍼런스 타입의 변수에
두 개의 클래스 객체 모두 올 수 있다.
```java
public class Test{
    public static void main(String[] args){
        UserDao userDao1 = new MyUserDao(); // UserDao에 MyUserDao가 담길 수 있다
        UserDao userDao2 = new YourUserDao(); // UserDao에 YourUserDao도 담길 수 있다
    }
}
class MyUserDao implements UserDao {}
class YourUserDao implements UserDao {}
```

[dynamic method dispatch](week6.md#dynamic-method-dispatch) 역시 적용된다.
UserDao 타입이라고 하더라도 실제로는 MyUserDao 객체가 담겨있는 상태에서
해당 참조변수를 통해 UserDao 메소드를 호출 했을 때, 만약 이 메소드를 MyUserDao에서 `override`했다면
`override`한 메소드가 호출된다.

#### static method
자바 8부터 인터페이스도 완전한 구현을 갖는 `static` 메소드를 가질 수 있다.
완전한 구현을 가지는데다가 `static` 메소드이기 때문에 이 인터페이스를 구현하는 클래스에서
해당 메소드를 `override`하거나 변경할 수 없다.

#### private method
자바 9부터 인터페이스도 완전한 구현을 갖는 `private` 메소드를 가질 수 있다.

```java
public interface CustomInterface {
     
    void method1();
     
    // default: 이 인터페이스를 구현하는 클래스에서 별도의 구현 없이도 사용할 수 있음
    default void method2() {
        method4();  //private method inside default method
        method5();  //static method inside other non-static method
        System.out.println("default method");
    }
     
    // public static: 인터페이스 외부에서 쓰일 수 있음
    static void method3() {
        method5(); //static method inside other static method
        System.out.println("static method");
    }
     
    // private non-static: 인터페이스 내의 non-static 메소드에서 쓰일 수 있음
    private void method4(){
        System.out.println("private method");
    } 
     
    // private static: 인터페이스 내의 non-static과 static 모두에서 쓰일 수 있음
    private static void method5(){
        System.out.println("private static method");
    } 
}
 
public class CustomClass implements CustomInterface {
 
    // 클래스에서는 abstract 메소드만 구현하면 됨
    @Override
    public void method1() {
        System.out.println("abstract method");
    }
     
    public static void main(String[] args){
        CustomInterface instance = new CustomClass();
        instance.method1(); // non-static은 구현체의 객체를 생성한 뒤 참조
        instance.method2(); // non-static은 구현체의 객체를 생성한 뒤 참조
        CustomInterface.method3(); // 인터페이스의 public static은 인터페이스 이름을 통해 직접 참조
    }
}
 
Output:
 
abstract method
private method
private static method
default method
private static method
static method
```

#### 참고
[LSP](https://en.wikipedia.org/wiki/Liskov_substitution_principle)
<br>[static method](https://www.geeksforgeeks.org/static-method-in-interface-in-java)
<br>[private method](https://howtodoinjava.com/java9/java9-private-interface-methods)
<br>Java in a Nutshell 7th