package N2020_09_1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>();
        int key = 0;
        int value = 1;
        for (int i = 0; i < N; i++) {
            int temp = calculateDistance(x,y, sc.nextInt(), sc.nextInt());
            list.add(new AbstractMap.SimpleEntry<>(temp,value));
            value++;
        }

        // 下面两个都可以，一个是静态方法，一个是创建比较器
//        list.sort(Map.Entry.comparingByKey());
        list.sort(Comparator.comparing(Map.Entry::getKey));


        int flag = 3;
        for (Map.Entry<Integer, Integer> entry : list) {
            System.out.println(entry.getValue());
            flag--;
            if (flag == 0) {
                return;
            }
        }




    }

    public static int calculateDistance(int x1, int y1, int x2, int y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }
}
