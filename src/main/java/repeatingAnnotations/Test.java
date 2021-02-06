package repeatingAnnotations;

public class Test {

    @SuppressWarnings(value = "unchecked")
    void myMethod(){}

    @Override
    public String toString() {
        return super.toString();
    }

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

@UninheritedAnnotationType
class A {}

@InheritedAnnotationType
class B extends A {}

class C extends B {}

@Author(
        name = "Benjamin Franklin",
        date = "3/27/2003"
)
@Author(
        name = "kobe",
        date = "24/8/2020"
)
class MyClass {}