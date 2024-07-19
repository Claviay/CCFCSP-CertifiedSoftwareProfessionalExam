package B12_2;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 因子化简

 生成素数
 从小开始除以素数
 */
public class Main {

    // 存储素数的列表
    private static List<Long> primes = new ArrayList<>();

    // 判断是否是素数
    private static boolean isPrime(int x) {
        if (x <= 1) return false;
        for (int i = 2; i * i <= x; ++i) {
            if (x % i == 0) return false;
        }
        return true;
    }

    // 计算一系列素数，然后存储到primes里面
    private static void generatePrimes(int maxNum) {
        for (int i = 2; i <= maxNum; ++i) {
            if (isPrime(i)) {
                primes.add((long) i);
            }
        }
    }

    // 主函数，简化数字
    public static long simplifyNumber(long n, long k) {
        long result = 1;
        Map<Long, Long> factorCount = new HashMap<>();

        // 素因数分解
        for (Long prime : primes) {
            while (n % prime == 0) {
                n /= prime;
                factorCount.put(prime, factorCount.getOrDefault(prime, 0L) + 1);
            }
            if (n == 1) break; // 分解到1就可以终止循环了
        }

        // 根据阈值 k 调整结果
        for (Map.Entry<Long, Long> factor : factorCount.entrySet()) {
            if (factor.getValue() >= k) {
                for (long i = 0; i < factor.getValue(); i++) {
                    result *= factor.getKey();
                }
            }
        }

        // result 如果等于1就意味着没有满足条件的素数因子
        return result == 1 && n > 1 ? n : result; // 如果没有任何素因子大于阈值且 n 不是 1，则返回 n
    }

    public static void main(String[] args) {
        generatePrimes(1000); // 生成1000以内的所有素数

        Scanner scanner = new Scanner(System.in);

        int q = scanner.nextInt();

        // 一共有q个数
        while (q-- > 0) {
            long n = scanner.nextLong();  // 操作的数
            long k = scanner.nextLong();  // 阈值
            System.out.println(simplifyNumber(n, k));
        }

        scanner.close();
    }
}
