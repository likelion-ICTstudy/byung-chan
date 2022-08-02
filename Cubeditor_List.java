import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// https://bowbowbow.tistory.com/6#comment5168448
// https://www.youtube.com/watch?v=9bkbV-VANQ0&t=5s
// 위에 블로그 ,  아래 유튜브  // 알고리즘
public class Cubeditor_List {
    static List<String> pi;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine(); // 입력 받은 문자열

        // 입력받은 문자열에서 부분 문자열을 찾아야함.
//        문자열 내에서 부분 문자열이 2번 이상 나오는 것중 길이가 가장 긴 것을 구하는 프로그램을 구현하시오.
        pi = new ArrayList<>();
        int len = str.length();
        int ans = 0;

        for (int i = 0; i < len; i++) {
            String input = str.substring(i, len);
            ans = Math.max(ans, kmp(input));
        }

        System.out.println(ans);

    }

    private static int kmp(String str) {
        int length = str.length();
        int returnValue = 0;

        for (int i = 0; i < str.length(); i++) {
            pi.add(str.substring(0, i));
            String comparedStr = str.substring(length - i, length);
            returnValue = (pi.get(i).equals(comparedStr) ? comparedStr.length() : returnValue);
        }
        pi.clear();

        return returnValue;
    }
}
