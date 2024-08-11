package I2022_03_2;



import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 出行计划数目
        int m = sc.nextInt(); // 查询的个数
        int waitTime = sc.nextInt(); // 等待核酸检测结果所需时间


        // 定义一个大小为 1000001 的差分数组
        // 每个元素是指： 在这一天生效 - 这一天失效的 = 净增量
        // 记住，这些都是节点，有开始生效，开始失效的节点
        int[] Q = new int[1000001];

        // 处理每个出行计划
        for (int i = 0; i < n; i++) {
            int t = sc.nextInt(); // 出行时间
            int c = sc.nextInt(); // 有效时间

            // 计算范围
            // l 表示为了满足这个出行计划，你最早可以在什么时间点去做核酸检测。
            // r 表示为了满足这个出行计划，你最晚可以在什么时间点去做核酸检测。

            // +1的原因：24小时有效，则+23都有效
            int l = Math.max(0, t - waitTime - c + 1);

            // 比如10天开始检测，5天检测时间，15天可以进入
            // 10 11 12 13 14--------结果到了15天可以进入
            // 所以是 t - waitTime
            int r = Math.max(0, t - waitTime);

            // 更新差分数组
            Q[l]++;
            if (r + 1 < Q.length) {
                Q[r + 1]--; // 因为r+1仍然是最晚可通过计划时间，r+1就不可以
            }
        }

        // 前缀和处理，将差分数组转化为普通数组
        // 差分数组转换成普通数组的方法： 累加
        for (int i = 1; i < 1000001; i++) {
            Q[i] += Q[i - 1];
        }

        // 处理每个查询
        for (int i = 0; i < m; i++) {
            int q = sc.nextInt(); // 查询时间
            System.out.println(Q[q]);
        }

        sc.close(); // 关闭输入流
    }
}
