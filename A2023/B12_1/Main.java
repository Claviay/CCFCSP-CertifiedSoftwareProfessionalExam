package B12_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 仓库个数
        int m = sc.nextInt(); // 仓库的维度

        List<List<Integer>> lists = new ArrayList<>(); // 输入 所有仓库数据
        List<Integer> list = new ArrayList<>();        // 输入 单个仓库数据

        List<Integer> list_candidate = new ArrayList<>(); // 候选上级仓库

        // 依次输入仓库维度
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                list.add(sc.nextInt());
            }
            lists.add(list);
            list.clear();
        }

        // 开始比较筛选出上级仓库
        int flag = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                // 在 i 的基础上对比非 i 的 list
                else {
                    for (int k = 0; k < m; k++) {
                        if (lists.get(i).get(k) >= lists.get(j).get(k)) {
                            flag = 0;
                            break;
                        }
                    }

                    if (flag == 1) {

                    }
                }
            }
        }

        sc.close();
    }


}
