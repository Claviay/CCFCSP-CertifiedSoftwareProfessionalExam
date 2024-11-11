import java.util.*;
import java.io.*;
class Main {
    public static int[][] matrix = new int[10001][10001];
    public static int n, m;
    public static ArrayList<Integer> list = new ArrayList<>(1001);
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = br.readLine().split(" ");
        n = Integer.parseInt(line1[0]);  // 矩阵大小
        m = Integer.parseInt(line1[1]);  // 矩阵大小
        int consultNumber = Integer.parseInt(line1[2]);  // 查询次数

        for (int i = 0; i < n; i++) {
            String[] line2 = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                int temp = Integer.parseInt(line2[j]);
                list.add(temp);
                matrix[i][j] = temp;
            }
        }

        // 接下来 consultNumber 查询次数
        for (int i = 0; i < consultNumber; i++) {
            String[] line3 = br.readLine().split(" ");
            int funNum = Integer.parseInt(line3[0]);

            switch (funNum) {
                case 1:
                    fun1(Integer.parseInt(line3[1]), Integer.parseInt(line3[2]));
                    break;

                case 2:
                    fun2();
                    break;

                case 3:
                    int result = fun3(Integer.parseInt(line3[1]), Integer.parseInt(line3[2]));
                    System.out.println(result);
                    break;

                default:
                    break;
            }

        }

        br.close();
    }

    // 重塑矩阵
    public static void fun1(int p, int q) {


        int index = 0;
        for (int i = 0; i < p ; i++) {
            for (int j = 0; j < q; j++) {
                matrix[i][j] = list.get(index++);
            }
        }

        n = p;
        m = q;
    }




    // 转置矩阵
    public static void fun2() {
        list.clear();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                list.add(matrix[j][i]); // 注意这里的转置
            }
        }

        int index = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = list.get(index++);
            }
        }


        int temp = n;
        n = m;
        m = temp;
    }


    // 查询元素
    public static int fun3(int p, int q) {
        return matrix[p][q];
    }




}


