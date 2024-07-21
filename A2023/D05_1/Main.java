package D05_1;

import java.util.HashMap;
import java.util.Scanner;

/*
重复局面

主要注意用StringBuilder构建

key： 每一个局面作为独特的String作为key
value： 计算出现次数为value
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());  // 读取整数，  或者直接int n = sc.nextInt()也行

        // 使用HashMap存储每个局面和它出现的次数
        HashMap<String, Integer> map_count = new HashMap<>();

        // 依次读取每个局面并处理
        for (int i = 1; i <= n; i++) {
            StringBuilder board = new StringBuilder();
            for (int j = 0; j < 8; j++) {
                board.append(sc.nextLine()); // 把这8行都加上
            }

            // 使用 toString() 方法可以将 StringBuilder 的内容转换成一个 String 对象。
            String boardStr = board.toString();

            // 记录当前局面出现的次数
            int count = map_count.getOrDefault(boardStr, 0) + 1;
            map_count.put(boardStr, count);

            // 输出当前局面是第几次出现
            System.out.println(count);
        }

        sc.close();
    }
}
