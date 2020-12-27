import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class CopyTest implements Serializable {

    class Person implements Serializable, Cloneable {
        String name;
        int age;
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age &&
                    Objects.equals(name, person.name);
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    @Test
    public void shallowCopy() {
        Person original = new Person("Person-A", 1);
        Person copy = original;
        assertThat(copy).isEqualTo(original);

        // 변경
        copy.name = "change";
        copy.age = 2;
        assertThat(copy).isEqualTo(original);
    }

    @Test
    public void shallowCopyArray() {
        Person[] original = new Person[] {new Person("Person-A", 1),
                new Person("Person-B", 2),
                new Person("Person-C", 3)};

        // 얕은 복사 방법1
        // Arrays의 clone은 public으로 이미 오버라이드 되어 있는 듯하다
        // 또한 얕은 복사이므로 객체에 대한 clone이 일어나지 않으므로 Person은 Cloneable을 구현하지 않아도 CloneNotSupportedException 발생 X
        Person[] copy = original.clone();
        // 얕은 복사 방법2
        // Person[] copy = original;
        // 얕은 복사 방법3
        // Person[] copy = new Person[3];
        // System.arraycopy(original, 0, copy, 0, 3); // 보다 효율적으로 구현되어 있다고 한다
        assertThat(copy).isEqualTo(original);

        // 변경
        copy[1].name = "Person-D";
        copy[1].age = 4;
        assertThat(copy).isEqualTo(original);
    }

    @Test
    public void deepCopy() throws CloneNotSupportedException {
        Person original = new Person("Person-A", 1);
        Person copy = (Person) original.clone(); // 단, Person은 Cloneable을 구현해야 하고, clone을 public으로 오버라이드 해야함
        assertThat(copy).isEqualTo(original);

        // 변경
        copy.name = "change";
        copy.age = 2;
        assertThat(copy).isNotEqualTo(original);
    }

    @Test
    public void deepCopyArray() {
        Person[] original = new Person[] {new Person("Person-A", 1),
                                          new Person("Person-B", 2),
                                          new Person("Person-C", 3)};

        // 깊은 복사(Person은 Serializable을 구현해야 한다)
        // 이너 클래스인 경우 아우터 클래스도 Serializable을 구현해야 하나보다.
        Person[] copy = SerializationUtils.clone(original);
        assertThat(copy).isEqualTo(original);

        // 변경
        copy[1].name = "Person-D";
        copy[1].age = 4;
        assertThat(copy).isNotEqualTo(original);
    }

}
