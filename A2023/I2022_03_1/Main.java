package I2022_03_1;

import java.util.HashSet;
import java.util.Scanner;
/*
未初始化警告


左边的如果不在HashSet里面，result++
然后左边的放入HashSet中
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        HashSet<Integer> yes = new HashSet<>();
        int result = 0;
        int num_variable = sc.nextInt();
        int num_sentence = sc.nextInt();
        for (int i = 0; i < num_sentence; i++) {
            int left = sc.nextInt();
            int right = sc.nextInt();
            if (right!=0 && !yes.contains(right)) {
                result++;
            }
            yes.add(left);
        }

        System.out.println(result);
    }
}
