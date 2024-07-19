package C09_1;


import java.util.Scanner;
/*
坐标变换（其一）

前面n次变换，先加起来

需要处理m种坐标，把之前上面的加起来的和 再加上
然后输出
 */
public class Main {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt(); // 变换次数
    int m = sc.nextInt(); // 接收需要处理后并输出的答案的个数

    int count_on_x = 0;
    int count_on_y = 0;


    for (int i = 0; i < n; i++) {
        count_on_x += sc.nextInt();
        count_on_y += sc.nextInt();
    }

    int x;
    int y;
    for (int i = 0; i < m; i++) {
        x = sc.nextInt() + count_on_x;
        y = sc.nextInt() + count_on_y;
        System.out.println(x + " " + y);
    }



    }
}
