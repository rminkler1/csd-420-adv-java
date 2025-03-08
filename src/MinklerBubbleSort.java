/*
 * Robert Minkler
 * Feb 2, 2025
 * CSD 420 Advanced Java
 * Module 6 Bubble Sort
 *
 * Create and test two overloaded bubbleSort methods.
 * One that implements Comparable and the other that accepts a comparator.
 */

import java.util.Arrays;
import java.util.Comparator;

public class MinklerBubbleSort {
    public static <E extends Comparable<E>> void bubbleSort(E[] list) {

        // Sorted tests the list to stop processing once the list is sorted
        boolean sorted = false;

        // The sorting area is limited to the unsorted portion of the array.
        int UnsortedListLength = list.length - 1;

        // test code shows progress during sort
        // System.out.println("Start: " + Arrays.toString(list));

        // A pass for each element in the array until no swaps are performed and the array is sorted.
        for (int j = 0; j < list.length - 1 && !sorted; j++) {

            // Switch sorted to true. If no swaps are needed during this pass it will stay true and the loop will end.
            sorted = true;

            // check each pair of elements and swap if the first one is larger than the second one.
            for (int i = 0; i < UnsortedListLength; i++) {

                if (list[i].compareTo(list[i + 1]) > 0) {
                    // Swap list items if the next item is larger than the current item
                    swap(i, list);
                    sorted = false;
                }

                // test code shows progress during sort
                // System.out.println("Progress: " + Arrays.toString(list));

            }

            // Each pass pushes the next largest value towards the end, meaning that the end of the list is sorted.
            // This reduces the necessary search area for each pass.
            // I don't know why I'm bothering to optimize a bubble sort, but here it is.
            UnsortedListLength--;
        }
    }

    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {

        // Sorted tests the list to stop processing once the list is sorted
        boolean sorted = false;

        // The sorting area is limited to the unsorted portion of the array.
        int UnsortedListLength = list.length - 1;

        // test code shows progress during sort
        // System.out.println("Start: " + Arrays.toString(list));

        // A pass for each element in the array until no swaps are performed and the array is sorted.
        for (int j = 0; j < list.length - 1 && !sorted; j++) {

            // Switch sorted to true. If no swaps are needed during this pass it will stay true and the loop will end.
            sorted = true;

            // check each pair of elements and swap if necessary
            for (int i = 0; i < UnsortedListLength; i++) {

                // Compare using the comparator
                if (comparator.compare(list[i], list[i + 1]) > 0) {
                    // Swap list items if the comparator is greater than zero.
                    swap(i, list);
                    sorted = false;
                }

                // test code shows progress during sort
                // System.out.println("Progress: " + Arrays.toString(list));

            }

            // Each pass pushes the next largest value towards the end, meaning that the end of the list is sorted.
            // This reduces the necessary search area for each pass.
            // Just looked at the solution code and that solution is a bit more elegant than mine, but k does the same
            // thing as this variable.
            UnsortedListLength--;
        }

    }

    private static <E> void swap(int index, E[] list) {

        // validate index value before proceeding
        if (index < list.length - 1 && index >= 0) {

            // Swap items in the list
            E temp = list[index];
            list[index] = list[index + 1];
            list[index + 1] = temp;

        } else {
            // Throw an IndexOutOfBoundsException with clear messaging.
            throw new IndexOutOfBoundsException("The BubbleSort.swap() method index value must be between 0 and list.length - 2 inclusive.");
        }
    }

