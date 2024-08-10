package H2022_06_2;

import java.util.HashMap;
import java.util.Scanner;

/*
寻宝！大冒险！

该算法的核心在于枚举每个树作为藏宝图的左下角，检查其是否满足藏宝图的所有要求。
为了优化性能，先检查是否超出地图边界，再比较树木数量是否匹配，
最后逐个对比具体位置上的树木分布。这种方法能够有效减少不必要的计算，提高匹配的准确性和效率。

如果map_value藏宝图是1，这里必须出现在map记录树坐标中
如果map_value藏宝图是0，这里必须！不能！出现再map记录树坐标中
 */
public class Main {

    // 避免了L直接输入巨大矩阵
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 树木的总数
        int L = sc.nextInt(); // 总地图边长
        int S = sc.nextInt(); // 藏宝图边长

        // 树的坐标
        int[][] map = new int[n][2];
        for (int i = 0; i < n; i++) {
            map[i][0] = sc.nextInt();
            map[i][1] = sc.nextInt();
        }

        // 藏宝图
        int[][] map_treasure = new int[S + 1][S + 1];
        int tree = 0; // tree：藏宝图上面的树木的数量
        for (int i = S; i >= 0; i--) { // 注意这里的输入是从S然后递减输入
            for (int j = 0; j <= S; j++) {
                map_treasure[i][j] = sc.nextInt();
                if (map_treasure[i][j] == 1) {
                    tree++;
                }
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            int x = map[i][0], y = map[i][1];

            // 如果出现超界，跳过
            if (x + S > L || y + S > L) continue;

            int tree_sum = 0;
            for (int j = 0; j < n; j++) { // 每有1棵树，对比它若是作为左下边界的一棵，框出藏宝图大小内的树木的数量
                if (map[j][0] - x >= 0 && map[j][0] - x <= S && map[j][1] - y >= 0 && map[j][1] - y <= S) {
                    tree_sum++;
                }
            }

            // 先筛选出不符合数量的，如果数量不符合，直接跳过
            if (tree_sum == tree) {
                boolean flag = true;
                for (int j = 0; j <= S && flag; j++) {  // 这里的flag优化很好
                    for (int k = 0; k <= S; k++) {

                        // 两个分类讨论是为了防止出现预期 0结果1，预期1结果0的情况
                        if (map_treasure[j][k] == 0) { // 如果藏宝图这里显示为0，那么这个坐标不应该 在map记录树坐标 中出现
                            for (int m = 0; m < n; m++) {
                                if (map[m][0] == x + j && map[m][1] == y + k) { // 如果真的出现了，flag变更为false
                                    flag = false;
                                    break;
                                }
                            }
                        } else { // 如果藏宝图这里显示为1，那么应该在 map记录树坐标 中出现
                            boolean found = false;
                            for (int m = 0; m < n; m++) {
                                if (map[m][0] == x + j && map[m][1] == y + k) {
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                flag = false;
                                break;
                            }
                        }
                    }
                }
                if (flag) result++;
            }
        }

        System.out.println(result);
        sc.close();
    }





 // 70分答案 设置了matrix_L的矩阵太大了
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt(); // 总长L的绿化林的总 树木的个数
//        int L = sc.nextInt();
//        int S = sc.nextInt(); // S藏宝图长度远小于L
//
//        // 总地图L
//        int[][] matrix_L = new int[L+1][L+1];
//        int[] matrix_L_tree_x = new int[n];
//        int[] matrix_L_tree_y = new int[n];
//        int tree_index = 0;
//        int temp_x = 0, temp_y = 0;
//        for (int i = 0; i < n; i++) {
//            temp_x = sc.nextInt();
//            temp_y = sc.nextInt();
//            matrix_L[temp_x][temp_y] = 1;
//
//            // 记录树的地点
//            matrix_L_tree_x[tree_index] = temp_x;
//            matrix_L_tree_y[tree_index] = temp_y;
//            tree_index++;
//        }
//
//        // 藏宝图S
//        int[][] matrix_S = new int[S+1][S+1];
//        for (int i = S; i >= 0; i--) {
//            for (int j = 0; j < S+1; j++) {  // 注意输入顺序
//                matrix_S[i][j] = sc.nextInt();
//            }
//        }
//
//
//        int result = 0;
//        for (int i = 0; i < n; i++) {
//            if (matrix_L_tree_x[i]+S >= L+1 || matrix_L_tree_y[i]+S>=L+1) {
//                continue;
//            }
//            else {
//                int flag = 1;
//                int offset_x = matrix_L_tree_x[i];
//                int offset_y = matrix_L_tree_y[i];
//                for (int j = 0; j < S+1; j++) {
//                    if (flag == 0) {
//                        break;
//                    }
//                    for (int k = 0; k < S+1; k++) {
//                        if (matrix_L[j+offset_x][k+offset_y] != matrix_S[j][k]) {
//                            flag = 0;
//                            break;
//                        }
//                    }
//                }
//
//                if (flag == 1) {
//                    result++;
//                }
//            }
//        }
//
//
//
//        System.out.println(result);
//        sc.close();
//    }
}
