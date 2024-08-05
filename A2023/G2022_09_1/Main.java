package G2022_09_1;

import java.util.Scanner;

/*
如此编码

累乘
然后加减乘除
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // 个数
        Long m = sc.nextLong();  // 总大数

        Long arr_a[] = new Long[n+1];
        Long arr_c[] = new Long[n+1]; // 来自于arr_a累乘
        arr_c[0] = 1L;
        for (int i = 1; i <= n; i++) {
            arr_a[i] = sc.nextLong();
            arr_c[i] = arr_c[i - 1] * arr_a[i];
        }
        sc.close();
//        // 检查是否正确记录arr_a和arr_c
//        for (int i = 1; i <= n; i++) {
//            System.out.print(arr_a[i] + " ");
//        }
//        System.out.println();
//        for (int i = 0; i < n; i++) {
//            System.out.print(arr_c[i] + " ");
//        }


        Long arr_b[] = new Long[n+1];
        Long temp_accumulation = 0L;
        arr_b[1] = (m % arr_c[1]) / arr_c[0];
        System.out.print(arr_b[1]+" ");

        // 核心
        for (int i = 2; i <= n; i++) {
            temp_accumulation = m % arr_c[i] - m % arr_c[i - 1];
//            System.out.println(temp_accumulation+" ");
            arr_b[i] = temp_accumulation / arr_c[i-1];
            System.out.print(arr_b[i]+" ");
        }

    }
}
