package liveStudy.queue;
public interface MyQueue {

    void offer(int data);
    Integer poll();
    Integer peek();
    int size();

}
