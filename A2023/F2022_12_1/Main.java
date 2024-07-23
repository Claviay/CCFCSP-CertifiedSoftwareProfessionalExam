package F2022_12_1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // n年
        int n = sc.nextInt();

        double rate = 1.0 / (sc.nextDouble()+1.00000d);

        // 累乘记录
        double rate_accumulation = rate;

        // 结果（含初始化）
        double result = sc.nextDouble();

        for (int i = 1; i <= n; i++) {

            result += sc.nextDouble() * rate_accumulation;
            rate_accumulation *= rate;
        }

        sc.close();
        System.out.print(String.format("%.6f", result));
    }
}
