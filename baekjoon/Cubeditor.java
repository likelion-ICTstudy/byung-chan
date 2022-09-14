package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cubeditor {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine(); // 입력 받은 문자열

        // 입력받은 문자열에서 부분 문자열을 찾아야함.
//        문자열 내에서 부분 문자열이 2번 이상 나오는 것중 길이가 가장 긴 것을 구하는 프로그램을 구현하시오.
        int len = str.length();
        int ans = 0;

        for (int i = 0; i < len; i++) {
            String input = str.substring(i, len);
            ans = Math.max(ans, kmp(input));
        }

        System.out.println(ans);

    }

    private static int kmp(String str) {
        int[] pi = new int[str.length()];
        int index = 0;
        int length = str.length();
        int returnValue = 0;

        for(int i = 1; i < length; i++) { // i : 우측(접미사) 비교 문자열 인덱스
            while(index > 0 && str.charAt(i) != str.charAt(index)) {
                index = pi[index - 1];
            }

            if(str.charAt(i) == str.charAt(index)) {
                pi[i] = ++index;
                returnValue = Math.max(returnValue, pi[i]);
            }
        }

        return returnValue;
    }
}



