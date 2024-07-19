package C09_2;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(); // 操作次数
        int m = scan.nextInt(); // 初始坐标个数

        // 两个数组，用于存储累积的缩放倍数和旋转角度
        double[] k = new double[n + 1]; // 累积缩放倍数
        double[] angle = new double[n + 1]; // 累积旋转角度

        // 初始化所有操作的累积倍数为1，角度为0
        for (int i = 0; i <= n; i++) {
            k[i] = 1;
            angle[i] = 0;
        }

        // 读取每个操作的类型和值，并更新累积倍数和角度
        for (int i = 1; i <= n; i++) {
            int type = scan.nextInt(); // 操作类型，1表示乘法，2表示加法
            double val = scan.nextDouble(); // 操作值
            if (type == 1) {
                // 如果是乘法操作，更新k值，sita不变
                k[i] = k[i - 1] * val;
                angle[i] = angle[i - 1];
            } else {
                // 如果是加法操作，更新sita值，k不变
                k[i] = k[i - 1];
                angle[i] = angle[i - 1] + val;
            }
        }

        // 对每个初始坐标进行操作并输出结果
        for (int i = 0; i < m; i++) {
            int l = scan.nextInt(); // 左边界
            int r = scan.nextInt(); // 右边界
            double x = scan.nextDouble(); // 初始x坐标
            double y = scan.nextDouble(); // 初始y坐标

            // 计算从l到r的累积旋转角度和累积缩放倍数
            double sum_sita = angle[r] - angle[l - 1]; // 区间[l, r]的旋转角度
            double sum_k = k[r] / k[l - 1]; // 区间[l, r]的缩放倍数

            // 根据公式计算最终的坐标
            double nx = x * Math.cos(sum_sita) - y * Math.sin(sum_sita); // x坐标
            double ny = x * Math.sin(sum_sita) + y * Math.cos(sum_sita); // y坐标

            // 输出结果
            System.out.println(nx * sum_k + " " + ny * sum_k); // 最终坐标
        }

        scan.close();
    }
}


