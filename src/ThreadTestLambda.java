public class ThreadTestLambda {
    public static void main(String[] args) {

        // Define the new thread with a lambda
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
            }
        });

        // Start the thread
        thread.start();
    }
}
