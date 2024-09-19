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
        HashMap<Long, Long> factorCount = new HashMap<>();

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

        return result;
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


/*
2024.09.05
import java.util.*;

public class Main {
    public static List<Long> primes = new ArrayList<>(1000000);
    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        for (int i = 2; i < 1000; i++) {
            if (isPrime(i)) {
                primes.add((long)i);
            }
        }


        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        for (int i = 1; i <= num; i++) {
            long item = sc.nextLong();
            long k = sc.nextInt();
            long ans = 1L;
            int primesIndex = 0;

            while (item != 1L && primesIndex < primes.size()) {
                if (item % primes.get(primesIndex) == 0) {
                    int tempCount = 0;
                    long tempAns = 1L;
                    while (item % primes.get(primesIndex) == 0) {
                        item /= primes.get(primesIndex);
                        tempAns *= primes.get(primesIndex);
                        tempCount++;
                    }

                    if (tempCount >= k) {
                        ans *= tempAns;
                    }
                }
                primesIndex++;
            }

            if (item > 1 && k == 1) {
                ans *= item;
            }

            System.out.println(ans);
        }
    }

}

 */