package typeInference;

import java.util.*;

public class TargetType<T> {

    static <T> List<T> emptyList(){
        return new ArrayList<>();
    }

    void processStringList(List<String> stringList) {
        // process stringList
    }

    void test() {
        processStringList(Collections.<String>emptyList());
    }

    List<String> listOne = Collections.<String>emptyList();


    public static void main(String[] args) {

        Map myMap = new HashMap();
        Map<Integer, List<String>> myMap2 = myMap;
        Map<String, List<String>> myMap3 = myMap;

        myMap.put("asdf", "qwer");
        System.out.println(myMap.get("asdf"));
        myMap.put(3, "4");
        System.out.println(myMap.get(3));

    }

}