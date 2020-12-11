package liveStudy.linkedList;

public class ListNode {
    Integer value;
    ListNode next;

    public ListNode(Integer value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public ListNode getNext() {
        return next;
    }
    public void setNext(ListNode next) {
        this.next = next;
    }

}
