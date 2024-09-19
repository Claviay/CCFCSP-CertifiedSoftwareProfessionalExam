package B12_1;

import java.util.*;

/*
仓库规划
注意!!!当满足上级仓库条件的群中，选取序号最小的那个作为目标的上级仓库（序号即为输入的仓库的顺序）
核心：三层遍历，复杂度 O（n^2)
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

//flag_1 被用于检测仓库 j 是否在所有维度上都大于仓库 i。
// 如果 flag_1 在内部循环结束后仍然为 1，则说明仓库 j 可以作为仓库 i 的上级。

//flag_2 则用于检测对于仓库 i 是否已经找到并记录了任何上级仓库。
// 如果在整个外部循环结束后 flag_2 仍为 1，则说明没有找到任何上级仓库，
// 并且应该将结果列表中的相应位置设置为 0。
        int flag_1 = 1;
        int flag_2 = 1;
        for (int i = 0; i < n; i++) {


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
                        list_result.add(j + 1);
                        flag_2 = 0;
                        break;
                    }
                }

                flag_1 = 1;
            }


            // 如果对于i 比较了所有发现没有上级仓库（其中flag_2 = 1 也就是说没有被改变）
            if (flag_2 == 1) {
                list_result.add(0);
            }
            flag_2 = 1; // 复位
        }

        // 输出答案
        for (int x : list_result) {
            System.out.println(x);
        }
        sc.close();
    }
}
