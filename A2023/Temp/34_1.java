import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n, m;
        int p, q;
        String[] line1 = br.readLine().split(" ");

        // 原本的矩阵 n*m
        n = Integer.parseInt(line1[0]);
        m = Integer.parseInt(line1[1]);

        // 更改后的矩阵 p*q
        p = Integer.parseInt(line1[2]);
        q = Integer.parseInt(line1[3]);

        ArrayList<Integer> list = new ArrayList<>(1005);
        for (int i = 0; i < n; i++) {
            String[] line2 = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                list.add(Integer.parseInt(line2[j]));
            }
        }

        int index = 0;
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < q; j++) {
                System.out.print(list.get(index++) + " ");
            }
            System.out.println();
        }

    }


}
