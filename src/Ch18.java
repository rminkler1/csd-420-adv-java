public class Ch18 {
    public static void main(String[] args) {
        System.out.println(twoOfN(2));
        System.out.println(twoOfN(3));
        System.out.println(twoOfN(4));
        System.out.println(twoOfN(5));

        System.out.println(xOfN(3, 2));
        System.out.println(xOfN(3, 3));
        System.out.println(xOfN(3, 4));
        System.out.println(xOfN(3, 5));

        System.out.println(factorial(5));

        System.out.println(progressiveSum(1));
        System.out.println(progressiveSum(2));
        System.out.println(progressiveSum(3));
        System.out.println(progressiveSum(4));
        System.out.println(progressiveSum(0));

    }

    public static int twoOfN(int n) {
        if (n == 1) {
            return 2;
        } else {
            return 2 * twoOfN(n - 1);
        }
    }

    public static int xOfN(int x, int n) {
//        if (n == 1) {
//            return x;
//        } else {
//            return x * xOfN(x, n - 1);
//        }

        return (n == 1) ? x : x * xOfN(x, n - 1);
    }

    public static int factorial(int n) {
        return (n == 0) ? 1 : n * factorial(n - 1);
    }

    public static int progressiveSum(int n) {
        return (n <= 1) ? n : n + progressiveSum(n - 1);
    }
}
