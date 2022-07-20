import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 타임머신_실패 {

    static ArrayList<edge<Integer, Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<edge<Integer, Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new edge<>(0,0));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken()); // weight

            if (graph.get(A) != null) {
                int max = Math.max(graph.get(A).getWeight() , C);
                graph.get(A).setEdge(B, max);
            } else {
                graph.get(A).setEdge(B,C);
            }
        }

        int time = 0;

        dfs(1, time);
    }

    private static int dfs(int position , int totalTime) {
        for (edge<Integer, Integer> e : graph) {
            if (e.getPoint() == position + 1){
                totalTime += e.getWeight();
            }
            if ( totalTime <= 0) {
                return -1;
            }
            dfs(position + 1, totalTime);
        }
        return totalTime;
    }
}

class edge<P , W> {
    private P point;
    private W weight;

    edge(P i , W j) {
        this.point = i;
        this.weight = j;
    }

    public void setEdge(P point, W weight) {
        this.point = point;
        this.weight = weight;
    }

    public W getWeight(){
        return this.weight;
    }

    public P getPoint(){
        return this.point;
    }

}
