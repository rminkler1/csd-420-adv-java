/*
 * Robert Minkler
 * Jan 9, 2025
 * CSD420 Advanced Java
 * Module 1 Programming Assignment
 *
 * Write two arrays and a Date object, then read them back in another file.
 *
 */

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Date;

public class Mod1Write {

    public static void main(String[] args) {

        // Variables
        int arrayLength = 5;
        String fileName = "robert_minkler_datafile.dat";

        // Build int array, Date, and double array
        int[] intArray = getIntArray(arrayLength);
        Date now = new Date();
        double[] dblArray = getDblArray(arrayLength);

        // Build file connection
        try (ObjectOutputStream output =
                     new ObjectOutputStream(
                             new BufferedOutputStream(
                                     new FileOutputStream(fileName)))
        ) {

            // Write the objects to the file
            output.writeObject(intArray);
            output.writeObject(now);
            output.writeObject(dblArray);

        } catch (IOException e) {

            // Print error to the console
            System.err.println("The system encountered an error while attempting to write the file to disk. Nothing was saved.");
            e.printStackTrace();
            System.exit(1);
        }

        // Output results to the console so we know what was written to the file.
        System.out.println("All objects written to the file " + fileName + ".");
        System.out.println("Array of int: " + Arrays.toString(intArray));
        System.out.println("Date: " + now);
        System.out.println("Array of doubles: " + Arrays.toString(dblArray));
    }

    // Build an array of a given length and assign a random int to each location
    private static int[] getIntArray(int length) {

        // Initialize Array of int
        int[] intArray = new int[length];

        // Assign random values to the array
        for (int i = 0; i < length; i++) {
            intArray[i] = (int) (Math.random() * 100);
        }
        return intArray;
    }

    // Build an array of a given length and assign a random double to each location
    private static double[] getDblArray(int length) {

        // Initialize Array of double
        double[] dblArray = new double[length];

        // Assign random values to the array
        for (int i = 0; i < length; i++) {
            dblArray[i] = Math.random() * 100;
        }
        return dblArray;
    }
}


//Assignment:
//
//    Write a program that stores:
//        An array of five random integers
//        A Date objective instance using the current date
//        An array of five random double values
//    Write the data in a file titled <yourname> datafile.dat.
//    Write a second program that will read the file and display the data.
//    Test the code to ensure the code functions correctly.
//
//Assignment Requirements and Grading:
//
//    This assignment is due by Sunday, 11:59 p.m., CST.
//    Add the necessary documentation as described in Documentation Requirements Click for more options
//    Submit your .java file(s) by clicking on the Assignment Link above, then scroll down to the Attach Files section
//    and click on Browse My Computer. Select your assignment file, add any comments as appropriate, and then click on Submit.
//    To view the grading rubric for this assignment, click on the following: Programming Rubric
//
//(50 points)