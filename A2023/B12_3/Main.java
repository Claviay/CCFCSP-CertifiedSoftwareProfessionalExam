package B12_3;

import java.util.*;
import java.io.*;
/*
树上搜索

dfs(int pre): 深度优先搜索，用于更新节点及其子树的总权重。如果节点未被标记为不可访问，则递归地调用其子节点。
getMinIdx(int pre): 计算所有已访问节点的权重差异，返回权重差异最小的节点。权重差异计算为当前节点总权重与其子节点权重的差的绝对值。
check(int idx, int t): 检查目标节点t是否在以idx为根的子树中。如果是，返回true；否则，继续递归检查子节点。
 */
public class Main {
    static long[] wi, temp; // wi: 节点及其子树的总权重；temp: 节点的初始权重
    static List<Integer>[] ls; // 邻接列表表示的树结构


    static Set<Integer> yes = new HashSet<>(); // 已访问且可访问的节点集合
    static Set<Integer> no = new HashSet<>(); // 不可访问的节点集合

    /*
    5 2
    10 50 10 10 20
    1 1 3 3
    5
    3
     */
    public static void main(String[] args) throws IOException {
        QuickInput in = new QuickInput(); // 自定义输入类，用于快速读取输入

        // 输出流，用于输出结果
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = in.nextInt(), m = in.nextInt(); // n: 节点数；m: 查询次数
        temp = new long[n + 1]; // 初始化节点权重数组
        ls = new List[n + 1]; // 初始化邻接列表
        for (int i = 1; i <= n; i++) temp[i] = in.nextInt(); // 读取每个节点的初始权重
        for (int i = 1; i <= n; i++) ls[i] = new ArrayList<>(); // 实际初始化邻接列表


        // 构建树结构，注意i从2开始，因为1是根节点，没有父节点   1 1 3 3
        // ls[index] = value 中这个index表示的是 上级
        // ----value则是 下级 （有很多个下级，所以用 ls = new List[n+1]）
        for (int i = 2; i <= n; i++) ls[in.nextInt()].add(i);



        // t是查询序号目标
        for (int i = 0; i < m; i++) { // 对于每个查询
            int t = in.nextInt(), pre = 1; // t: 目标节点；pre: 当前节点（初始为根节点）
            no.clear(); // 清空不可访问节点集合




            while (true) {
                wi = Arrays.copyOf(temp, n + 1); // 复制节点权重到wi（因为每次筛选掉一个之后，情况都会变化）
                yes.clear(); // 清空已访问节点集合


                // 获得了权重差异值的树，存在w[i]中
                dfs(pre); // 执行深度优先搜索


                if (yes.size() == 1) break; // 如果只剩下一个节点，说明找到了目标节点
                int idx = getMinIdx(pre); // 获取当前节点子树中权重差异最小的节点
                if (check(idx, t)) { // 如果目标节点在该子树中
                    pre = idx; // 更新当前节点为差异最小的节点
                } else { // 否则，将差异最小的节点标记为不可访问
                    no.add(idx);
                }
                out.print(idx + " "); // 输出差异最小的节点
            }







            out.println(); // 每个查询后换行
        }
        out.flush(); // 刷新输出流
    }





    // 计算某个节点（含节点本身）的子树权重之和
    public static void dfs(int pre) { // DFS函数
        yes.add(pre); // 标记当前节点为已访问
        for (int i : ls[pre]) { // 遍历当前节点的所有子节点
            if (!no.contains(i)) { // 如果子节点未被标记为不可访问
                dfs(i); // 递归调用DFS
                wi[pre] += wi[i]; // 更新当前节点的总权重
            }
        }
    }


    public static int getMinIdx(int pre) { // 获取权重差异最小的节点
        long min = Long.MAX_VALUE; // 初始化最小差异为最大值
        int idx = 1; // 初始化差异最小的节点索引
        for (int j : yes) { // 遍历已访问的节点

            // wi[j] 和 total-wi[j]    的差的绝对值
            long wj = Math.abs(wi[pre] - 2 * wi[j]); // 计算差异

            // min是记录的     wj是当前的
            if (min > wj) { // 如果找到更小的差异
                min = wj; // 更新最小差异
                idx = j; // 更新差异最小的节点索引
            }
        }
        return idx; // 返回差异最小的节点索引
    }


    // 检查t是否在idx（含idx）下子树中
    public static boolean check(int idx, int t) { // 检查目标节点是否在当前节点的子树中
        if (idx == t) return true; // 如果当前节点就是目标节点，直接返回true
        for (int i : ls[idx]) { // 遍历当前节点的子节点
            if (no.contains(i)) continue; // 如果子节点不可访问，跳过
            if (check(i, t)) return true; // 递归检查子节点的子树
        }
        return false; // 如果没有找到目标节点，返回false
    }




    static class QuickInput { // 自定义输入类
        StreamTokenizer input = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        int nextInt() throws IOException { // 读取整数
            input.nextToken();
            return (int) input.nval;
        }
    }
}