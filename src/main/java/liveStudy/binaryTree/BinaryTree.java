package liveStudy.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    public void dfs(Node node){
        if(node == null)
            return;
        dfs(node.left);
        System.out.print(node.value + " ");
        dfs(node.right);
    }

    public void bfs(Node node){
        if(node == null)
            return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()){
            Node poll = queue.poll();
            System.out.print(poll.value + " ");
            if(poll.left != null)
                queue.offer(poll.left);
            if(poll.right != null)
                queue.offer(poll.right);
        }
    }

}
