public class BinarySearch {

    public static void main(String[] args) {

        int[] testArray = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

        // Test cases
        System.out.println("10 is at index " + recursiveSearch(testArray, 10));
        System.out.println("30 is at index " + recursiveSearch(testArray, 30));
        System.out.println("100 is at index " + recursiveSearch(testArray, 100));
        System.out.println("0 is not found and returns " + recursiveSearch(testArray, 0));
        System.out.println("1000 is not found and returns " + recursiveSearch(testArray, 1000));
    }

    public static int recursiveSearch(int[] array, int value) {

        // set pointers to define the search area
        int lo = 0;
        int hi = array.length - 1;

        // run the recursive search
        return recursiveSearch(array, value, lo, hi);
    }

    public static int recursiveSearch(int[] array, int value, int lo, int hi) {

        // get midpoint
        int mid = (lo + hi) / 2;

        // value not found - Exit with negative int
        if (lo > hi)
            return -lo - 1;

        // value found - return index
        if (array[mid] == value)
            return mid;

        // value not found - value is after mid
        if (array[mid] < value)
            return recursiveSearch(array, value, mid + 1, hi);

        // value not found - value is before mid
        return recursiveSearch(array, value, lo, mid - 1);
    }
}
