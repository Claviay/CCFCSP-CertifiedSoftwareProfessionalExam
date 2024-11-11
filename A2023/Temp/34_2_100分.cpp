
#include<iostream>
using namespace std;

// 定义全局变量n, m, t，分别表示矩阵的行数、列数和操作次数
int n, m, t;

int main() {
    // 从标准输入读取矩阵的行数n、列数m和操作次数t
    cin >> n >> m >> t;
    
    // 定义变量n1和m1，用于记录矩阵重塑或转置后的新尺寸
    int n1 = n, m1 = m;
    
    // 定义一维数组array，用于存储矩阵的所有元素，大小为n*m
    int array[n * m], num = 0;
    
    for (int i = 0; i < n; i++) {      
        for (int j = 0; j < m; j++) {         
            cin >> array[num++];  // 读取元素并存储到array中，同时递增num
        }
    }
    
    // 定义变量a, b, op，用于存储操作类型及其参数
    int a, b, op;
    
    // 循环t次，执行t个操作
    for (int i = 0; i < t; i++) {
        cin >> op >> a >> b;   
        
        if (op == 1) {     // 操作类型1：重塑矩阵
            n1 = a;         // 更新矩阵的行数为a
            m1 = b;          // 更新矩阵的列数为b
        }
        
        if (op == 2) {      // 操作类型2：转置矩阵
            int array1[n * m];   // 创建一个临时数组array1，用于存储转置后的矩阵
            // 遍历当前矩阵的所有元素，并将它们转置到临时数组array1中
            for (int i = 0, x = 0; i < n * m; i++, x++) {
            //转置前后的二维矩阵的一维形式符合一定规律：array1[(x%m1)*n1+x/m1]=array[x]; 
                array1[(x % m1) * n1 + x / m1] = array[x]; 
            }
            
            // 将转置后的矩阵复制回array
            for (int i = 0; i < n * m; i++) {
                array[i] = array1[i];
            }
            // 交换n1和m1，因为转置后行数和列数互换
            int temp = n1;
            n1 = m1;
            m1 = temp;
        }

        if (op == 3) {    // 操作类型3：查询矩阵元素
            // 输出位于第a行，第b列的元素（使用转置后的尺寸计算索引）
            cout << array[m1 * a + b] << endl;
        }
    }
    return 0;
}


