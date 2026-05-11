import java.util.HashMap;
import java.util.Map;

public class WordCounter {
    
    /**
     * Counts the frequency of each word in a sentence
     * @param sentence The input sentence
     * @return A Map with words as keys and their counts as values
     */
    public static Map<String, Integer> countWords(String sentence) {
        Map<String, Integer> wordCount = new HashMap<>();
        
        // Convert to lowercase and split by whitespace and punctuation
        String[] words = sentence.toLowerCase()
                                 .replaceAll("[^a-z0-9\\s]", "")
                                 .split("\\s+");
        
        // Count each word
        for (String word : words) {
            if (!word.isEmpty()) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }
        
        return wordCount;
    }
    
    /**
     * Alternative method using computeIfPresent and putIfAbsent
     */
    public static Map<String, Integer> countWordsAlternative(String sentence) {
        Map<String, Integer> wordCount = new HashMap<>();
        
        String[] words = sentence.toLowerCase()
                                 .replaceAll("[^a-z0-9\\s]", "")
                                 .split("\\s+");
        
        for (String word : words) {
            if (!word.isEmpty()) {
                wordCount.putIfAbsent(word, 0);
                wordCount.put(word, wordCount.get(word) + 1);
            }
        }
        
        return wordCount;
    }
    
    /**
     * Method using merge (Java 8+)
     */
    public static Map<String, Integer> countWordsMerge(String sentence) {
        Map<String, Integer> wordCount = new HashMap<>();
        
        String[] words = sentence.toLowerCase()
                                 .replaceAll("[^a-z0-9\\s]", "")
                                 .split("\\s+");
        
        for (String word : words) {
            if (!word.isEmpty()) {
                wordCount.merge(word, 1, Integer::sum);
            }
        }
        
        return wordCount;
    }
    
    public static void main(String[] args) {
        String sentence = "Hello World! Hello Java. World is beautiful.";
        
        System.out.println("Original Sentence: " + sentence);
        System.out.println("\n--- Method 1: Using getOrDefault ---");
        Map<String, Integer> result1 = countWords(sentence);
        printWordCount(result1);
        
        System.out.println("\n--- Method 2: Using putIfAbsent ---");
        Map<String, Integer> result2 = countWordsAlternative(sentence);
        printWordCount(result2);
        
        System.out.println("\n--- Method 3: Using merge ---");
        Map<String, Integer> result3 = countWordsMerge(sentence);
        printWordCount(result3);
        
        // Additional test case
        String sentence2 = "Java is great. Java developers love Java.";
        System.out.println("\n\nOriginal Sentence: " + sentence2);
        System.out.println("--- Word Count ---");
        Map<String, Integer> result4 = countWords(sentence2);
        printWordCount(result4);
    }
    
    /**
     * Helper method to print the word count map
     */
    private static void printWordCount(Map<String, Integer> wordCount) {
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
