package liveStudy.queue;

public class MyQueueWithArray implements MyQueue {
    private final static int DEFAULT_CAPACITY = 100;
    Integer[] arr = new Integer[DEFAULT_CAPACITY];
    int pointer = 0;
    int size = 0;

    public void offer(int data){
        arr[size++] = data;
    }

    public Integer poll(){
        if(size == 0)
            return null;
        size = size - 1;
        return arr[pointer++];
    }

    public Integer peek(){
        if(size == 0)
            return null;
        return arr[pointer];
    }

    public int size(){
        return this.size;
    }
}
