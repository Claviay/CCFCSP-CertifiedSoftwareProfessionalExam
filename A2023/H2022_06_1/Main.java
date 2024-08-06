package H2022_06_1;

import java.util.Scanner;
/*
归一化处理

很简单的加减乘除
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Double arr[] = new Double[N];
        double total_sum = 0.0;
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextDouble();
            total_sum += arr[i];
        }

        double average = total_sum / N;
        double total_D = 0.0;
        for (int i = 0; i < N; i++) {
            total_D += Math.pow((arr[i] - average),2);
        }
        total_D = total_D / N;
        total_D = Math.sqrt(total_D);


        for (int i = 0; i < N; i++) {
            System.out.println((arr[i] - average)/total_D + " ");
        }

    }
}
