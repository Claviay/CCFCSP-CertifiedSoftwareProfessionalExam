package M2020_12_1;

import java.util.Scanner;

/*
期末预测之安全指数


 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int res = 0;
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            res += sc.nextInt() * sc.nextInt();
        }

        System.out.println(Math.max(0,res));
        sc.close();
    }
}
