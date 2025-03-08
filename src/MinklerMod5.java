/*
 * Robert Minkler
 * Jan 28, 2025
 * CSD 420 Advanced Java
 * Module 5 Maps and Sets
 *
 * Read all words from a file, then display all non duplicated words in ascending order, then descending order.
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class MinklerMod5 {

    // set file name constant - THE TRAGEDY OF ROMEO AND JULIET
    final static String fileName = "collection_of_words.txt";


    public static void main(String[] args) {

        try {
            // Create a word counter (HashMap) from the file
            Map<String, Integer> counterMap = wordCounter(new File(fileName));


            System.out.println("Begin Ascending order:");

            // Output in ascending order
            displayMapAscending(counterMap);

            System.out.println("\nAscending order complete.");
            System.out.println("\nBegin Descending order:");

            // Output in descending order
            displayMapDescending(counterMap);

            System.out.println("\nDescending order complete.");

        } catch (FileNotFoundException e) {
            // Handle FileNotFoundException
            System.out.println("File " + fileName + " Not Found!");
        }
    }

    public static Map<String, Integer> wordCounter(File file) throws FileNotFoundException {

        // Create a map to count word instances
        Map<String, Integer> counter = new HashMap<>();

        // Open the file for input
        Scanner input = new Scanner(new FileInputStream(file));

        // Process the file word by word removing punctuation and storing it in the counter
        while (input.hasNext()) {

            // get next word
            String word = input.next();

            // Remove punctuation from words and convert to lowercase
            // https://stackoverflow.com/questions/18830813/how-can-i-remove-punctuation-from-input-text-in-java
            String cleanedWord = word.replaceAll("[^a-zA-Z '’]", "").toLowerCase();

            if (cleanedWord.isEmpty()) continue;  // do not count empty strings, go to next word

            // Add word to the counter or increment the counter if word has already been added to the counter
            if (!counter.containsKey(cleanedWord)) {
                counter.put(cleanedWord, 1);
            } else {
                int value = counter.get(cleanedWord);
                counter.put(cleanedWord, ++value);
            }
        }
        return counter;
    }

    public static void displayMapAscending(Map<String, Integer> map) {

        // Convert HashMap to TreeMap sorted with default sorting
        TreeMap<String, Integer> sortedMap = new TreeMap<>(map);

        // Iterate over each entry, printing it if it only occurs once in the text
        sortedMap.forEach((word, count) -> {
            if (count == 1) System.out.print(word + ", ");
        });
    }

    public static void displayMapDescending(Map<String, Integer> map) {
        // Create TreeMap with reverse sorting - Comparator.reverseOrder() would work the same.
        TreeMap<String, Integer> sortedMap = new TreeMap<>((a, b) -> b.compareTo(a));

        // Add HashMap to the TreeMap
        sortedMap.putAll(map);

        // Iterate through the tree, printing each item that was only counted once.
        sortedMap.forEach((word, count) -> {
            if (count == 1) System.out.print(word + ", ");
        });
    }
}

/*
    Write a test program that reads words from a text file and displays all non-duplicate words in ascending order and
    then in descending order.
        The file is to be referenced in the program, not needing to be used as a command line reference.
        The word file is to be titled collection_of_words.txt and included in your submission.
    Write test code that ensures the code functions correctly.
*/