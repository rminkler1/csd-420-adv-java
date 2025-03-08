/*
 * Robert Minkler
 * Jan 9, 2025
 * CSD420 Advanced Java
 * Module 1 Programming Assignment
 *
 * Write two arrays and a Date object, then read them back in another file.
 *
 */


import java.io.*;
import java.util.Arrays;
import java.util.Date;

public class Mod1Read {
    public static void main(String[] args) {

        // Variables
        String fileName = "robert_minkler_datafile.dat";

        // Connect to file to read it
        try (ObjectInputStream input =
                     new ObjectInputStream(
                             new BufferedInputStream(
                                     new FileInputStream(fileName)))
        ) {

            // Read objects from the file
            int[] intArray = (int[]) (input.readObject());
            Date date = (Date) (input.readObject());
            double[] dblArray = (double[]) (input.readObject());

            // Output the read data to the console
            System.out.println("Data read from " + fileName + " successfully.");
            System.out.println("Array of int: " + Arrays.toString(intArray));
            System.out.println("Date: " + date);
            System.out.println("Array of doubles: " + Arrays.toString(dblArray));


        } catch (FileNotFoundException e) {

            // Print FNF error to the console
            System.err.println("********************************************************\n" +
                    fileName + " not found.\n" +
                    "You must run Mod1Write.java before running Mod1Read.java\n" +
                    "********************************************************\n");
            e.printStackTrace();
            System.exit(1);

        } catch (IOException e) {

            // Print error to the console
            System.err.println("IOException. Unable to read from the file.");
            e.printStackTrace();
            System.exit(2);

        } catch (ClassNotFoundException e) {

            // Print error to the console
            System.err.println("ClassNotFoundException. Unable to read from the file.");
            e.printStackTrace();
            System.exit(3);
        }
    }
}
