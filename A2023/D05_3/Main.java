package D05_3;
import java.util.Scanner;
/*
解压缩

看不懂
 */
public class Main {
    static final int N = 5000010;
    static char[] str = new char[N];
    static char[] ret = new char[N];
    static int s, cnt, i;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        s = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < (s - 1) / 8 + 1; i++) {
            String temp = scanner.nextLine();
            for (int j = 0; j < temp.length(); j++) {
                str[i * 16 + j] = temp.charAt(j);
            }
        }

        for (i = 0; i < s * 2; i += 2) { // 引导域
            String ss = "" + str[i] + str[i + 1];
            if (tran(ss, 16) < 128) break; // 最高位为0，退出
        }

        for (i += 2; i < s * 2; i += 2) {
            String ss = twostr(str[i], str[i + 1]);
            if (ss.charAt(6) == '0' && ss.charAt(7) == '0') { // 字面量
                int le = tran(ss.substring(0, 6), 2), p, ct; // 获取字面量长度-1的值
                if (le >= 60) { // le+1>=61
                    int dx = le - 59; // 存储字面量长度的字节数
                    StringBuilder lee = new StringBuilder();
                    for (p = i + dx * 2; p > i; p -= 2) { // 小端序拼接
                        lee.append(str[p]).append(str[p + 1]);
                    }
                    le = tran(lee.toString(), 16); // 获取字面量长度-1的值
                    i += dx * 2;
                }
                for (p = i + 2, ct = 0; ct < le + 1; p += 2, ct++) { // 存储字面量字节
                    ret[cnt++] = str[p];
                    ret[cnt++] = str[p + 1];
                }
                i = p - 2;
            } else { // 回溯引用
                int l, o;
                if (ss.charAt(6) == '0' && ss.charAt(7) == '1') { // 01形式
                    l = tran(ss.substring(3, 6), 2) + 4;
                    ss = ss.substring(0, 3) + twostr(str[i + 2], str[i + 3]);
                    i += 2;
                    o = tran(ss, 2);
                } else { // 10形式
                    l = tran(ss.substring(0, 6), 2) + 1;
                    ss = "" + str[i + 4] + str[i + 5] + str[i + 2] + str[i + 3];
                    i += 4;
                    o = tran(ss, 16);
                }

                int pcnt = cnt / 2; // 存一下现在的字节数
                for (int p = 2 * (pcnt - o), ct = 0; ct < l; p += 2, ct++) { // 从第pcnt-o个字节开始，输出l个字节
                    if (o < l && p == 2 * pcnt) p = 2 * (pcnt - o); // 如果o<l，且到了末尾，回到起点，反复输出
                    ret[cnt++] = ret[p];
                    ret[cnt++] = ret[p + 1];
                }
            }
        }

        for (int i = 0; i < cnt; i++) {
            if (i != 0 && i % 16 == 0) System.out.println();
            System.out.print(ret[i]);
        }
    }

    private static int tran(String str, int t) { // t进制转十进制
        int ret = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) ret = ret * t + str.charAt(i) - '0';
            else ret = ret * t + str.charAt(i) - 'a' + 10;
        }
        return ret;
    }

    private static String twostr(char c1, char c2) { // 16进制转2进制
        StringBuilder ret = new StringBuilder("00000000");
        int a = Character.isDigit(c1) ? c1 - '0' : c1 - 'a' + 10;
        int b = Character.isDigit(c2) ? c2 - '0' : c2 - 'a' + 10; // 改为数字
        for (int i = 3; i >= 0 && a != 0; i--) { // 前四位
            ret.setCharAt(i, (char) (a % 2 + '0'));
            a /= 2;
        }
        for (int i = 7; i >= 4 && b != 0; i--) { // 后四位
            ret.setCharAt(i, (char) (b % 2 + '0'));
            b /= 2;
        }
        return ret.toString();
    }
}
