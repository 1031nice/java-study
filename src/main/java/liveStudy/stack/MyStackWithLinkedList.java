package liveStudy.stack;

import liveStudy.linkedList.ListNode;
import liveStudy.linkedList.MyLinkedList;

public class MyStackWithLinkedList implements MyStack {
    private MyLinkedList list = new MyLinkedList();
    ListNode head;
    int size = 0;

    public ListNode getHead() {
        return head;
    }
    public void setHead(ListNode head) {
        this.head = head;
    }
    public int size() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    public void push(int data) {
        if(this.size() == 0) {
            head = new ListNode(data);
            this.setSize(this.size() + 1);
        }
        else {
            list.add(head, new ListNode(data), this.size());
            this.setSize(this.size() + 1);
        }
    }

    public int pop() {
        int ret = list.remove(head, this.size()-1).getValue();
        this.setSize(this.size()-1);
        return ret;
    }
}
