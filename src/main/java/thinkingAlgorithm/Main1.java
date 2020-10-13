package thinkingAlgorithm;

import java.util.Scanner;

public class Main1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = null;
        int numOfProb = 0;
        input = scanner.nextLine();
        if(input.equals("고무오리 디버깅 시작")) {
            while (true) {
                input = scanner.nextLine();
                if (input.equals("고무오리 디버깅 끝"))
                    break;
                else if (input.equals("문제"))
                    numOfProb++;
                else if (input.equals("고무오리"))
                    if (numOfProb == 0)
                        numOfProb += 2;
                    else
                        numOfProb--;
            }
            if(numOfProb > 0)
                System.out.println("힝구");
            else
                System.out.println("고무오리야 사랑해");

        }
    }

}
