package M2020_12_2;

import java.util.*;
/*
期末预测之最佳阈值

不断删去当前的0预测，加上下一个的1预测

还有记住迭代器用法：
        Set<Integer> keySet = treeMap.keySet();
        Iterator<Integer> iterator = keySet.iterator();
 */
public class Main {
    public static void main(String[] args) {

        // 利用TreeSet的唯一性、有序性
        TreeMap<Integer, Integer[]> treeMap = new TreeMap<>();

        int result = 0;
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int level = in.nextInt();
            int predict_boolean = in.nextInt();

            if (!treeMap.containsKey(level)) {
                Integer[] predict = new Integer[2];
                predict[0] = 0; // 初始化为 0
                predict[1] = 0; // 初始化为 0
                treeMap.put(level, predict);
            }

            Integer[] predict = treeMap.get(level);
            if (predict_boolean == 1) {
                predict[1]++;
                result++;
            } else {
                predict[0]++;
            }
        }

        int resultIndex = 0;
        Set<Integer> keySet = treeMap.keySet();
        Iterator<Integer> iterator = keySet.iterator();

        int level = 0;
        if (iterator.hasNext()) {
            level = iterator.next(); // 跳过第一个元素
        }

        int currentResult = result;
//        System.out.println(resultIndex+ " " + result);

        // 从第2个元素开始
        // 不断删去当前的0预测，加上下一个的1预测
        while (iterator.hasNext()) {
            currentResult += treeMap.get(level)[0];
            level = iterator.next();
            currentResult -= treeMap.get(level)[1];
            if (currentResult >= result) {
                resultIndex = level;
                result = currentResult;
            }
        }

        // 就是那个level
        System.out.println(resultIndex);
    }
}
