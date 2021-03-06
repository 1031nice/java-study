interface IntHolder {
    public int getValue();
}


public class Weird {
    public static void main(String[] args) {
        IntHolder[] holders = new IntHolder[10];
        for (int i = 0; i < 10; i++) {
            int fi = i;
            holders[i] = () -> {
                return fi; // 컴파일 에러: variable used in lamda expression should be final or effectively final
            };
            fi = 1; // fi는 더이상 effectively final이 아님
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(holders[i].getValue());
        }
    }
}

@FunctionalInterface
interface Add {
    int add(int a1, int a2);
//    default int sub(int a1, int a2) {return -1; }
}