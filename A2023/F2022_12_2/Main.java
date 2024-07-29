package F2022_12_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
训练计划

--注意：第2行 不应该只是直接通过 n - consumption[i] + 1 计算，没有考虑到依赖关系！！！
第1行：从前往后得到
第2行：从后往前追溯

还有n天开幕，有m个项目
1）最早开始时间：该科目最早可以于哪一天开始训练？
2）最晚开始时间：在不耽误参赛的前提下（n天内完成所有训练），该科目最晚可以从哪一天开始训练？

如果不能在n天内完成全部m项科目的训练，就无法参加大赛。这种情况下也就不需要再计算“最晚开始时间”了。
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // 天数
        int m = sc.nextInt();  // 训练项目数量
        int[] depend = new int[m+1];  // 存储 依赖
        int[] consumption = new int[m+1];   // 存储 训练时长


        for (int i = 1; i <= m; i++) {
            depend[i] = sc.nextInt();
        }

        for (int i = 1; i <= m; i++) {
            consumption[i] = sc.nextInt();

        }
        sc.close();


        /*----------------输入完毕-------------------*/










        /*-------------------第一行的输出，以及初步筛选是否输出第二行-------------------*/
        int[] result_line_1 = new int[m+1];
        boolean if_output_line_2 = true;
        for (int i = 1; i <= m; i++) {
            if (depend[i] == 0) {
                result_line_1[i] = 1;
            }else {
                // 由于序列号后的只依赖序列号前的，所以问题比较简化：
                // 最早可开始时间 = 依赖项 的 最早可开始时间       +  依赖项 的 训练消耗时间
                result_line_1[i] = result_line_1[depend[i]] + consumption[depend[i]];

                if (if_output_line_2) {
                    // 初步筛选要不要打印第2行
                    // 至于为什么要n+1而不是n，是因为result_line_1[i]是最早开始天数，并不包含这一天本身
                    if (result_line_1[i] + consumption[i] > n + 1) {
                        if_output_line_2 = false;
                    }
                }
            }
        }









        /*----------第2行-----------*/
        int[] result_line_2 = new int[m+1];
        Arrays.fill(result_line_2,n+1);
        boolean[] already_yes = new boolean[n+1]; // 判断是否被依赖关系连结

        // 如果没有被初步确认为 不输出第2行，进一步判断
        if (if_output_line_2) {
            for (int i = m; i >= 1 ; i--) {
                int consumption_total = 0;



                int trans_index = i;
                ArrayList<Integer> list = new ArrayList<>();
                while (trans_index != 0) {

                    already_yes[trans_index] = true;
                    consumption_total+=consumption[trans_index];
                    result_line_2[trans_index] = Math.min(result_line_2[trans_index], n+1-consumption_total);

                    // 下一个依赖是否也有依赖，传递过去
                    // 如果现在是最后一个，没有依赖，那么现在的depend[trans_index]是为0的，不会进入下一次循环
                    trans_index = depend[trans_index];
                }

            }

            // 专门为 没有被依赖的 && 不依赖其他
            // special
            for (int i = m; i >= 1 ; i--) {
                if (!already_yes[i]) {
                    result_line_2[i] = n + 1 - consumption[i];  // 直接减去consumption
                }
            }

        }





        /*---------------正式输出----------------*/

        // 打印第1行
        for (int i = 1; i <= m; i++) {
            System.out.print(result_line_1[i] + " ");
        }

        // 如果需要，打印第2行
        if (if_output_line_2) {
            System.out.print("\n");
            for (int i = 1; i <= m; i++) {
                System.out.print(result_line_2[i] + " ");
            }
        }

    }
}
