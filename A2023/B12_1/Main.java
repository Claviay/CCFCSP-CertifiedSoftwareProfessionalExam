package B12_1;

import java.util.*;

/*
仓库规划
注意：当满足上级仓库条件的群中，选取序号最小的那个作为目标的上级仓库（序号即为输入的仓库的顺序）
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 仓库个数
        int m = sc.nextInt(); // 仓库的维度

        List<List<Integer>> lists = new ArrayList<>(); // 输入 所有仓库数据

        List<Integer> list_result = new ArrayList<>(); // 输出 该处仓库上级的编号

        // 依次输入仓库维度
        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>(); // 输入 单个仓库数据
            for (int j = 0; j < m; j++) {
                list.add(sc.nextInt());
            }
            lists.add(list);
        }







        // 开始比较筛选出上级仓库
        int flag_1 = 1;
        for (int i = 0; i < n; i++) {

            //(候选上级仓库,数据序号)
            List<Integer> list_candidate = new ArrayList<>(); // 每次比较时重新初始化候选上级仓库

            // 1.获得 i 仓库的候选上级仓库
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                // 在 i 的基础上对比非 i 的 list
                else {
                    for (int k = 0; k < m; k++) {
                        // 依次比较各个维度
                        if (lists.get(i).get(k) >= lists.get(j).get(k)) {
                            flag_1 = 0;
                            break;
                        }
                    }

                    // 如果j仓库的维度都大于i仓库
                    if (flag_1 == 1) {
                        // 存放序号
                        list_candidate.add(j + 1);
                    }
                }

                flag_1 = 1;
            }

            // 2.得到 i 仓库的候选上级仓库后，进一步筛选出最佳上级仓库
            int min_candidate = Integer.MAX_VALUE;
            if (list_candidate.isEmpty()) {
                list_result.add(0); // 如果没上级，默认为中心仓库设置为0
            } else {
                for (int candidate : list_candidate) {
                    if (candidate < min_candidate) {
                        min_candidate = candidate;
                    }
                }

                // 3. 把i仓库对应的上级仓库的编号记录到list_result中
                list_result.add(min_candidate);
            }
        }

        // 输出答案
        for (int x : list_result) {
            System.out.println(x);
        }
        sc.close();
    }
}
