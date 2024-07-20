package C09_03;

import java.io.BufferedReader;  // 用于读取输入
import java.io.InputStreamReader;
import java.util.ArrayDeque;     // 用于实现双端队列
import java.util.ArrayList;     // 用于实现动态数组
import java.util.Deque;         // 双端队列接口
import java.util.List;          // List接口
import java.util.StringTokenizer;  // 用于分割字符串

/*
梯度求解

样例 1 输入

2 2    变量个数n  求的偏导数个数m
x1 x1 x1 * x2 + *      逆波兰式
（接下来m行）
1 2 3   x1的偏导数   x1和x2取（2，3）
2 3 4   x2的偏导数   x1和x2取（3，4）


样例 1 输出
15  x1的偏导数
3   x2的偏导数



表达式树构建：使用栈构建表达式树，每个节点根据其类型（操作符、变量、常量）存储在不同的列表中。
递归求解：solve函数递归计算表达式树的值，支持加法、减法和乘法操作，并进行模运算以确保结果在合理范围内。

表达式：x1 x1 x1 * x2 + *
构建的树结构如下：

        *
       / \
      *   x2
     / \
    x1  +
       / \
      x1  x1
 */

public class Main {
    private static final int MOD = 10_0000_0007; // 常量，用于模运算
    private static final int CONST = -1;       // 常量类型标识
    private static final int VAR = -2;         // 变量类型标识
    private static final int OP = -3;          // 操作符类型标识

    public static void main(String[] args) throws Exception {
        // 使用BufferedReader读取输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 读取第一个数n
        int m = Integer.parseInt(st.nextToken()); // 读取第二个数m

        String s = br.readLine(); // 读取表达式字符串


        // 用于将字符串分割成更小的部分，通常用于解析命令行参数、配置文件或任何以特定分隔符分隔的文本数据。
        // x1 x1 x1 * x2 + *      逆波兰式
        // 这些token用空格分开了
        StringTokenizer qwq = new StringTokenizer(s, " ");



        List<Integer> l = new ArrayList<Integer>();   // 存储左子节点
        List<Integer> r = new ArrayList<Integer>();   // 存储右子节点
        List<Integer> info = new ArrayList<Integer>(); // 存储节点信息（值或操作符）----值的话是x1的1，x2的2那种； 操作符取操作符的ASCII
        List<Integer> kind = new ArrayList<Integer>(); // 存储节点类型
        Deque<Integer> id = new ArrayDeque<Integer>(); // 栈，用于构造树
        int nodeId = 0; // 节点ID

        // 解析表达式字符串并构造表达式树
        // hasMoreTokens() 是 StringTokenizer 类的一个方法，用于检查是否还有更多的标记（tokens）可以获取。
        // 当 StringTokenizer 对象通过分隔符将源字符串分割后，你可以通过调用 hasMoreTokens() 方法来判断是否还有未读取的标记。
        while (qwq.hasMoreTokens()) {
            String token = qwq.nextToken();
            if (token.length() == 1 && "+-*".contains(token)) { // 这两个判断条件 都可以 ---------- 加减乘

                // 如果是操作符，弹出栈顶两个元素，作为左右子节点

                int rson = id.pop(); // 右子节点
                int lson = id.pop(); // 左子节点
                l.add(lson);
                r.add(rson);
                info.add((int) token.charAt(0)); // 操作符ASCII值
                kind.add(OP); // 操作符类型

                id.push(nodeId);
                nodeId++;
            } else if (token.charAt(0) == 'x') {       // --------------------------- 如果是变量，token的第一个就是x

                // 如果是变量，解析变量编号


                int x = Integer.parseInt(token.substring(1)) - 1; // 变量下标
                l.add(-1); // 叶子节点，无左子节点
                r.add(-1); // 叶子节点，无右子节点
                info.add(x);
                kind.add(VAR); // 变量类型

                id.push(nodeId);
                nodeId++;
            } else {

                // 如果是常量，解析常量值


                int x = Integer.parseInt(token); // 常量值
                l.add(-1); // 叶子节点，无左子节点
                r.add(-1); // 叶子节点，无右子节点
                info.add(x);
                kind.add(CONST); // 常量类型

                id.push(nodeId);
                nodeId++;
            }
        }



        int root = id.pop(); // 根节点

        int[] a = new int[n]; // 数组a，存储变量的值-----一定是读取n个，因为一共有n个变量，所以读取每一个变量的赋值

        // 处理每个输入数据集
        for (int i = 0; i < m; ++i) {

            // 这个st与之前的不一样了，而是新赋予的
            // 形如 -----------1 2 3   x1的偏导数   x1和x2取（2，3）
            st = new StringTokenizer(br.readLine());


            int x = Integer.parseInt(st.nextToken()) - 1; // 变量索引
            for (int j = 0; j < n; ++j) {
                // 一定是读取n个，因为一共有n个变量，所以读取每一个变量的赋值
                a[j] = Integer.parseInt(st.nextToken()); // 读取变量值
            }
            //----------------------
            // root 栈顶deque的顶部
            // x目标变量的索引
            // 数组a，存储变量的值
            // l 左子树
            // r 右子树
            // info 信息 包含 操作符/变量名
            // kind 种类
            int[] result = solve(root, x, a, l, r, info, kind); // 计算结果
            //----------------------
            System.out.println(result[1]); // 输出结果
        }
    }

    // 递归求解函数，计算表达式树的值
    private static int[] solve(int u, int x, int[] a, List<Integer> l, List<Integer> r, List<Integer> info, List<Integer> kind) {
        if (kind.get(u) == VAR) {
            // new int[]{...}：这是Java中的一种语法，用于创建一个新的整数数组。花括号 {} 中包含数组的元素。
            return new int[]{a[info.get(u)], (info.get(u) == x) ? 1 : 0};
        } else if (kind.get(u) == CONST) {
            return new int[]{info.get(u), 0};
        } else {
            //----------------------------------------------------------------操作符
            int[] lans = solve(l.get(u), x, a, l, r, info, kind); // 计算左子树
            int[] rans = solve(r.get(u), x, a, l, r, info, kind); // 计算右子树
            //-----------------------------------------------------------------
            /*
        *
       / \
      *   x2
     / \
    x1  +
       / \
      x1  x1
             */
            int sum = 0, dsum = 0;
            if (info.get(u) == (int) '+') {
                sum = lans[0] + rans[0];
                dsum = lans[1] + rans[1];
                if (sum >= MOD) sum -= MOD;
                if (dsum >= MOD) dsum -= MOD;
            } else if (info.get(u) == (int) '-') {
                sum = lans[0] - rans[0];
                dsum = lans[1] - rans[1];
                if (sum >= MOD) sum -= MOD;
                if (dsum >= MOD) dsum -= MOD;
            } else {
                sum = (int) ((1L * lans[0] * rans[0]) % MOD);
                dsum = (int) ((1L * lans[0] * rans[1] % MOD + 1L * lans[1] * rans[0] % MOD) % MOD);
                if (dsum >= MOD) dsum -= MOD;
            }
            if (sum < 0) sum += MOD;
            if (dsum < 0) dsum += MOD;
            return new int[]{sum, dsum};
        }
    }
}
