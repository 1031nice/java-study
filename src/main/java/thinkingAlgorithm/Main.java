package thinkingAlgorithm;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfPlayers = 0;
        int limit = 0;

        PriorityQueue<String> waitingQ = new PriorityQueue<>();
        PriorityQueue<String> inputQ = new PriorityQueue<>();
        Map<String, Integer> playerMap = new HashMap<>();

        numOfPlayers = scanner.nextInt();
        limit = scanner.nextInt();

        for(int i=0; i<numOfPlayers; i++){
            int level = scanner.nextInt();
            String name = scanner.next();
            inputQ.offer(name);
            playerMap.put(name, level);
        }

        ArrayList<ArrayList<String>> roomList = new ArrayList<>();

        int roomLevel = -10;
        int num = 0;
        boolean flag = true;
        PriorityQueue<String> temp = new PriorityQueue<>();
        while(true) {
            for (; inputQ.size() > 0;) {
                flag = false;
                String name = inputQ.poll();
                Integer level = playerMap.get(name);
                if (roomLevel == -10) {
//                    System.out.println("Started!");
                    roomList.add(new ArrayList<>());
                    roomLevel = level;
                }
                if ((roomLevel + 10) >= level && (roomLevel - 10) <= level) {
                    ArrayList<String> room = (ArrayList<String>) roomList.get(roomList.size() - 1);
                    room.add(name);
//                    System.out.println(level + " " + name);
                    num++;
                }
                else {
                    waitingQ.offer(name);
                }
                if (num == limit) {
                    num = 0;
                    flag = true;
                }
            }
            roomLevel = -10;
            if(waitingQ.size() == 0)
                break;
            temp = inputQ;
            inputQ = waitingQ;
            waitingQ = temp;
        }
        for(int i=0; i<roomList.size(); i++){
            roomList.get(i).sort(String::compareTo);
        }
        for(int i=0; i<roomList.size(); i++){
            System.out.println("Started!");
            for(int j=0; j<roomList.get(i).size(); j++){
                String name = roomList.get(i).get(j);
                System.out.println(playerMap.get(name) + " " + name);
            }
        }
        if(!flag)
            System.out.println("Waiting!");
    }

}
