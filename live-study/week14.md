## 14주차 live-study 목차
- [제네릭 등장 배경](#제네릭-등장-배경)
- [제네릭](#제네릭)
- [Type Parameter](#Type-Parameter)
- [Diamond Syntax](#Diamond-Syntax)
- [Type Erasure](#Type-Erasure)
- [Bounded Type Parameters](#Bounded-Type-Parameters)
- [Wildcards](#Wildcards)
- [Bounded Wildcards](#Bounded-Wildcards)
- [제네릭 메소드](#제네릭-메소드)
- [참고](#참고)

#### 제네릭 등장 배경
초기 버전의 컬렉션은 저장되는 데이터의 타입을 드러내지 못한다는 한계가 있었다.
제네릭은 이런 한계를 해결하기 위해 등장했다. 다음의 상황을 생각해보자.

`Shape`를 담는 컬렉션을 만들고 싶을 때 `List`를 이용해서 다음과 같이 담아낼 수 있다.

```java
// Shape 객체를 담을 리스트 생성
List shapes = new ArrayList();

// 리스트에 Shape 객체 추가
shapes.add(new CenteredCircle(1.0, 1.0, 1.0));
shapes.add(new CenteredSquare(2.5, 2, 3));

// List::get()는 Object를 리턴하므로
// CenteredCircle 타입으로 받기 위해서는 반드시 캐스트해야 한다
CenteredCircle c = (CentredCircle)shapes.get(0);
// 1번 인덱스에는 CenteredSquare 객체가 들어가 있는데
// CenteredCircle 객체로 캐스트 시도하므로 런타임 에러가 발생한다
CenteredCircle c = (CentredCircle)shapes.get(1);
```

위의 코드에서 리스트는 자신이 어떤 타입의 데이터를 담고 있는지 모른다는 문제가 있다.
때문에 리스트에서 `Shape` 객체를 꺼내오려면 반드시 캐스트가 일어나야 한다.
뿐만 아니라 다른 타입의 객체를 같은 리스트에 넣을 수 있는데,
그럼에도 잘못된 캐스트가 일어나기 전까지 프로그램은 정상적으로 돌아갈 것이다.

#### 제네릭
리스트가 자신이 무엇을 담고 있는지 알고 있게 할 수는 없을까?
그래서 런타임까지 가지 않고 컴파일 타임에 javac가 `List` 메소드로
부적절한 인자가 전달됐을 때 이를 감지하고 컴파일 에러를 발생시키게 할 수는 없을까?

자바는 컬렉션이 단일한 타입의 객체만 담을 수 있도록 해주는,
즉 어떤 타입의 객체가 담겨있는지를 표시해주는 단순한 문법을 제공해준다.
이를 제네릭이라고 하며 다음과 같이 사용한다.

```java
// CenteredCircle 타입의 객체만 담는 리스트 생성
List<CenteredCircle> shapes = new ArrayList<CenteredCircle>();

// 리스트에 CenteredCircle 객체 추가
shapes.add(new CenteredCircle(1.0, 1.0, 1.0));

// 앞선 예제와 달리 컴파일 에러 발생
shapes.add(new CenteredSquare(2.5, 2, 3));

// List<CenteredCircle>::get()는 앞선 예제와 달리 Object 객체가 아니라
// CenteredCircle를 리턴하므로 캐스트할 필요 없음 
CenteredCircle c = shapes.get(0);
```

`<>` 안에 들어가는 타입을 제네릭 타입이라고 부르며 다음과 같이 선언할 수 있다.

```java
interface Box<T> {
    void box(T t);
    T unbox();
}
```

위와 같은 선언은 `Box`라는 인터페이스가 어떤 타입이든 담아낼 수 있는 제네럴한 컨테이너임을 나타낸다.
이 자체로 완전한 인터페이스는 아니며 `T` 타입을 대체할 수 있는 각 타입을 담아내낼 모든 인터페이스에 대한 제네럴한 디스크립션에 가깝다.

#### Type Parameter
`<T>`와 같은 문법은 타입 매개변수라는 특별한 이름으로 불린다.
`Map<String, Integer>`와 같이 사용함으로써 타입 매개변수에 구체적인 값을 할당할 수 있다.
단, 타입 매개변수의 값으로 프리미티브 타입이 올 수는 없다.

#### Diamond Syntax
제네릭 타입의 인스턴스를 생성할 때 assignment statement의 righthand side는
타입 매개변수의 값을 반복할 뿐이므로 불필요하다. 생략하더라도 컴파일러가 추론할 수 있기 때문이다.
최신 버전의 자바는 diamond syntax라 불리는 문법에서 반복되는 타입 값은 다음과 같이 생략할 수 있다.

`List<CenteredCircle> shapes = new ArrayList<>();`

#### Type Erasure
자바 플랫폼의 강점 중 하나가 바로 backward compatibility이다.
제네릭은 Java 5에 등장했는데 그러면 그 이전의 코드, 즉 제네릭이 없는 컬렉션 클래스(raw type이라고 한다)를 허용하는 타입 시스템과의
compatibility는 어떻게 유지할 수 있을까? 아래와 같이 캐스팅을 이용하면 된다.

```java
List someThings = getSomeThings();
// someThings이 String을 담고 있다는 것을 안다고 가정한다
// someThings가 만약 Integer를 담고 있다고 해도 컴파일 에러는 발생하지 않으므로 주의해야 한다
List<String> myStrings = (List<String>)someThings;
```

위의 코드는 적어도 어떤 레벨에서는 `List`와 `List<String>`가 호환 가능한 타입임을 뜻한다.
어떻게 두 개의 타입이 호환 가능한 것으로 인식될까? 자바는 type erasure를 통해 위의 두 타입이 호환성을 갖도록 만들었다.

Type erasure란 제네릭을 지워내는 것이다. `List<String>`는 type erasure를 거치면 `List`가 된다.
제네릭 타입 매개변수는 오직 컴파일 타임에서만 보이고, javac에 의해 제거되어 바이트코드에는 반영되지 않는다.

```java
// 컴파일 에러
interface OrderCounter {
    int totalOrders(Map<String, List<String>> orders);
    int totalOrders(Map<String, Integer> orders);
}
```

두 개의 메소드는 정상적인 메소드 오버로딩처럼 보이지만 type erasure를 거치면
두 메소드 모두 메소드 시그니처가 `int totalOrders(Map);`가 되기 때문에 컴파일 에러가 발생한다.

#### Bounded Type Parameters
```java
public class Box<T> {
    protected T value;
    public void box(T t) {
        value = t;
    }
    public T unbox() {
        T t = value;
        value = null;
        return t;
    }
}
```

위와 같은 `Box` 클래스가 있을 때 만약 박스에 오직 숫자만 담길 수 있도록 제한하고 싶다면 어떻게 해야할까?
타입 매개변수에 bound를 사용하면 이런 목적을 달성할 수 있다.
Bound란 타입 매개변수의 값으로 쓰일 수 있는 타입을 제한하는 능력을 말한다.
다음은 박스에 들어오는 타입을 `Number`라고 제한한 예시이다.

```java
public class NumberBox<T extends Number> extends Box<T> {
    public int intValue() {
        return value.intValue();
    }
}
```

`T extends Number`는 T가 오직 `Number` 또는 `Number`의 하위 타입이어야 한다는 뜻이다.
이로써 컴파일러는 `Box` 클래스의 T 타입 변수 `value`가 `intValue()` 메소드를 가질 것이라고 확신할 수 있다.
만약 타입 매개변수에 엉뚱한 값을 넣어 `NumberBox` 클래스의 인스턴스를 생성하려고 하면 컴파일 에러가 발생한다.

```java
NumberBox<Integer> ni = new NumberBox<>();
NumberBox<Object> no = new NumberBox<>(); // 컴파일 에러
```

#### Wildcards
`ArrayList<T>`와 같이 매개변수가 남아있는 타입은 인스턴스를 만들 수 없다.
`T`는 타입 매개변수로 진짜 타입을 위한 placeholder에 불과하기 때문이다.
우리가 타입 매개변수에 구체적인 값을 줄 때야 비로소 완전한 구색을 갖춘, 인스턴스화 가능한 타입이 된다.

만약 우리가 컴파일 타임에는 알 수 없는 타입을 사용해야 한다면?
구체적인 값을 주기 전에는 인스턴스화 불가능하다고 했으니 사용할 수 없는 것일까?
자바의 와일드카드를 이용하면 제네릭에 컴파일 타임에 알 수 없는 타입을 사용할 수 있다.

```java
ArrayList<?> mysteryList = unknownList();
Object o = mysteryList.get(0);
```

#### Bounded Wildcards
컴파일 타임에는 특정할 수 없으나 반드시 특정 클래스 또는 그 자식 타입만을 사용해야 한다면  bounded 와일드카드를 이용하면 된다.
예를 들어 어떤 타입인지는 특정할 수는 없지만 반드시 `List`를 구현해야 하는 타입이라면
타입 매개변수 자리에 `? extends List`와 같이 bounded 와일드카드를 사용할 수 있다.
이때 와일드카드를 제한하는 타입은 클래스든 인터페이스든 `extends`라는 키워드를 사용한다.  

```java
// Cat은 Pet의 자식 클래스 중 하나
List<Cat> cats = new ArrayList<Cat>();
List<? extends Pet> pets = cats; // pets는 Pet 자신 또는 자식 클래스 중 하나의 타입만 담는 컬렉션을 가리킨다

pets.add(new Cat()); // 컴파일 에러: Cat은 Pet의 자식이긴 하지만 pets가 Cat을 담고 있다는 보장이 없다
pets.add(new Pet()); // 컴파일 에러: pets가 담고 있는 것이 Pet이라는 보장이 없다
cats.add(new Cat()); // cats는 Cat을 담겠다고 명시적으로 선언되어 있다
```

`extends`라는 키워드가 타입 매개변수의 상한이라면 `super`를 이용하여 타입 매개변수의 하한을 정할 수 있다.
`? super Cat`와 같이 사용하면 타입 매개변수에 들어오는 타입 값은 반드시 `Cat` 클래스 자신 또는 부모 클래스여야만 한다.

```java
List<Pet> pets = new ArrayList<>();
List<? super Cat> cats = pets; // cats는 Cat 자신 또는 부모 클래스 중 하나의 타입만 담는 컬렉션을 가리킨다

cats.add(new Cat()); // cats는 Cat 또는 Cat의 부모만 담을 수 있는데 어떤 부모든 다형성에 의해 Cat을 담을 수 있다
cats.add(new Pet()); // 컴파일 에러: cats가 담고 있는 것이 Pet이라는 보장이 없다
```

#### 제네릭 메소드
메소드에서 제네릭을 사용하기 위해서는 리턴 타입 앞에 `<T>`와 같이 타입 매개변수를 두면 된다.
```java
// 리턴 타입과 메소드 매개변수 모두에서 사용되는 경우
public <T> T both(T param) {
    return param;
}
// 메소드 매개변수에서만 사용되는 경우
public <T> String onlyParam(T param) {
    return "param";
}
```

메소드 정의에 등장하는 `<T>`는 클래스의 제네릭과는 무관한다.
즉 메소드가 제네릭을 가졌다고 해서 클래스가 제네릭을 가질 필요도,
만약 클래스가 제네릭을 가진다고 해도 매개변수가 꼭 `<T>`일 필요도 없다.
참고로 클래스의 타입 매개변수와 메소드에서 선언한 타입 매개변수의 이름이 같을 경우
메소드에서 사용하는 타입 매개변수는 메소드에서 선언한 타입 매개변수이다.

### 참고
Java in a Nutshell 7th