    public static void main(String[] args) {

        // BubbleSort tests

        System.out.println("Robert Minkler Bubble Sort Tests.\n");

        // Already sorted array
        Integer[] myList1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        bubbleSort(myList1);
        // This was already sorted and the sort algorithm only performs one pass.
        System.out.println("Started sorted: " + Arrays.toString(myList1));

        // Random Integers including negative values with the extreme values on opposite ends.
        Integer[] myList2 = {13930984, 1254, 113, 1078, 93, 858, 7876, 612, -534, -1, 43, 354, 22, 0, 1, -100000000};
        bubbleSort(myList2);
        // sorted [-100000000, -534, -1, 0, 1, 22, 43, 93, 113, 354, 612, 858, 1078, 1254, 7876, 13930984]
        // mix of random Integers sorted with negative values where expected
        System.out.println("Random Integers: " + Arrays.toString(myList2));

        // Empty array
        Integer[] myList3 = {};
        bubbleSort(myList3);
        // []
        // the empty array is passed without issue
        System.out.println("Empty array: " + Arrays.toString(myList3));

        // Small array
        Integer[] myList4 = {1, 0};
        bubbleSort(myList4);
        // sorted [0, 1]
        // Verified that there is no off by one error and small arrays sort
        System.out.println("Two element array: " + Arrays.toString(myList4));

        // Random Integers all negative values
        Integer[] myList5 = {-13930984, -1254, -113, -1078, -93, -858, -7876, -612, -534, -43, -354, -22, -1};
        bubbleSort(myList5);
        // sorted [-13930984, -7876, -1254, -1078, -858, -612, -534, -354, -113, -93, -43, -22, -1]
        // Ensuring negative numbers sort correctly
        System.out.println("All negative Integers: " + Arrays.toString(myList5));

        System.out.println("******************************");

        // Comparator alphabetically
        String[] myList10 = {"z", "b", "c", "f", "8548", "13930984", "d", "a", "7876", "614442", "-534", "-1", "43", "354"};
        bubbleSort(myList10, String::compareTo);
        // sorted [-1, -534, 13930984, 354, 43, 614442, 7876, 8548, a, b, c, d, f, z]
        // alphabetic sort starts with special characters, then numbers, then the alphabet.
        System.out.println("Comparator sort alphabetically: " + Arrays.toString(myList10));

        // Comparator By String Length
        String[] myList11 = {"12454", "113", "1078", "93", "8548", "13930984", "d", "a", "7876", "614442", "-534", "-1", "43", "354"};
        bubbleSort(myList11, (String a, String b) -> b.length() - a.length());
        // sorted [13930984, 614442, 12454, 1078, 8548, 7876, -534, 113, 354, 93, -1, 43, d, a]
        // the longest string to the shortest string.
        System.out.println("Comparator sort by String length - largest to smallest: " + Arrays.toString(myList11));

        // Comparator reverse order
        String[] myList12 = {"z", "b", "c", "f", "8548", "13930984", "d", "a", "7876", "614442", "-534", "-1", "43", "354"};
        bubbleSort(myList12, Comparator.reverseOrder());
        // sorted [z, f, d, c, b, a, 8548, 7876, 614442, 43, 354, 13930984, -534, -1]
        // the alphabet is sorted in reverse and the numbers are sorted as strings, based on the first character of the string.
        System.out.println("Comparator Strings in reverse order: " + Arrays.toString(myList12));


        // Implement the test cases from the Example file
        System.out.println("Sample file test cases...");
        Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
        bubbleSort(list);
        // sorted [-2, 1, 2, 2, 3, 3, 5, 6, 12, 14]
        System.out.println("Example file test of Integers: " + Arrays.toString(list));

        // Implement the test cases from the Example file
        String[] list1 = {"ABC", "abc", "abm", "Anf", "Good", "Bad", "nice"};
        bubbleSort(list1, (s1, s2) -> s1.compareToIgnoreCase(s2));
        // sorted [ABC, abc, abm, Anf, Bad, Good, nice]
        System.out.println("Example file test of Strings with comparator: " + Arrays.toString(list1));
    }
}

/*
    Write a program with the two following generic methods using a bubble sort. The first method sorts the elements
    using the Comparable interface, and the second uses the Comparator interface.

        public static <E extends Comparable<E>>
         void bubbleSort(E[] list)
        public static <E> void bubbleSort(E[] list,
         Comparator<? super E> comparator)

        Attached above is a solution zip file for your review. Ensure you actually write your own code, only using the
        attached solution as an example, and document your code.
    Write test code that ensures your code functions correctly.
 */