## 12주차 live-study 목차
- [애노테이션](#애노테이션)
  - [애노테이션 정의 방법 및 사용 예시](#애노테이션-정의-방법-및-사용-예시)
  - [애노테이션 프로세서](#애노테이션-프로세서)
- [메타애노테이션](#메타애노테이션)
  - [@Retention](#@Retention)
  - [@Documented](#@Documented)
  - [@Target](#@Target)
  - [@Inherited](#@Inherited)
  - [@Repeatable](#@Repeatable)
- [참고](#참고)

### 애노테이션
Annotations types(또는 annotations)이란
자바 타입 시스템에서 특정 역할을 이행하는 특별한 형태의 인터페이스를 말한다.
애노테이션은 메타데이터의 형식으로 프로그램에 대한 데이터를 제공하는데
이 데이터는 프로그램의 일부가 아니기 때문에 애노테이션이 붙은 코드의 동작에 직접적인 영향을 미치지는 않는다.

`@`는 컴파일러에게 다음에 오는 것이 애노테이션임을 알려주는 문자이다. 애노테이션의 가장 단순한 형태는 아래와 같다.

`@Override`

#### 애노테이션 정의 방법 및 사용 예시
애노테이션은 '특별한 형태의 인터페이스'이다.
애노테이션을 정의할 때는 `interface` 키워드 앞에 `@`를 붙여 애노테이션임을 표현해주어야 한다.

```java
public @interface Author {
    String name();
    String date();
}
```

애노테이션은 elements를 가질 수 있다. `Author`에서 name과 date가 바로 elements이다.
애노테이션을 사용할 때는 해당 애노테이션의 elements에 반드시 값을 설정해주어야 한다.

```java
@Author(
        name = "Benjamin Franklin",
        date = "2/3/2021"
)
class MyClass {}
```

하지만 다음과 같이 애노테이션을 정의할 때 `default` 키워드를 이용하여
초깃값을 설정해주면 elements에 값을 부여하지 않아도 문제되지 않는다.

```java
public @interface Author {
    String name() default "Thompson";
    String date() default "2/3/2021";
}
```

만약 애노테이션이 `value`라는 유일한 element를 갖고 있는 경우라면 이름을 생략할 수 있다.

```java
public @interface Author {
    String value();
}

@Author("Thompson")
class A {}
```

#### 애노테이션 프로세서
Source-level 애노테이션 프로세싱은 자바 5에서 처음 등장했다.
이는 컴파일 스테이지에서 추가적인 소스 파일을 생성하는 아주 편리한 기술이다.
애노테이션 프로세싱에 의해 생성되는 소스 파일은 자바 파일을 뿐 아니라 다른 것이 될 수도 있다.
Description, metadata, documentation, resources 등 어떤 타입의 파일일 수도 있다.

* 애노테이션 프로세싱은 많은 자바 라이브러리에서 활발하게 사용되고 있다.
예를 들어, QueryDSL와 JPA의 메타클래스를 생성하거나 롬복에서 boilerplate 코드를 생성할 때 사용된다.
* 애노테이션 프로세싱 API의 한계는 기존 파일에 변화를 줄 수는 없고
오직 새로운 파일을 생성하는데만 쓰일 수 있다는 것이다.

애노테이션 프로세싱은 여러 라운드를 통해 수행된다.
각각의 라운드가 시작되면 컴파일러는 소스 파일에서 애노테이션을 찾고
해당 애노테이션에 적합한 애노테이션 프로세서를 찾는다.
각 애노테이션 프로세서는 차례로 대응되는 소스 위에서 호출된다.
만약 이 과정에서 어떤 파일이 생성되었다면 다음 라운드는 생성된 파일을 인풋으로 받은 뒤 라운드를 진행한다.
이 과정은 프로세싱 스테이지 동안 새로운 파일이 생성되지 않을 때까지 계속된다.

애노테이션 프로세싱 API는 javax.annotation.processing 패키지에 위치해있다.
반드시 구현해야 하는 주요 인터페이스는 `Processor`인데, 이 인터페이스의 메소드 중
일부에 대한 구현을 가진 `AbstractProcessor` 클래스를 상속받아 자신만의 애노테이션 프로세서를 만들 수 있다.

### 메타애노테이션
다른 애노테이션에 적용되는 애노테이션을 메타애노테이션이라 한다.
다음은 java.lang.annotation에 정의된 몇몇 메타애노테이션이다.

#### @Retention
애노테이션을 어떻게 저장할지를 명시하는 애노테이션이다.
이 애노테이션은 `value`라는 element에 `RetentionPolicy` 타입을 줄 수 있다.
* RetentionPolicy.SOURCE: 소스 레벨에서만 유지되고 컴파일러에게는 무시된다(주석과 다를바 없다).
* RetentionPolicy.CLASS: 컴파일 타임까지 살아남지만 JVM에게는 무시된다.
* RetentionPolicy.RUNTIME: JVM까지 볼 수 있도록 유지된다. 따라서 런타임 환경에 사용될 수 있다.

#### @Documented
`@Documented`를 가진 애노테이션이 가리키는 자바 elements는 Javadoc 도구에 의해 문서화된다. (디폴트 설정을 따르면 애노테이션은 Javadoc에 포함되지 않는다.)

#### @Target
어떤 자바 elements에 적용될 수 있는지를 나타내는 애노테이션이다.
`value`라는 이름의 element에 아래와 같은 `ElementType`이 올 수 있다.

* ElementType.ANNOTATION_TYPE
* ElementType.CONSTRUCTOR
* ElementType.FIELD
* ElementType.LOCAL_VARIABLE
* ElementType.METHOD
* ElementType.PACKAGE
* ElementType.PARAMETER
* ElementType.TYPE

예를 들어 `ElementType.METHOD`이 붙은 애노테이션을 클래스 위에 붙이면 컴파일 에러가 발생한다.

#### @Inherited
애노테이션이 수퍼 클래스로부터 상속될 수 있음을 나타낸다. 디폴트 설정은 false이다.

```java
import repeatingAnnotations.InheritedAnnotationType;
import repeatingAnnotations.UninheritedAnnotationType;

@UninheritedAnnotationType
class A { }

@InheritedAnnotationType // @Inherited가 붙은 애노테이션
class B extends A {}

class C extends B {}

public class Test {
  public static void main(String[] args) {
    System.out.println(new A().getClass().getAnnotation(InheritedAnnotationType.class));
    System.out.println(new B().getClass().getAnnotation(InheritedAnnotationType.class));
    System.out.println(new C().getClass().getAnnotation(InheritedAnnotationType.class));
    System.out.println("_________________________________");
    System.out.println(new A().getClass().getAnnotation(UninheritedAnnotationType.class));
    System.out.println(new B().getClass().getAnnotation(UninheritedAnnotationType.class));
    System.out.println(new C().getClass().getAnnotation(UninheritedAnnotationType.class));
  }
}
```

출력 결과

```
null
@repeatingAnnotations.InheritedAnnotationType()
@repeatingAnnotations.InheritedAnnotationType()
_________________________________
@repeatingAnnotations.UninheritedAnnotationType()
null
null
```

클래스 B를 확인해보면 클래스 A를 상속받고 있지만 UninheritedAnnotationType 애노테이션을 가지고 있지 않다.
UninheritedAnnotationType은 @Inherited가 붙지 않아 상속되지 않은 것이다.
클래스 C를 확인해보면 클래스 B를 상속받고 있는데 InheritedAnnotationType 애노테이션을 가지고 있다.
C에는 아무 애노테이션도 안붙어 있는데, 수퍼 클래스인 B에서 @Inherited가 붙은 InheritedAnnotationType 애노테이션을 가지고 있어 상속되었다.

#### @Repeatable
자바8부터 도입되었다. 이 애노테이션을 가진 애노테이션은
같은 declaration 또는 type use(자바8부터 type use에도 애노테이션이 붙을 수 있게 되었다)에 한 번 이상 적용될 수 있다.

```java
@Repeatable(Authors.class)
@interface Author {
    String name();
    String date();
}

@Author(
        name = "Benjamin Franklin",
        date = "3/27/2003"
)
@Author(
        name = "kobe",
        date = "24/8/2020"
)
class MyClass {}
```

`@Repeatable` 없이 애노테이션을 두 번 이상 반복하면 컴파일 에러가 발생한다.
<br>`java: ... is not a repeatable annotation type`

### 참고
[JAVA 애노테이션에 대해 알아보자](https://live-everyday.tistory.com/201)
<br>[JAVA SE 8 이후의 Annotations](https://live-everyday.tistory.com/202)
<br>[어노테이션 프로세서](https://www.baeldung.com/java-annotation-processing-builder)
<br>Java in a nutshell 7th