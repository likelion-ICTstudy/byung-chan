import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 다정님코드 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //N, M, K 값 받아오기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // list : 소 착유 순서(두번째 줄) 받아서 int 배열에 집어넣음
        int[] list = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        // 위치 고정 소 배치
        int[] orderList = new int[N + 1];
        // 지정 순서부터 차례대로 배치
        for (int i = 1; i < K + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int cow = Integer.parseInt(st.nextToken());
            int order = Integer.parseInt(st.nextToken());
            orderList[order] = cow;
        }

        // 빈 곳에 1번 소 위치 차례로 대입 => 가능여부 확인
        boolean result = false;
        for(int j = 1; j < N+1; j++) {
            System.out.println(Arrays.toString(orderList));
            System.out.println(Arrays.toString(list));
            if(orderList[j] == 0) {
                result = true;
                //1.
                orderList[j] = 1;

                // zerocnt : 자리 할당 못받은 소
                int zerocnt = 0;
                // cnt: 서열 소 리스트에서의 카운트
                int cnt = 0;
                int bIdx = -1;

                //2.
                for (int i = 1; i < orderList.length; i++) {
                    int idx = contains(list, orderList[i]);
                    //만약에 자리가 지정된 소가 list에 들어가 있는 경우
                    if (idx != -1) {
                        // 고정 위치가 정해진 소들이 서열순서에 맞게 배치되어 있는지 체크
                        // case1. 다르게 배치된 경우
                        if (list[cnt] != orderList[i]) {
                            if(bIdx == -1 && (zerocnt == 0)){
                                result= false;
                                break;
                            } else if (zerocnt >= (idx - bIdx - 1)) {
                                zerocnt = 0;
                                bIdx = idx;
                            } else {
                                result= false;
                                break;
                            }
                        } else { // case2. 맞게 배치된 경우
                            bIdx = idx;
                            cnt++;
                        }
                    } else if (orderList[i] == 0) {
                        zerocnt++;
                    }
                }

                if(result == true){
                    System.out.println(j);
                    return;
                } else {
                    orderList[j] = 0;
                }

            }
        }
        System.out.println("-1");
        return;
    }

    // 리스트에 key 파라미터가 들어가 있는지 확인하는 함수(있으면 index, 없으면 -1 리턴)
    static int contains(int[] arr, int key) {
        for(int i = 0; i < arr.length; i++){
            if(arr[i]==key) return i;
        }
        return -1;
    }


}
