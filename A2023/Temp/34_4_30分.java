import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = br.readLine().split(" ");
        int n = Integer.parseInt(line1[0]);  // 仓库数量
        int m = Integer.parseInt(line1[1]);  // 货物数量
        int v = Integer.parseInt(line1[2]);  // 目标价值

        int[][] dp = new int[1005][40010];  // 前i个仓库费用j可以获得的最大总价值
        List<Integer>[] a = new List[1005];  // 第i个仓库的第j个货物的价值
        for (int i = 0; i <= n; i++) {
            a[i] = new ArrayList<>();
        }
        int[] b = new int[1005];  // 仓库基本费用
        int[] c = new int[1005];  // 仓库计件费用

        // 初始化 dp 数组
        for (int i = 0; i <= 40000; i++) {
            dp[0][i] = 0;
        }

        // 读取仓库信息
        for (int i = 1; i <= n; i++) {
            String[] line2 = br.readLine().split(" ");
            b[i] = Integer.parseInt(line2[0]);
            c[i] = Integer.parseInt(line2[1]);
        }

        // 读取货物信息
        for (int i = 1; i <= m; i++) {
            String[] line3 = br.readLine().split(" ");
            int val = Integer.parseInt(line3[0]);
            int t = Integer.parseInt(line3[1]) + 1;
            a[t].add(val);
        }

        // 对每个仓库的货物按价值降序排序
        for (int i = 1; i <= n; i++) {
            Collections.sort(a[i], Collections.reverseOrder());
        }

        // 动态规划
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= 40000; j++) {
                dp[i][j] = dp[i - 1][j];  // 不选第i个仓库
                int sum = 0;
                for (int k = 0; k < a[i].size(); k++) {  // 选第i个仓库
                    if (b[i] + c[i] * (k + 1) > j) {
                        break;
                    }
                    sum += a[i].get(k);  // 收益和
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - b[i] - c[i] * (k + 1)] + sum);
                }
            }
        }

        // 寻找满足条件的最小花费
        int ans = -1;
        for (int i = 0; i <= 40000; i++) {
            if (dp[n][i] >= v) {
                ans = i;
                break;
            }
        }

        System.out.println(ans);
        br.close();
    }
}
