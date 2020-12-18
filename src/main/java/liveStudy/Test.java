package liveStudy;

import liveStudy.linkedList.ListNode;
import liveStudy.linkedList.MyLinkedList;
import liveStudy.queue.MyQueue;
import liveStudy.queue.MyQueueWithArray;
import liveStudy.queue.MyQueueWithLinkedList;
import liveStudy.stack.MyStack;
import liveStudy.stack.MyStackWithArray;
import liveStudy.stack.MyStackWithLinkedList;

public class Test {

    public static void main(String[] args) {
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
        list.remove(node3, 3);
        ListNode remove = list.remove(node3, 0);
        list.print(remove);
        System.out.println(list.contains(remove, node3));
        System.out.println(list.contains(remove, node5));
        System.out.println(list.contains(remove, node2));

        MyQueue queue = new MyQueueWithArray();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        System.out.println("Queue with array");
        for(int i=0; i<5; i++){
            System.out.println("poll: " + queue.poll());
            if(i == 2)
                System.out.println("peek: " + queue.peek());
        }

        MyQueue queue2 = new MyQueueWithLinkedList();
        queue2.offer(1);
        queue2.offer(2);
        queue2.offer(3);
        queue2.offer(4);
        queue2.offer(5);
        System.out.println("Queue with linked list");
        for(int i=0; i<5; i++){
            System.out.println("poll: " + queue2.poll());
            if(i == 1)
                System.out.println("peek: " + queue2.peek());
        }

        MyStack stack = new MyStackWithArray();
        stack.push(5);
        stack.push(1);
        stack.push(2);
        stack.push(4);
        stack.push(6);
        System.out.println("Stack with array");
        for(int i=0; i<5; i++){
            System.out.println(stack.pop());
        }

        MyStack stack2 = new MyStackWithLinkedList();
        stack2.push(5);
        stack2.push(1);
        stack2.push(2);
        stack2.push(4);
        stack2.push(6);
        System.out.println("Stack with linked list");
        for(int i=0; i<5; i++){
            System.out.println(stack2.pop());
        }
    }
}
