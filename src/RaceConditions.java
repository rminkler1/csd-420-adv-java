import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RaceConditions {


    public static void main(String[] args) {
        Account account = new Account();

        try (ExecutorService executorService = Executors.newCachedThreadPool()) {

            for (int i = 0; i < 1000; i++) {
                executorService.execute(() -> account.deposit(1));
            }
        }

        System.out.println(account.getBalance());
    }


}

class Account {

    private int balance;
    private final Lock myLock = new ReentrantLock();

    public Account() {
        balance = 0;
    }

    public Account(int initBal) {
        balance = initBal;
    }

    public synchronized int deposit(int deposit) {
//        myLock.lock();
//
//
//        try {
//
        balance += deposit;
////            Thread.sleep(5);
////
////        } catch (InterruptedException _) {
//        } finally {
//            myLock.unlock();
//        }

        return balance;
    }

    public int getBalance() {
        return balance;
    }
}
