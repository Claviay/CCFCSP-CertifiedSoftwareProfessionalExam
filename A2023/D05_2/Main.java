package D05_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
/*
矩阵运算

目标矩阵： (W * (Q * K_T)) * V



输入row和col分别是n和m

为避免超限、错误，分别注意如下两个重点：
1. 由于n远大于m，所以尽量避免产生n*n的矩阵。 应该运用结合律优先获得后部分的m*m矩阵，避免内存超限
2. 使用Long数组，避免答案错误
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        long[][] Q = new long[n][m];
        long[][] K = new long[n][m];
        long[][] V = new long[n][m];
        long[] W = new long[n];

        // Read matrix Q
        for (int i = 0; i < n; i++) {
            line = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                Q[i][j] = Long.parseLong(line[j]);
            }
        }

        // Read matrix K
        for (int i = 0; i < n; i++) {
            line = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                K[i][j] = Long.parseLong(line[j]);
            }
        }

        // Read matrix V
        for (int i = 0; i < n; i++) {
            line = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                V[i][j] = Long.parseLong(line[j]);
            }
        }

        // Read vector W
        line = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            W[i] = Long.parseLong(line[i]);
        }

        //----------------------------------------------------

        long[][] K_T = trans_T_matrix(K);
        long[][] K_T_and_V = calculate_matrix(K_T, V);

        long[][] Q_and_K_T_and_V = calculate_matrix(Q, K_T_and_V);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Q_and_K_T_and_V[i][j] *= W[i];
                System.out.print(Q_and_K_T_and_V[i][j] + " ");
            }
            System.out.println();
        }
    }


    // 计算矩阵相乘
    static long[][] calculate_matrix(long[][] x, long[][] y) {
        int x_row = x.length;
        int y_row = y.length;
        int x_col = x[0].length;
        int y_col = y[0].length;
        if (x_col != y_row) {
            throw new ArithmeticException("Matrix dimensions do not match for multiplication.");
        }
        long[][] result = new long[x_row][y_col]; // x的row y的col

        for (int i = 0; i < x_row; i++) {
            for (int j = 0; j < y_col; j++) {
                result[i][j] = 0;
                for (int k = 0; k < x_col; k++) {
                    result[i][j] += x[i][k] * y[k][j];
                }
            }
        }
        return result;
    }


    // 转置矩阵
    static long[][] trans_T_matrix(long[][] x) {
        int row = x.length;
        int col = x[0].length;
        long[][] result = new long[col][row];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                result[i][j] = x[j][i];
            }
        }
        return result;
    }
}
