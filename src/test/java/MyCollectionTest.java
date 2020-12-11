import liveStudy.linkedList.ListNode;
import liveStudy.linkedList.MyLinkedList;
import liveStudy.queue.MyQueue;
import liveStudy.queue.MyQueueWithLinkedList;
import liveStudy.stack.MyStack;
import liveStudy.stack.MyStackWithArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyCollectionTest {

    @Test
    void stack(){
        MyStack stack = new MyStackWithArray();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(stack.size(), 3);
        assertEquals(stack.pop(), 3);
        assertEquals(stack.pop(), 2);
        assertEquals(stack.pop(), 1);
        assertEquals(stack.size(), 0);
    }

    @Test
    void linkedList(){
        MyLinkedList list = new MyLinkedList();

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        list.add(node1, node2, 0);
        list.add(node2, node3, 0);
        list.add(node3, node4, 2);
        list.add(node3, node5, 4);
        assertEquals(list.remove(node3, 3).getValue(), 1);
        assertEquals(list.remove(node3, 1).getValue(), 2);
        assertEquals(list.remove(node3, 2).getValue(), 5);
        assertTrue(list.contains(node3, node4));
    }

    @Test
    void queue(){
        MyQueue queue2 = new MyQueueWithLinkedList();
        for(int i=0; i<5; i++) {
            queue2.offer(i+1);
        }
        int beforeSize = queue2.size();
        int index;
        for(index = 0; index < 3; index++){
            assertEquals(queue2.poll(), index+1); // first in first out이 맞는지
        }
        int afterSize = queue2.size();
        assertEquals(afterSize, beforeSize-(index)); // poll한 만큼 크기가 변했는지
        assertEquals(queue2.peek(), index+1); // peek이 다음 차례의 원소를 제대로 가리키고 있는지
        assertEquals(afterSize, beforeSize-(index)); // peek 이후에 사이즈가 변하지 않는지
    }

}
