package E03_2;


import java.util.Scanner;

/*
垦田计划

输入的时候就 把初始t天的资源消耗 加上

然后source[t]  是那一天的 所有的和的 单位资源消耗

                m -= source[maxT]; // 消耗相应资源
source[maxT - 1] += source[maxT]; // 将资源需求累加到前一天     累加!!!!!

 */
public class Main {
    static final int DAY = 200000; // 假设最大的开垦天数为200000天，足够大以覆盖题目中提到的限制

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // 读取区域总数
        long m = in.nextInt(); // 读取总资源数
        int k = in.nextInt(); // 读取每块田最少天数（最小减到的位置）

        int maxT = 0; // 初始化最大开垦时间为0
        int[] source = new int[DAY]; // 创建一个数组，记录每个开垦天数的资源需求总和

        // 读取每块田的开垦时间和对应的资源需求
        for (int i = 0; i < n; i++) {
            int t = in.nextInt(); // 开垦天数
            int c = in.nextInt(); // 缩短单位时间所需的资源量
            source[t] += c; // 累加相同开垦天数的资源需求
            maxT = Math.max(maxT, t); // 更新最大开垦时间
        }

        // 开始使用资源减少开垦时间
        while (maxT > k) { // 只要最大开垦时间大于最小减到的位置
            if (m >= source[maxT]) { // 如果当前资源足够减少最大开垦天数所需的资源
                m -= source[maxT]; // 消耗相应资源

                //--------------------------------------------------------
                // 加出了一个面积，累加法
                source[maxT - 1] += source[maxT]; // 将资源需求累加到前一天     累加!!!!!
                //--------------------------------------------------------

                maxT--; // 最大开垦时间减1天
            } else break; // 如果资源不够，则退出循环
        }

        // 输出最终的最大开垦时间
        System.out.println(maxT);
    }
}





























//下面是70分暴力解法答案
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt(); // 总区域数
//        int m = sc.nextInt(); // 手中资源总量
//        int k = sc.nextInt(); // 每块田最少天数（最小减到的位置）
//
//        int[] arr_base = new int[n+1];
//        int[] arr_consumptipon = new int[n+1];
//        for (int i = 1; i <= n; i++) {
//            arr_base[i] = sc.nextInt(); // 基础消耗时间
//            arr_consumptipon[i] = sc.nextInt(); // 缩短单位时间 所需的资源量
//        }
//        sc.close();
//
//
//        // 冒泡排序
//        int temp_base = 0;
//        int temp_consumptipon = 0;
//        for (int i = 1; i <= n ; i++) {
//            for (int j = i + 1; j <= n; j++) {
//                if (arr_base[i] < arr_base[j]) {
//                    temp_base = arr_base[i];
//                    arr_base[i] = arr_base[j];
//                    arr_base[j] = temp_base;
//
//                    temp_consumptipon = arr_consumptipon[i];
//                    arr_consumptipon[i] = arr_consumptipon[j];
//                    arr_consumptipon[j] = temp_consumptipon;
//                }
//            }
//        }
//
//        int result = arr_base[1];
//        int sub = arr_consumptipon[1];
//        int index = 1;
//        while (m > 0) {
//            if (index == n) {
//                while (m > 0) {
//                    m -= sub;
//                    if (m <= 0) {
//                        System.out.println(arr_base[index]);
//                        return;
//                    }
//                    arr_base[index]--;
//                }
//            }
//            while (arr_base[index] == arr_base[index+1]) {
//
//                sub += arr_consumptipon[index+1];
//
//                index++;
//
//                if (index == n) {
//                    while (m > 0) {
//                        m -= sub;
//                        if (m <= 0) {
//                            System.out.println(arr_base[index]);
//                            return;
//                        }
//                        arr_base[index]--;
//                    }
//                }
//            }
//            while (arr_base[index] > arr_base[index+1]) {
//                m -= sub;
//                if (m <= 0) {
//                    System.out.println(arr_base[index]);
//                    return;
//                }
//                arr_base[index]--;
//            }
//        }
//    }
//}
