package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준24400 {
    static String ans ="";
    static int N , Q;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            ans += st.nextToken();
        }

//        System.out.println("====================");
//        System.out.println(ans);
//        System.out.println("====================");

        select(A , 0 , N-1 , Q-1);

        System.out.println(answer);
    }

    public static Object select(int[] arr, int start, int end, int target) {

        if (start == end) {
            return arr[start];
        }
        int q = partition(arr , start , end);

        if (q > target) {
            return select(arr, start, q - 1, target);
        } else if ( target == q) {
            return arr[q];
        } else {
            return select(arr , q+1, end, target);
        }
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int i = start - 1;
        int j = start;
        int temp;

        while (j < arr.length) {
            if (arr[j] <= pivot) {
                temp = arr[j];
                arr[j] = arr[++i];
                arr[i] = temp;
            }
            j++;

            compareAns(arr);

        }

        if (j == arr.length) {
            j--;
        }

        if (arr[i] > arr[j]) {
            temp= arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }

        return i;
    }

    private static void compareAns(int[] arr) {
        String compareStr = "";
        for (int ar : arr) {
            compareStr += ar;
        }

//        System.out.println("====================");
//        System.out.println(compareStr);
//        System.out.println("====================");

        if (compareStr.equals(ans)){
            answer = 1;
        }
    }
}
