package main.java.string_operations_performance.assigment_problems;
import java.util.*;

public class WordFrequencyReport {

    static void printFilteredWordFrequency(String feedback) {

        // Convert to lowercase
        String cleaned = feedback.toLowerCase();

        // Remove punctuation
        cleaned = cleaned.replace(".", "");
        cleaned = cleaned.replace(",", "");

        // Split into words
        String[] words = cleaned.split("\\s+");

        // Stop words
        String[] stopWords = {
            "the", "was", "and", "a", "is", "of", "in"
        };

        HashMap<String, Integer> frequency = new HashMap<>();

        // Count words
        for (int i = 0; i < words.length; i++) {

            boolean isStopWord = false;

            // Check whether word is a stop word
            for (int j = 0; j < stopWords.length; j++) {

                if (words[i].equals(stopWords[j])) {
                    isStopWord = true;
                    break;
                }
            }

            if (!isStopWord) {
                frequency.put(
                    words[i],
                    frequency.getOrDefault(words[i], 0) + 1
                );
            }
        }

        // Convert map entries to list
        ArrayList<Map.Entry<String, Integer>> list =
                new ArrayList<>(frequency.entrySet());

        // Sort by frequency in descending order
        list.sort((a, b) -> b.getValue() - a.getValue());

        // Print result
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter feedback: ");
        String feedback = sc.nextLine();

        printFilteredWordFrequency(feedback);

        sc.close();
    }
}