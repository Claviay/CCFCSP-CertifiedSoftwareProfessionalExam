import java.io.*;
import java.util.*;

public class Main {
    public static ArrayList<String> a = new ArrayList<>();
    public static HashMap<Character, String> charMap = new HashMap<>();

    // 获取字符的索引
    public static int getIndex(char c) {
        if (c >= 'a' && c <= 'z') {
            return c - 'a';
        }
        if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 26;
        }
        if (c >= '0' && c <= '9') {
            return c - '0' + 52;
        }
        if (c == ' ') {
            return 63; // 空格字符
        }
        return -1;
    }

    // 根据旋转次数对字符进行旋转
    public static char rotateChar(char c, long n) {
        String group = charMap.get(c); // 找到字符所属的转换组
        if (group == null || group.length() <= 1) {
            return c; // 不需要转换
        }
        int index = group.indexOf(c); // 找到字符在组内的索引
        int turn = (int) (n % group.length()); // 计算旋转的步数

        // 0 1 2 3
        // 这是一个技巧
        return group.charAt((index + turn) % group.length()); // 根据旋转步数返回字符
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String originalString = br.readLine(); // 最初的字符串
        int transNum = Integer.parseInt(br.readLine()); // 读取转换规则的次数

        for (int i = 0; i < transNum; i++) {
            String currentTran = br.readLine();
            currentTran = currentTran.substring(1, currentTran.length() - 1); // 去掉开头和结尾的 '#'

            char leftChar = currentTran.charAt(0);
            char rightChar = currentTran.charAt(1);

            String leftCharGroup = charMap.getOrDefault(leftChar, "" + leftChar);
            String rightCharGroup = charMap.getOrDefault(rightChar, "" + rightChar);

            if (!leftCharGroup.equals(rightCharGroup)) {
                // 合并两个组并更新所有字符的所属组
                String mergedGroup = leftCharGroup + rightCharGroup;
                for (char c : mergedGroup.toCharArray()) {
                    charMap.put(c, mergedGroup); // 所有字符映射到同一组
                }
            }
        }

        int consultNum = Integer.parseInt(br.readLine()); // 读取查询次数
        String[] consults = br.readLine().split(" "); // 读取查询的次数对应的数组

        for (int i = 0; i < consultNum; i++) {
            long n = Long.parseLong(consults[i]); // 当前查询的旋转次数

            // 输出转换后的字符串
            System.out.print("#");
            for (int j = 1; j < originalString.length() - 1; j++) {
                System.out.print(rotateChar(originalString.charAt(j), n));
            }
            System.out.println("#");
        }
    }
}
/*
#Hello World#
6
#HH#
#e #
# r#
#re#
#oa#
#ao#
3
1 2 3



#H llarWaeld#
#HrlloeWo ld#
#Hella Warld#
 */
