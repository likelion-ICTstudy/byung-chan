package programmers;

import java.util.HashMap;

public class ponketmon {

    public static void main(String[] args) {
        Solution_ponketmon solution = new Solution_ponketmon();
//        int[] nums = new int[]{3, 1, 2, 3};
//        int[] nums = new int[]{3,3,3,2,2,4};
        int[] nums = new int[]{3,3,3,2,2,2};
        System.out.println(solution.solution(nums));
    }
}

class Solution_ponketmon {
    public int solution(int[] nums) {

        int N = nums.length;
        int myMon = N / 2;
        int answer = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        if ( myMon >= map.size()) {
            answer = map.size();
        } else {
            answer = myMon;
        }

        return answer;
    }
}
