import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class PredicateStudy {

    public static void main(String[] args) {

//        Supplier<String> supplier = () -> "hello, world";
//
//        System.out.println(new Supplier<String>(){
//            @Override
//            public String get() {
//                return "이건 되는데";
//            }
//        }.get());

//        System.out.println(() -> {
//            return "chaining처럼 람다 표현식 괄호로 감싸고 바로 .찍어서 사용하는건 안되는듯";
//        });
        Predicate<String> predicate = s -> (s.contains("ng"));
        ArrayList<String> list = new ArrayList<>();
        list.add("donghun");
        list.add("pangyo");
        list.add("java");

        Arrays.stream(list.toArray(new String[0])).filter(predicate).forEach(System.out::println);
    }

    public static String test1(String str){
        return str;
    }

    public static String test2(Supplier<String> supplier){
        return supplier.get();
    }

}

// https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html#toArray-T:A-

/*
        Predicate<String> predicate = s -> (s.contains("ng"));
        ArrayList<String> list = new ArrayList<>();
        list.add("donghun");
        list.add("pangyo");
        list.add("java");

        Arrays.stream(list.toArray(new String[0])).filter(predicate).forEach(System.out::println);

 */