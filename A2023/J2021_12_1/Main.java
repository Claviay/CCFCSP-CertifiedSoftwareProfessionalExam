package J2021_12_1;

import java.util.Scanner;
/*
序列查询

减法
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int res = 0;

        int itemsLen = sc.nextInt();
        int money = sc.nextInt() - 1; // 注意这里是开区间，所以要减1

        int[] items = new int[itemsLen+1];
        for (int i = 1; i <= itemsLen; i++) {
            items[i] = sc.nextInt(); // 递增的一个数组
        }

        if (itemsLen == 1) {
            if (items[1] > money) {
                System.out.println(0);
                return;
            }
            else {
                System.out.println((money - items[1] + 1));
            }
        }

        else {
            Boolean flag =true;
            int Max_right;
            for (int j = 2; j <= itemsLen && flag; j++) {
                // 结算商品之间的间隔的值
                res += (j - 1)*(items[j] - items[j - 1]);

                // 如果出现有的商品价格 > 我的钱包
                // 也就是说钱包在商品序列之间
                // 此时开始结算最后一个小于等于钱包的商品（因为之前结算唯一遗漏的就是这个）
                if (j + 1 <= itemsLen && items[j + 1] > money) {
                    flag = false;
                    Max_right = j;
                    res += j*(money - items[Max_right] + 1);
                }

                // 如果所有商品的价格都 <= 我的钱包
                // 也开始结算最后一个小于等于钱包的商品（因为之前结算唯一遗漏的就是这个）
                if (j+1 > itemsLen && items[j] <= money) {
                    res += (j)*(money - items[j] + 1);
                }
            }
        }

        System.out.println(res);
        sc.close();
    }
}
