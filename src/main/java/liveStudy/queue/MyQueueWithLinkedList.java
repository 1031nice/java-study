package liveStudy.queue;

import liveStudy.linkedList.ListNode;
import liveStudy.linkedList.MyLinkedList;

public class MyQueueWithLinkedList  implements MyQueue {
    private MyLinkedList list = new MyLinkedList();
    ListNode head;
    int size = 0;

    public void offer(int data){
        if(size == 0) {
            head = new ListNode(data);
            size++;
        }
        else {
            list.add(head, new ListNode(data), size);
            size++;
        }
    }

    public Integer poll(){
        if(size == 0)
            return null;
        Integer ret = head.getValue();
        head = head.getNext();
        size--;
        return ret;
    }

    public Integer peek(){
        if(size == 0)
            return null;
        return head.getValue();
    }

    public int size(){
        return this.size;
    }
}
