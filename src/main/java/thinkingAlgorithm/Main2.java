package thinkingAlgorithm;

import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sizeOfOrchard = 0;
        int max = -1001;
        int sumOfAll = 0;
        // input 이때 1x1 최대 크기랑, 전체 크기의 합도 계산해두자
        sizeOfOrchard = scanner.nextInt();

        // 시간초과로 실패하였으니 캐시를 사용하자
        int [][][] cache = new int [sizeOfOrchard][sizeOfOrchard][sizeOfOrchard];
        int [][] arr = new int[sizeOfOrchard][sizeOfOrchard];
        for(int i=0; i<sizeOfOrchard; i++){
            for(int j=0; j<sizeOfOrchard; j++){
                arr[i][j] = scanner.nextInt();
                cache[0][i][j] = arr[i][j];
                sumOfAll += arr[i][j];
                if(arr[i][j] > max)
                    max = arr[i][j];
            }
        }

        if(sizeOfOrchard == 1) // 과수원 크기 1인 경우
            System.out.println(max);
        else{ // 그 외의 경우
            int temp = 0; // 정사각형 속 모든 사과나무의 총이익의 합을 담을 임시 변수
            for(int i=2; i < sizeOfOrchard; i++){ // 정사각형 크기 2짜리부터 계산하면된다.
                for(int row=0; row < sizeOfOrchard-(i-1); row++) {
                    for (int col = 0; col < sizeOfOrchard-(i-1); col++) {
                        temp = getSumOfSquare(arr, cache, i, row, col);
                        if(temp > max)
                            max = temp;
                    }
                }
            }
            // input받을 때 가장 큰 정사각형 이미 구해뒀으니까
            if(sumOfAll > max)
                max = sumOfAll;
            System.out.println(max);
        }
    }

    private static int getSumOfSquare(int[][] arr, int[][][] cache, int squareSize, int i, int j) {
        int sum = cache[squareSize-2][i][j];
        if(sum != 0) {
            for (int row=0; row<squareSize; row++){
                sum += arr[i+row][j+squareSize-1];
            }
            for (int col=0; col<squareSize; col++){
                sum += arr[i+squareSize-1][j+col];
            }
            sum = sum - arr[i+squareSize-1][j+squareSize-1];
        }
        else {
            for (int p = 0; p < squareSize; p++) {
                for (int q = 0; q < squareSize; q++) {
                    sum += arr[i + p][j + q];
                }
            }
        }
        cache[squareSize-1][i][j] = sum;
        return sum;
    }
}
