package L2021_4_2;

import java.util.Scanner;

/*
领域均值----> 动态规划 + 移动窗口

独立完成！成功做出第二题

动态规划： 1.从[1][1]这一个记录  2.延伸到第一列   3.进而然后每一行都以所在行的第一个为基础
移动窗口： 此时控制变量只需计算左右平移
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // n×n的矩阵
        int L = sc.nextInt(); // 灰度值（数的大小）0~L（左闭右开）
        int r = sc.nextInt(); // 邻近度
        int t = sc.nextInt(); // 阈值，小于或等于这个阈值，计入答案
        int result = 0;

        int[][] matrix = new int[n+1][n+1];
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }





        // 第1行第1列 起始点
        int[][] dp = new int[n+1][n+1];
        for (int i = 1; i <= r + 1; i++) {
            for (int j = 1; j <= r + 1; j++) {
                dp[1][1] += matrix[i][j];
            }
        }





        // 接着补足第1列
        for (int i = 2; i < n+1; i++) {
            int j = 1;

            // 上边触及边界，下边不触及
            if (i - r <= 1 && i + r <= n) {
                dp[i][j] += dp[i-1][j];
                for (int k = 1; k <= r+1 ; k++) {
                    dp[i][j] += matrix[i+r][k];
                }
            }
            // 上边触及边界，下边触及边界
            else if (i - r <= 1) {
                dp[i][j] += dp[i-1][j];
            }
            // 上边不触及边界，下边不触及
            else if (i - r > 1 && i + r <= n) {
                dp[i][j] += dp[i-1][j];
                for (int k = 1; k <= r+1 ; k++) {
                    dp[i][j] += matrix[i+r][k] - matrix[i-r - 1][k];
                }
            }
            // 上边不触及，下边触及
            else {
                dp[i][j] += dp[i-1][j];
                for (int k = 1; k <= r+1 ; k++) {
                    dp[i][j] -= matrix[i-r - 1][k];
                }
            }
        }




        // 前面已经生成好第一列，开始每一行从第一个开始补全
        for (int i = 1; i <= n ; i++) {
            for (int j = 2; j <= n; j++) {
                // 左边触及边界，右边不触及
                if (j - r <= 1 && j + r <= n) {
                    dp[i][j] += dp[i][j - 1];

                    dp[i][j] += calculate_right(matrix,i,j,n,r);

                }

                // 左边触及边界，右边触及边界
                else if (j - r <= 1) {
                    dp[i][j] += dp[i][j - 1];
                }
                // 左边不触及边界，右边不触及
                else if (j - r > 1 && j + r <= n) {
                    dp[i][j] += dp[i][j - 1];
                    dp[i][j] += calculate_right(matrix,i,j,n,r) - calculate_left(matrix,i,j,n,r);
                }
                // 左边不触及，右边触及
                else {
                    dp[i][j] += dp[i][j - 1];
                    dp[i][j] -= calculate_left(matrix,i,j,n,r);
                }
            }
        }
//
//        for (int i = 1; i <= n ; i++) {
//            for (int j = 1; j <= n; j++) {
//                System.out.print(dp[i][j]+" ");
//            }
//            System.out.println();
//        }

        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= n; j++) {
//                System.out.print(calculate_num(dp,i,j,n,r)+" ");
                if (calculate_num(dp,i,j,n,r) * t >= dp[i][j]) {
                    result++;
                }

            }
        }

        System.out.println(result);
    }



    // 计算此位置拥有的元素个数
    public static double calculate_num(int[][] matrix, int i, int j,int n, int r) {
        int left,right,up,down;
        left = Math.max(1, i - r);
        right = Math.min(n, i + r);
        up = Math.max(1, j - r);
        down = Math.min(n, j + r);
        return (double) (right - left + 1) * (down - up + 1);
    }



    // 这里是消失掉的左边，这个消失的位置j-r-1
    public static int calculate_left(int[][] matrix, int i, int j,int n, int r) {
        int res = 0;
        for (int k = Math.max(1, i - r); k <= Math.min(n, i+r) ; k++) {
            res += matrix[k][j-r-1];
        }
        return res;
    }



    // 这是新增的右边，这个新增的位置j+r
    public static int calculate_right(int[][] matrix, int i, int j,int n, int r) {
        int res = 0;
        for (int k = Math.max(1, i - r); k <= Math.min(n, i+r) ; k++) {
            res += matrix[k][j+r];
        }
        return res;
    }
}
