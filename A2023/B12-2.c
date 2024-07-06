/*
  2023-12-2
  因子化简
*/

#include <iostream>
#include <vector>
#include <unordered_map>
#include <cmath>

using namespace std;

// 存储素数的vector
vector<long long> primes;

// 判断是不是素数
bool isPrime(int x) {
    if (x <= 1) return false;
    for (int i = 2; i * i <= x; ++i) {
        if (x % i == 0) return false;
    }
    return true;
}

// 计算一系列素数，然后存储到primes里面
void generatePrimes(int maxNum) {
    for (int i = 2; i <= maxNum; ++i) {
        if (isPrime(i)) {
            primes.push_back(i);
        }
    }
}


// 主函数
long long simplifyNumber(long long n, long long k) {
    long long result = 1;
    unordered_map<long long, long long> factorCount;

    // 素因数分解
    for (long long prime : primes) {
        while (n % prime == 0) {
            n /= prime;
            factorCount[prime]++;
        }
        if (n == 1) break; // 分解到1就可以终止for了
    }

    // 根据阈值 k 调整结果
    // hashmap的循环记得使用 const auto&
    for (const auto& factor : factorCount) {
        if (factor.second >= k) {
            for (long long i = 0; i < factor.second; i++) {
                result *= factor.first;
            }
        }
    }

    // result 如果等于1就意味着没有满足条件的因素因子
    return result == 1 && n > 1 ? n : result; // 如果没有任何素因子大于阈值且 n 不是 1，则返回 n
}


int main() {
    generatePrimes(1000); // 生成1000以内的所有素数

    int q;
    cin >> q;

    while (q--) {
        long long n, k;
        cin >> n >> k;
        cout << simplifyNumber(n, k) << endl;
    }

    return 0;
}

