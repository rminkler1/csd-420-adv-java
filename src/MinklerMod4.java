/*
 * Robert Minkler
 * Jan 24, 2025
 * CSD 420 Advanced Java
 * Module 4 LinkedList Traversal
 *
 * Traverse a LinkedList using an iterator and the get method. Record and discuss the results.
 */

/*
    Traversing the LinkedList through an iterator is significantly faster than the get method.

    Using an iterator, it took 92 milliseconds to traverse and print the 50k LinkedList.
    Using an iterator, it took 510 milliseconds to traverse and print the 500k LinkedList.
    Using get(index), it took 1183 milliseconds to traverse and print the 50k LinkedList.
    Using get(index), it took 2255 milliseconds to traverse and print the 500k LinkedList.

    An iterator steps through the LinkedList, one object at a time, returning the next object. When traversing a
    LinkedList using the get method, the get method must traverse the list from the beginning, stepping past each object
    until the desired object is located and returned. Instead of traversing the list once, as the iterator does, the
    get(index) method traverses the list every time the get method is called.
    A for-each loop or the forEach() method is an easy way to access an iterator to iterate through each object.

    The only thing that surprises me is the speed of iterating through 500k Integers using get. I expected it to take
    much longer than it did due to the additional work of iterating through a long LinkedList 500k times. I suspect
    Java's implementation of get() is optimized to start from the closest end instead of starting from the beginning
    each time.
 */

import java.util.LinkedList;

public class MinklerMod4 {

    public static void main(String[] args) {

        // Time calculations found at
        // https://stackoverflow.com/questions/180158/how-do-i-time-a-methods-execution-in-java
        // track timing variables
        long startTime;
        long endTime;

        // Build a LinkedList with 50,000 Integers and 500,000
        LinkedList<Integer> listOf50k = BuildLinkedList(50000, 10000);
        LinkedList<Integer> listOf500k = BuildLinkedList(500000, 10000);

        /*
         * Using an iterator
         */

        // 50,000
        // start the timer
        startTime = System.currentTimeMillis();

        // Traverse the list using an iterator
        // about 70 milliseconds
        listOf50k.forEach((e) -> {
            System.out.println(e);
        });

        // end the timer
        endTime = System.currentTimeMillis();

        // Calculate and output iterator time
        long iteratorTime50k = endTime - startTime;

        // 500,000
        // start the timer
        startTime = System.currentTimeMillis();

        // Traverse the list using an iterator
        // about 500 milliseconds
        listOf500k.forEach((e) -> {
            System.out.println(e);
        });

        // end the timer
        endTime = System.currentTimeMillis();

        // Calculate and output iterator time
        long iteratorTime500k = endTime - startTime;


        /*
         * Using get(index)
         */

        // 50,000
        startTime = System.currentTimeMillis();

        // Traverse the list using get(index)
        // about 1000 milliseconds
        for (int i = 0; i < listOf50k.size(); i++) {
            System.out.println(listOf50k.get(i));
        }

        endTime = System.currentTimeMillis();

        long getMethodTime50k = endTime - startTime;


        // 500,000
        startTime = System.currentTimeMillis();

        // Traverse the list using get(index)
        // about 2000 milliseconds
        for (int i = 0; i < listOf500k.size(); i++) {
            System.out.println(listOf500k.get(i));
        }

        endTime = System.currentTimeMillis();

        long getMethodTime500k = endTime - startTime;


        // Output the results to the console
        System.out.println("Using an iterator, it took " + iteratorTime50k +
                " milliseconds to traverse and print the 50k LinkedList.");

        System.out.println("Using an iterator, it took " + iteratorTime500k +
                " milliseconds to traverse and print the 500k LinkedList.");

        System.out.println("Using get(index), it took " + getMethodTime50k +
                " milliseconds to traverse and print the 50k LinkedList.");

        System.out.println("Using get(index), it took " + getMethodTime500k +
                " milliseconds to traverse and print the 500k LinkedList.");
    }

    // build a linkedList with n random integers from 0 up to, but excluding max
    public static LinkedList<Integer> BuildLinkedList(int n, int max) {

        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            list.add((int) (Math.random() * max));
        }

        return list;
    }
}

/*
    Assignment Directions
    Write a test program that stores 50,000 integers in LinkedList and test the time to traverse the list using an
    iterator vs. using the get(index) method.
    Then, test your program storing 500,000 integers.
    After completing this program and having tested both values, in your comments, explain the results and discuss
    the time taken using both values and their difference with the get(index) approach.
    Write test code that ensures the code functions correctly.
 */