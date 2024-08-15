package K2021_9_2;


import java.util.*;
/*
非零段划分

引用TreeSet概念，唯一性（元素不能重合），有序性（从小到大排序）

初始：
    前面是0，后面是非0---这样一个间隔计数为非零段落

变化：
    检查当前修改后非零段的变化
    if (0 < a[pos - 1] && 0 < a[pos + 1])
        ++tmp;  // 如果前后都是非零，非零段增加
    if (a[pos - 1] == 0 && a[pos + 1] == 0)
        --tmp;  // 如果前后都是零，非零段减少

    同时，每次变化记录最后一次状态last
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();  // 读取数组长度
        int[] a = new int[n + 2];  // 创建一个长度为 n+2 的数组

        // 设置左右条件，0和n+1实际上是不存在的，但是可是看作赋值为0
        a[0] = 0;  // 设置边界条件
        a[n + 1] = 0;  // 设置边界条件

        TreeSet<Integer> tree = new TreeSet<>();  // 用来存储所有不同的非零元素
        HashMap<Integer, ArrayList<Integer>> b = new HashMap<>();  // 存储每个元素的位置索引

        // 读取输入的数组并进行初始化操作
        for (int i = 1; i < n + 1; i++) {
            a[i] = in.nextInt();  // 将输入的数存入数组中
            if (b.containsKey(a[i])) {
                // 如果 b 中已经包含 a[i]，则直接在对应的列表中加入索引
                b.get(a[i]).add(i);
            } else {
                // 如果 b 中不包含 a[i]，则新建一个列表并加入索引
                ArrayList<Integer> tpm = new ArrayList<>();
                tpm.add(i);
                b.put(a[i], tpm);
            }

            if (a[i] != 0)
                tree.add(a[i]);  // 将非零元素加入到 TreeSet 中
        }



        // 初始计算
        int sum = 0;  // 初始化非零段计数
        for (int i = 1; i <= n; i++) {
            // 统计初始非零段的数量
            if ((a[i] > 0) && (a[i - 1] == 0)) sum++;
        }




        // 后续迭代活得Max
        int last = sum;  // 记录上一次操作后的非零段数量

        // 遍历所有不同的非零元素（按顺序从小到大）
        for (int item_of_tree : tree) {
            int tmp = last;  // tmp 记录这次操作后的非零段数量
            // 遍历所有等于 item_of_tree 的元素位置
            for (int i = 0; i < b.get(item_of_tree).size(); i++) {
                int pos = b.get(item_of_tree).get(i);  // 获取当前位置
                a[pos] = 0;  // 将该位置的值置为 0

                // 检查当前修改后非零段的变化
                if (0 < a[pos - 1] && 0 < a[pos + 1])
                    ++tmp;  // 如果前后都是非零，非零段增加
                if (a[pos - 1] == 0 && a[pos + 1] == 0)
                    --tmp;  // 如果前后都是零，非零段减少
            }
            // 更新最大非零段数量
            sum = Math.max(sum, Math.max(last, tmp));
            last = tmp;  // 更新 last 记录
        }


        System.out.print(sum);  // 输出最大非零段数量
    }
}
