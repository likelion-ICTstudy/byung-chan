import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class dot {
    int x , y;

    dot(int x , int y) {
        this.x = x;
        this.y = y;
    }
}

public class boj치즈 {

    static StringTokenizer st;
    static int[][] grid;
    static boolean[][] checked;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int N , M , cheeseCount , time;
    static ArrayList<dot> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 1인 모든 부분을 검사
        // 2. 1인 부분의 4면을 검사 -> 만약 4면 중 2개 이상일 경우 copy[i][j] = 0 으로 교체 -> 모두 검사 time++;
        // 3. 다음 스텝 copy를 본 배열에 clone 그후 반복
        // 4. 모든 요소가 0 이면 time을 출력하고 종료.

        // 2번에서 틀림 --> 치즈 내부의 공기도 0 이기 때문에 틀림.... --> 밖의 공기만 다른 수로 변경 ?
        // 밖의 공기를 2로 변경 시도

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cheeseCount = 0;
        time = 0;

        grid = new int[N][M] ;
        checked = new boolean[N][M];
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                grid[i][j] = num;
                if (num == 1) {
                    list.add(new dot(i,j));
                    cheeseCount++;
                }
            }
        }

        while(cheeseCount != 0) { // 시간초과..... ( 4면을 검사하는 checking 메서드 구현 --> 시간 초과 --> 모든 인덱스를 접근하기 떄문에
                                  // 치즈가 있는 위치만 저장하는 객체 생성 --> dot() ;
            checked = new boolean[N][M];
            dfs(0, 0);

            for (int i = 0; i < list.size(); i++) {
                int x = list.get(i).x;
                int y = list.get(i).y;

                int cnt = 0; // 2와 접촉한 면의 개수
                for (int k = 0; k < 4; k++) { // 4면을 검사
                    int nextI = dx[k] + x;
                    int nextJ = dy[k] + y;
                    if (grid[nextI][nextJ] == 2) {
                        cnt++;
                    }
                }

                if (cnt >= 2){
                    grid[x][y] = 0;
                    cheeseCount--;
                    list.remove(i);
                    i--; // ㅑ의 값을 제거 했으므로 다음i 를 받기 위해서 .
                }
            }
            time++;
        }
        System.out.println(time);
    }


    private static void dfs(int x, int y) {
        checked[x][y] = true; // 방문을 했다.
        grid[x][y] = 2;

        for (int i = 0; i < 4; i++) {
            int nextX = dx[i] + x;
            int nextY = dy[i] + y;

            if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) { // 다음의 x,y 값의 위치가 배열을 벗어 난다
                continue;
            }

            if (grid[nextX][nextY] == 1 || checked[nextX][nextY]) { // 다음의 x,y 값이 치즈가 있는 곳이다.  + grid[nextX][nextY]를 방문 했는지.
                continue;
            }
            dfs(nextX, nextY);
        }
    }
}
