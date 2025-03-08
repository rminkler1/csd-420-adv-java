public class Test implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Test myTest = new Test();               // Create the runnable object instance
        Thread thread = new Thread(myTest);     // Create a thread to execute the runnable object
        thread.start();                         // Start the thread, running the run method in a new thread.
    }

    public synchronized void run() {
        // This code runs when the thread starts
        System.out.println("A new thread is running.");
    }
}