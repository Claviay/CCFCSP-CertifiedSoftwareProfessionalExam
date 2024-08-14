package K2021_9_1;

import java.util.HashMap;
import java.util.Scanner;

/*
数组排序

很简单的HashMap
    sum_max += key * map.get(key);
    sum_min += key ;

 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < num; i++) {
            int temp = sc.nextInt();
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }

        int sum_max = 0;
        int sum_min = 0;
        for (Integer key : map.keySet()) {
            sum_max += key * map.get(key);
            sum_min += key ;
        }

        System.out.println(sum_max);
        System.out.print(sum_min);
    }
}
