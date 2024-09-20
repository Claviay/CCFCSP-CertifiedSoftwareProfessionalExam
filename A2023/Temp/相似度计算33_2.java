import java.io.*;
import java.util.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int book1 = Integer.parseInt(input[0]);
        int book2 = Integer.parseInt(input[1]);

        String temp = br.readLine().toLowerCase();
        String[] book1Content = temp.split(" ");

        temp = br.readLine().toLowerCase();
        String[] book2Content = temp.split(" ");

        HashSet<String> book1Set = new HashSet<>();
        HashSet<String> book2Set = new HashSet<>();
        for (int i = 0; i < book1Content.length; i++) {
            book1Set.add(book1Content[i]);
        }
        for (int i = 0; i < book2Content.length; i++) {
            book2Set.add(book2Content[i]);
        }

        int book1Size = book1Set.size();
        int book2Size = book2Set.size();

//        int bothInBooks = 0;
//        for (String book : book1Set) {
//            if (book2Set.contains(book)) {
//                bothInBooks++;
//            }
//        }
//
//        int totalDifferentWordsNum = book1Size + book2Size - bothInBooks;
//
//
//        System.out.println(bothInBooks);
//        System.out.print(totalDifferentWordsNum);

        int bothInBooks;
        int totalDifferentWordsNum;

        book1Set.retainAll(book2Set);
        bothInBooks = book1Set.size();
        totalDifferentWordsNum = book1Size + book2Size - bothInBooks;

        System.out.println(bothInBooks);
        System.out.print(totalDifferentWordsNum);
    }


}
