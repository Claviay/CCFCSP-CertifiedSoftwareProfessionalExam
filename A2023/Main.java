import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int bookNum = sc.nextInt();  // 书籍个数
        int targetMaxNum = sc.nextInt();  // 字母最大个数


        int[] result_x = new int[10007];  // 出现书籍的个数
        int[] result_y = new int[10007];  // 出现的总单词数

        int temp = 0;
        for (int i = 0; i < bookNum; i++) {

            HashSet<Integer> set = new HashSet<>();
            int count = sc.nextInt();

            for (int j = 1; j <= count; j++) {
                temp = sc.nextInt();
                result_y[temp]++;
                set.add(temp);
            }

            Iterator it = set.iterator();
            int temp1;
            while (it.hasNext()) {
                temp1 = (Integer) it.next();

                result_x[temp1]++;
            }


        }

        for (int i = 1; i <= targetMaxNum; i++) {
            System.out.println(result_x[i] + " " + result_y[i]);
        }







    }


}