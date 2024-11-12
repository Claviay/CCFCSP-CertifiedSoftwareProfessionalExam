import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 输入的单词数量
        int m = scanner.nextInt(); // 期望的词汇表长度
        scanner.nextLine();

        // 读取单词和频率
        Map<String, Integer> wordFrequency = new HashMap<>();
        List<String> words = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] line = scanner.nextLine().split(" ");
            String word = line[0];
            int frequency = Integer.parseInt(line[1]);
            wordFrequency.put(word, frequency);
            words.add(word);
        }

        // 统计字母频率并初始化词汇表
        Map<String, Integer> vocabFrequency = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                vocabFrequency.put(String.valueOf(c), vocabFrequency.getOrDefault(String.valueOf(c), 0) + wordFrequency.get(word));
            }
        }

        // 将所有字母按照字典序加入词汇表
        PriorityQueue<String> vocabList = new PriorityQueue<>(vocabFrequency.keySet());
        List<String> vocab = new ArrayList<>(vocabList);
        Set<String> vocabSet = new HashSet<>(vocab);

        // 用于跟踪每个单词的分词序列
        Map<String, List<String>> wordSegments = new HashMap<>();
        for (String word : words) {
            List<String> segments = new ArrayList<>();
            for (char c : word.toCharArray()) {
                segments.add(String.valueOf(c));
            }
            wordSegments.put(word, segments);
        }

        // 迭代构建词汇表
        while (vocab.size() < m) {
            // 统计词汇对出现频率
            Map<String, Integer> pairFrequency = new HashMap<>();
            for (Map.Entry<String, List<String>> entry : wordSegments.entrySet()) {
                List<String> segments = entry.getValue();
                int freq = wordFrequency.get(entry.getKey());
                for (int i = 0; i < segments.size() - 1; i++) {
                    String pair = segments.get(i) + " " + segments.get(i + 1);
                    pairFrequency.put(pair, pairFrequency.getOrDefault(pair, 0) + freq);
                }
            }

            // 找出出现次数最多的词汇对，优先选择长度最短、字典序最小的组合
            String bestPair = null;
            int bestFreq = 0;
            for (Map.Entry<String, Integer> entry : pairFrequency.entrySet()) {
                String pair = entry.getKey();
                int freq = entry.getValue();
                if (freq > bestFreq || (freq == bestFreq && pair.compareTo(bestPair) < 0)) {
                    bestPair = pair;
                    bestFreq = freq;
                }
            }

            // 如果找不到词汇对，提前终止
            if (bestPair == null) break;

            // 合并选定的词汇对并更新分词序列
            String[] parts = bestPair.split(" ");
            String newWord = parts[0] + parts[1];
            vocab.add(newWord);
            vocabSet.add(newWord);

            // 更新所有单词的分词序列
            for (List<String> segments : wordSegments.values()) {
                for (int i = 0; i < segments.size() - 1; i++) {
                    if (segments.get(i).equals(parts[0]) && segments.get(i + 1).equals(parts[1])) {
                        segments.set(i, newWord);
                        segments.remove(i + 1);
                        i--;
                    }
                }
            }
        }

        // 输出前 m 个词汇
        for (int i = 0; i < m && i < vocab.size(); i++) {
            System.out.println(vocab.get(i));
        }

        scanner.close();
    }
}
