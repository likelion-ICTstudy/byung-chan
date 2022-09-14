package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 참조
// https://st-lab.tistory.com/95

public class 별찍기 {

    static char[][] star; // 별을 표시할 2차원 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        star = new char[n][n];

        starDraw(0,0, n,false);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(star[i][j]);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    // 재귀 함수
    static void starDraw(int x, int y, int size, boolean blank){

        // 빈칸이라면
        // 해당 사이즈에 접근해서 빈칸으로 설정하기
        if (blank == true) {
            for (int i = x; i < x + size; i++) {
                for (int j = y; j < y + size; j++) {
                    star[i][j] = ' ';
                }
            }
            return; // 재귀함수 종료
        }

        // 더이상 나눌 수가 없다면
        if (size == 1) {
            star[x][y] = '*';
            return; // 재귀함수 종료
        }

        // 분할 -> size / 3
        int divSize = size / 3; // 27 -> 9 - > 3
        int count = 0; // 3 * 3 일 경우 (0,0) , (0,1) , (0,2) , (1,0) , (1,1) 5번쨰 --> (1,1) 이 빈칸이다.

        for (int i = x; i < x + size; i += divSize) {
            for (int j = y; j < y + size; j += divSize) {
                count++;
                if (count == 5) { // (1,1) 의 위치이다  -> 빈칸이다.
                    starDraw(i , j , divSize , true);
                } else {
                    starDraw(i , j , divSize , false);
                }
            }
        }
    }
}
