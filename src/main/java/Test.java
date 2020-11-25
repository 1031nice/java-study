import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {

        int [] arr = {1, 2, 3};
        int index = 0;
        arr[index++] += 1;
        System.out.println(Arrays.toString(arr)); // [1, 0, 0]
        System.out.println(index); // 1

        int [] arr2 = {1, 2, 3};
        index = 0;
        arr2[index++] = arr2[index++] + 1; // 왼쪽 피연산자의 ++부터 수행
        System.out.println(Arrays.toString(arr2)); // [3, 2, 3]
        System.out.println(index); // 2


//        int [][] input = {{0,0,1,1},{1,1,1,1},{2,2,2,1},{0,0,0,2}};
//        int [][] input = {{0, 0, 1}, {2, 2, 1}, {0, 0, 0}};
//        int[] solution = Solution.solution(input);
//        for(int i=0; i<solution.length; i++){
//            System.out.print(" " + solution[i]);
//        }
//        String str = "abc";
//        str = str + "gerf";
//        System.out.println(str);

//        String str = new String("abc");
//        String str2 = str.toUpperCase();
//        System.out.println(System.identityHashCode(str));
//        System.out.println(System.identityHashCode(str2));
//        str = "abc";
//        System.out.println(System.identityHashCode(str));
//        str = new String("abc");
//        System.out.println(System.identityHashCode(str));
//        str = "abc";
//        System.out.println(System.identityHashCode(str));

//        ArrayList<String> li = new ArrayList<>();
//        li.add(new String("a"));
//        li.add(new String("b"));
//        for(String str : li)
//            str = str.toUpperCase();
//        for(String str : li)
//            System.out.println(str);
    }

}

class Solution {

    public static int[] solution(int[][] v) {
        int[] answer = new int [3];
        int[][] cache = new int[v.length][v[0].length];

        for(int i=0; i<v.length; i++){
            for(int j=0; j<v[0].length; j++){
                func(answer, v, cache, i, j, true, v[i][j]);
            }
        }

        return answer;

    }

    public static void func(int[] answer, int[][] v, int[][] cache, int i, int j, boolean isRoot, int from){
        if(i < 0 || j < 0 || i >= v.length || j >= v[0].length)
            return;
        else if(cache[i][j] == -1)
            return;
        else {
            if(isRoot) {
                answer[v[i][j]]++;
            }
            else{
                if(from != v[i][j]) {
                    return;
                }
            }
            cache[i][j] = -1;
            func(answer, v, cache, i+1, j, false, v[i][j]);
            func(answer, v, cache, i, j+1, false, v[i][j]);
            func(answer, v, cache, i-1, j, false, v[i][j]);
            func(answer, v, cache, i, j-1, false, v[i][j]);
        }
    }

//    public static String solution(String encrypted_text, String key, int rotation) {
//        int [] intKey = new int [key.length()];
//        String answer = "";
//
//        if(rotation > 0){
//            for(int i=0; i<rotation; i++){
//                char ch = encrypted_text.charAt(0);
//                encrypted_text = encrypted_text.substring(1) + ch;
//            }
//        }
//        else {
//            for(int i=0; i<-rotation; i++){
//                char ch = encrypted_text.charAt(encrypted_text.length()-1);
//                encrypted_text = ch + encrypted_text.substring(0, encrypted_text.length()-1);
//            }
//        }
//
//        System.out.println(encrypted_text);
//
//        // string key -> int key
//        for(int i=0; i<intKey.length; i++){
//            intKey[i] = ((int)key.charAt(i) - (int)'a' + 1);
//        }
//
//        int index = -1;
//        for(int i=0; i<encrypted_text.length(); i++){
//            index++;
//            int ch = (int)encrypted_text.charAt(i);
//            int move = intKey[index % intKey.length];
//            if(ch - move < (int)'a'){
//                ch = ch + 26 - move;
//            }
//            else
//                ch = ch - move;
//            answer = answer + (char)ch;
//        }
//        return answer;
//    }
//
//
//    public String solution(int n, int[][] delivery) {
//        int [] stock = new int [n]; // -1: 재고없음,  0: 모름, 1: 재고있음
//        String answer = "";
//
//        for(int i=0; i<delivery.length; i++){
//            if(delivery[i][2] == 1){ // 배송했다면 반드시 두 상품 모두 재고가 있다
//                stock[delivery[i][0]] = 1;
//                stock[delivery[i][1]] = 1;
//            }
//            else{ // 배송하지 않았다면 적어도 하나는 재고가 없다
//                if(stock[delivery[i][0]] == 1) // 첫 번째 상품이 재고가 있는 경우
//                    stock[delivery[i][1]] = -1; // 두 번째 상품은 반드시 재고가 없다
//                else if(stock[delivery[i][1]] == 1) // 두 번째 상품이 재고가 있는 경우
//                    stock[delivery[i][0]] = -1; // 두 번째 상품은 반드시 재고가 없다
//            }
//        }
//
//        String str = "abc";
//
//        for(int i=0; i<n; i++){
//            if(stock[i] == -1)
//                answer = answer + "x";
//            else if(stock[i] == 0)
//                answer = answer + "?";
//            else
//                answer = answer + "o";
//        }
//
//        return answer;
//    }
}