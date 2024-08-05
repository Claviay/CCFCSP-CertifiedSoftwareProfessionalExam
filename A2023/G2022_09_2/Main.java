package G2022_09_2;

import java.util.Collections;
import java.util.Scanner;
/*
何以包邮

dp背包问题
for (int i = 0; i < n; i++) {
    for (int j = target; j >= sum[i]; j--) {
        if (c[j] < (c[j - sum[i]] + sum[i])) {
            c[j] = c[j - sum[i]] + sum[i];
        }
    }
}

 */
public class Main {
    public static void main(String[] args) {
        // 创建 Scanner 对象以读取输入
        Scanner scanner = new Scanner(System.in);

        // 读取输入的整数 n 和 x
        int n = scanner.nextInt();  // 书本个数
        int x = scanner.nextInt();  // 包邮门槛

        // 初始化一个数组来存储输入的值
        int[] sum = new int[n];
        int num = 0;

        // 读取 n 个整数并存储到 sum 数组中，同时计算所有输入值的总和
        for (int i = 0; i < n; i++) {
            sum[i] = scanner.nextInt();
            num += sum[i]; // 计算总和 num
        }


        // 计算目标值，即 num - x
        int target = num - x;

        // 创建并初始化 dp 数组，大小为 300005，初始值为 0
        int[] c = new int[300005];
        for (int i = 0; i < c.length; i++) {
            c[i] = 0;
        }

        // 处理每一个 sum 数组中的值
        for (int i = 0; i < n; i++) {
            // 从右到左更新 dp 数组
            for (int j = target; j >= sum[i]; j--) {
                if (c[j] < (c[j - sum[i]] + sum[i])) {
                    c[j] = c[j - sum[i]] + sum[i];
                }
            }
        }

        // 打印结果，即 num - c[target]
        System.out.println(num - c[target]);

        // 关闭 Scanner
        scanner.close();
    }
}
