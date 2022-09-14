package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj계단오르기 {
    static Integer[] dp;
    static int[] stair;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp = new Integer[n + 1]; // 해당 계단을 밟았을 때 최대값을 저장할 배열
        stair = new int[n + 1];

        for (int i = 1; i <= n; i++) { // 각 인덱스에 해당하는 계단의 점수를 저장
            stair[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 0;
        dp[1] = stair[1];
        // dp[2]의 값 까지 필요함. 그런데 계단의 수가 1일 경우가 있다.
        // 그래서 조건을 달아줘야함.
        if(n>=2) {
            dp[2] = stair[1] + stair[2];
        }

        ans = downStair(n);
        System.out.println(ans);
    }

    private static int downStair(int n) {
        if (dp[n] == null) {
            dp[n] = stair[n] + Math.max(downStair(n-2) , downStair(n-3) + stair[n-1]);
        }

        return dp[n];
    }
}
