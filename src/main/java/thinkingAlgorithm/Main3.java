package thinkingAlgorithm;

import java.util.Scanner;

public class Main3 {

    /*
    1. 기약분수로 만들고
    2. 최대공약수를 찾고
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfItems = 0;
        numOfItems = scanner.nextInt();
        int parent = 0;
        int child = 0;

        if(numOfItems == 1){
            child = scanner.nextInt();
            parent = scanner.nextInt();
            System.out.println("1 " + parent / gcm(child, parent));
        }
        else {
            int gcm = 0;
            int[][] arr = new int[numOfItems][2];
            for (int i = 0; i < numOfItems; i++) {
                child = scanner.nextInt();
                parent = scanner.nextInt();
                gcm = gcm(child, parent);
                if (gcm != 1) {
                    child /= gcm;
                    parent /= gcm;
                }
                arr[i][0] = child;
                arr[i][1] = parent;
            }

            int temp = 0;
            int lcm = lcm(arr[0][1], arr[1][1]);
            for (int i = 2; i < numOfItems; i++) {
                lcm = lcm(lcm, arr[i][1]);
            }

            System.out.println("1 " + lcm);
        }
    }

    public static int lcm(int a, int b){
        return Math.abs( (a * b) / gcm(a, b) );
    }

    public static int gcm(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return Math.abs(a);
    }
}
