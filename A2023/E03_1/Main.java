package E03_1;

import java.util.Scanner;

/*
田地丈量


举例： 索引是2的话则指的是坐标轴2到3的方块

因为只输入整数，所以可以取单位面积的方块
单位方块默认是false， 如果田地覆盖则是true
最后一共计算有多少个true
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();

        boolean[][] land = new boolean[10003][10003];


        int temp_x_1, temp_y_1, temp_x_2, temp_y_2;
        for (int i = 0; i < n; i++) {
            temp_x_1 = sc.nextInt();
            temp_y_1 = sc.nextInt();
            temp_x_2 = sc.nextInt();
            temp_y_2 = sc.nextInt();
            if (temp_x_2 <= -1 || temp_x_1 >= x || temp_y_2 <= -1 || temp_y_1 >= y) {
                continue;
            }

            temp_x_1 = Math.max(temp_x_1, 0);
            temp_y_1 = Math.max(temp_y_1, 0);

            temp_x_2 = Math.min(temp_x_2, x);
            temp_y_2 = Math.min(temp_y_2, y);
            for (int j = temp_x_1; j < temp_x_2; j++) {
                for (int k = temp_y_1; k < temp_y_2; k++) {
                    land[j][k] = true;
                }
            }
        }


        // 最后看还剩多少个true
        int result = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (land[i][j]) {
                    result++;
                }
            }
        }

        System.out.println(result);

    }
}
