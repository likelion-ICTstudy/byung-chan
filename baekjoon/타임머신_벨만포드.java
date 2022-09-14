package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 타임머신_벨만포드 {

    static int N , M;
    static Bus[] bus;
    static long[] dist;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        bus = new Bus[M];

        // 출발 노드 설정
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken()); // weight

            bus[i] = new Bus(A, B, C);
        }

        // 최단 거리 테이블 초기화
        dist = new long[N + 1];
        for (int i = 1; i < N+1; i++) {
            dist[i] = INF;
        }

        // 벨만포드 실행 (true: 음수 간선 순환 존재, false : 음수 간선 순환 존재 x )
        if (bellmanford(1)) {
            System.out.println(-1);
        } else {
            // 1번 노드를 제외한 다른 모든 노드로 가기 위한 최단거리 출력
            for (int i = 2; i < N+1; i++) {
                if (dist[i] == INF) { // 도달할 수 없다면 -1
                    System.out.println("-1");
                } else { // 최단 거리 출력
                    System.out.println(dist[i]);
                }
            }
        }

    }

    private static boolean bellmanford(int start) {
        dist[start] = 0;

        // n 번 반복 (음수 간선 순환 체크하지 않으려면 n-1번 반복)
        for (int i = 0; i < N; i++) {
            // 매 반복마다 모든 간선을 확인
            for (int j = 0; j < M; j++) {
                int pos = bus[j].from;
                int nextPos = bus[j].to;
                int time = bus[j].weight;

                if (dist[pos] == INF) continue;

                if (dist[nextPos] > dist[pos] + time) {
                    dist[nextPos] = dist[pos] + time;

                    if (i == N-1) { return true; }
                }
            }
        }
        return false;
    }
}

class Bus {
    int from;
    int to;
    int weight;
    public Bus(int from , int to , int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
