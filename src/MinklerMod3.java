/*
 * Robert Minkler
 * Jan 17, 2025
 * CSD 420 Advanced Java
 * Module 3 Generic Method
 *
 * Remove duplicates from an ArrayList in a static method. Test it using a random list of Integers 1-20
 */

import java.util.ArrayList;
import java.util.HashSet;

public class MinklerMod3 {
    public static void main(String[] args) {

        int initCapacity = 50;  // initial array capacity
        int min = 1;            // minimum random value
        int max = 20;           // maximum random value

        // Build a list of Integers
        ArrayList<Integer> intList = new ArrayList<>(initCapacity);

        // Populate the list with random Integers between min and max
        for (int i = 0; i < initCapacity; i++) {
            intList.add((int) (min + Math.random() * (max - min + 1)));
        }

        // Display the original list then process and display the no duplicates list
        System.out.println("Random List of " + intList.size() + ":\n" + intList);
        System.out.println("After Duplicates are removed:\n" + removeDuplicates(intList));
    }

    // APPROACH #2
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        // Remove duplicates by converting the ArrayList to a HashSet then back into an ArrayList to return
        HashSet<E> set = new HashSet<>(list);
        return new ArrayList<>(set);
    }


    // APPROACH #1 works but APPROACH #2 is much faster due to the .contains() method overhead.
    public static <E> ArrayList<E> removeDuplicatesApproach1(ArrayList<E> list) {

        // Create new ArrayList to store the results
        ArrayList<E> noDuplicatesList = new ArrayList<>();

        // Add objects to the new ArrayList only if they are not already present.
        for (E object : list) {
            if (!noDuplicatesList.contains(object)) {
                noDuplicatesList.add(object);
            }
        }

        return noDuplicatesList;
    }
}

//    Write a test program that contains a static method that returns a new ArrayList.
//        The new ArrayList returned will contain all original values with no duplicates from the original ArrayList.
//        Fill the Original ArrrayList with 50 random values from 1 to 20.
//
//        public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list)
//
//    Write test code that ensures the code functions correctly.