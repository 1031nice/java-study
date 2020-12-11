package liveStudy.linkedList;

public class MyLinkedList {

    public ListNode add(ListNode head, ListNode nodeToAdd, int position){
        ListNode tail = findTail(nodeToAdd);
        if(position < 0) // position이 유효하지 않음
            return null;
        else if(position == 0) {
            tail.next = head;
            return nodeToAdd;
        }
        ListNode before = head;
        for(int i=1; i<position; i++){
            before = before.next;
            if(before == null) // position이 유효하지 않음
                return null;
        }
        tail.next = before.next;
        before.next = nodeToAdd;
        return head;
    }

    public ListNode remove(ListNode head, int positionToRemove){
        ListNode before = head;
        ListNode ret = null;
        if(positionToRemove < 0)
            return null;
        else if(positionToRemove == 0){
            ret = head;
            head = head.next;
            return ret;
        }
        for(int i=1; i<positionToRemove; i++){
            before = before.next;
            if(before == null) // position이 유효하지 않음
                return null;
        }
        if(before.next == null) // position이 유효하지 않음
            return null;
        ret = before.next;
        before.next = before.next.next;
        return ret;
    }

    public boolean contains(ListNode head, ListNode nodeToCheck){
        while(head != null){
            if(head.getValue() == nodeToCheck.getValue())
                return true;
            head = head.next;
        }
        return false;
    }

    public void print(ListNode head){
        while(head != null){
            System.out.print(head.value + " ");
            head = head.getNext();
        }
    }

    private ListNode findTail(ListNode node) {
        if(node == null)
            return null;
        while(node.next != null)
            node = node.next;
        return node;
    }
}