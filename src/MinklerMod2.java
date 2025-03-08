/*
 * Robert Minkler
 * Jan 17, 2025
 * CSD-420 Module 2 Recursion
 *
 * Recursively calculate the result of m(i) = 1/2 + 2/3 + 3/4 + 4/5 + 5/6 … i/(i + 1)
 */

public class MinklerMod2 {

    public static void main(String[] args) {

        // Run Test Cases
        System.out.println("Test 0: " + fracSum(0));
        System.out.println("Test 4: " + fracSum(4));
        System.out.println("Test 9: " + fracSum(9));
        System.out.println("Test 14: " + fracSum(14));
    }

    public static double fracSum(int num) {

        // Assumption - input must be a positive int
        // Base Case returns zero
        if (num <= 0) {
            return 0.0;

        } else {
            // build fraction and add prior results to it.
            return num / (num + 1.0) + fracSum(--num);
        }
    }
}


//    Create a program using a recursive method that returns the sum of the following type inputs.
//
//        m(i) = 1/2 + 2/3 + 3/4 + 4/5 + 5/6 … i/(i + 1)
//
//        ‘m’ is to be replaced with the method name you use.
//    Use three different input finishing values, testing your code to ensure it functions correctly.
//        Examples: m(4), m(9), m(14).
//
//4 = 2.716666666666667
//9 = 7.071031746031746
//14 - 11.681771006771008
