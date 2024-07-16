package B12_3;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(2, 1, 3);
        List<Integer> list2 = Arrays.asList(2, 3, 1);

        int size = Math.min(list1.size(), list2.size());
        for (int i = 0; i < size; i++) {
            int comparisonResult = list1.get(i).compareTo(list2.get(i));
            if (comparisonResult != 0) {

            }
        }
    }
}
