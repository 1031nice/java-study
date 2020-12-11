package liveStudy.stack;

public class MyStackWithArray implements MyStack {
    private final static int DEFAULT_CAPACITY = 100;

    int[] arr = new int[DEFAULT_CAPACITY];
    int pointer = -1;

    public void push(int data) {
        arr[++pointer] = data;
    }

    public int pop() {
        return arr[pointer--];
    }

    public int size(){
        return pointer+1;
    }
}
