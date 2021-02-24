package liveStudy;

import java.util.ArrayList;
import java.util.List;

public class TypeErasure {

    public static void main(String[] args) {
        List<Cat> cats = new ArrayList<>();
        List<? extends Pet> pets = pets2; // pets에는 모르긴 몰라도 Pet의 자식 클래스 중 하나가 담긴다

        pets.add(new Cat()); // 컴파일 에러: Cat은 Pet의 자식이긴 하지만 pets가 Cat을 담고 있다는 보장이 없다
        pets.add(new Pet()); // 컴파일 에러: pets가 담고 있는 것이 Pet이라는 보장이 없다

        List<Pet> pets2 = new ArrayList<>();
        List<? super Cat> cats2 = pets2;

        cats2.add(new Cat());
        cats2.add(new Pet());
    }

}

class Test2<T> {

    public static <T> T test() {
    }

}
