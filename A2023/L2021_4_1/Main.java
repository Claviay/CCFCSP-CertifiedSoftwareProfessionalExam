package L2021_4_1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // 行
        int m = sc.nextInt();  // 列
        int L = sc.nextInt();  // 输出的个数（L-1的位置是有L-1的灰度的个数）
        int[] arr_L = new int[L];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr_L[sc.nextInt()]++;
            }
        }

        for (int i = 0; i < L; i++) {
            System.out.print(arr_L[i] + " ");
        }

        sc.close();
    }
}

