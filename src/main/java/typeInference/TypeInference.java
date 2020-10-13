package typeInference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.LongAccumulator;

public class TypeInference {

    static <T> T pick(T a1, T a2) { return a2; }
    public static void main(String[] args) {
        Serializable d = pick("d", new ArrayList<String>());

        Map<String, List<String>> myMap1 = new HashMap<String, List<String>>();
        Map<String, List<String>> myMap2 = new HashMap<>();

        Map<String, List<String>> myMap = new HashMap();

    }
}

class MyClass<X> {
    <T> MyClass(T t) {
        // ...
    }

    public static void main(String[] args) {
        MyClass<Integer> myObject = new <String>MyClass<Integer>("");

    }
}

