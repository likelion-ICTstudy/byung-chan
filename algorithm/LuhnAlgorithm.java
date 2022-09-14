package algorithm;

public class LuhnAlgorithm{

    public static void main(String[] args){

        boolean isvalidCardNumber = luhn("5107372629291780");
        System.out.println(isvalidCardNumber);

    }

    static boolean luhn(String cardNum){

        int sum = 0; // 표의 맨 아랫부분인 sum을 모두 더해 저장할 변수

        char[] arr = cardNum.toCharArray();

        int cardLength = arr.length;

        boolean isEvenNum = false; // 짝수번째 인지 판단 // 처음 index는 1부터 시작하므로

        while(cardLength-- > 0){
            int n = Character.getNumericValue(arr[cardLength]); // 숫자 형태의 char형을 int형으로 변환
            if(isEvenNum){
                n *= 2;
                if(n > 9) {
                    n = (n % 10) + 1;
                }
            }
            isEvenNum = !isEvenNum;
            sum += n;
        }
        return (sum % 10 == 0);
    }
}