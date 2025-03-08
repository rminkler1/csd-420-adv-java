/**
 * Robert Minkler
 * Feb 12, 2025
 * CSD 420 Advanced Java
 * Module 8
 * <p>
 * Read all words from a file, then display all non duplicated words in ascending order, then descending order.
 */

/*
    Create a class titled <your first name here> ThreeThreads.
        In this class, you are to use three threads to output three types of characters to a text area for display.
            In the first thread, you are to output random letter characters such as a, b, c, d …
            In the second thread, you are to output random number digits such as 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
            In the third thread, you are to output random characters such as !, @, #, $, %, &, *
        Display a minimum of 10,000 of each of the three sets.
    Write test code that ensures all methods function correctly.
*/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class RobertMThreeThreads {
    public static void main(String[] args) {

        // How many characters to print of each collection
        int count = 10000;

        // Create Runnable objects that will print random characters
        PrintRandChar aToz = new PrintRandChar('a', 'z', count);
        PrintRandChar special = new PrintRandChar('!', '/', count);
        PrintRandChar num = new PrintRandChar('0', '9', count);

        // Execute the three Runnable objects in their own threads using an ExecutorService.
        // ExecutorService is autocloseable as of Java 19.
        try (ExecutorService executor = Executors.newFixedThreadPool(3)) {
            executor.execute(aToz);
            executor.execute(special);
            executor.execute(num);
        }
    }
}

// Runnable class to print random characters between start and end.
class PrintRandChar implements Runnable {
    char start;
    char end;
    int count;

    /**
     * Initialize a PrintRandChar object.
     * Requires the start character, the end character, and the number of characters to print to the console.
     * Characters are randomly selected based on their ASCII code, between start and end inclusive.
     *
     * @param start the first possible ASCII character
     * @param end   the last possible ASCII character
     * @param count the number of times to print a random character to the console
     */
    public PrintRandChar(char start, char end, int count) {
        this.start = start;
        this.end = end;
        this.count = count;
    }

    /**
     * This is the code that is executed when a new thread is started.
     * It loops through the specified number of times based on the submitted count value.
     * Then it outputs the characters it is using along with a random character from that series of characters.
     */
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println(start + " to " + end + ": " + getRandChar());
        }
    }

    /// @return random character between {@code start} and {@code end} characters inclusive.
    private char getRandChar() {
        // ThreadLocalRandom generates a thread specific seed without locking any resources.
        return (char) ThreadLocalRandom.current().nextInt(start, end + 1);
    }
}