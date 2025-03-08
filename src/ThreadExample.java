public class ThreadExample {
    public static void main(String[] args) {

        PrintChar printChar = new PrintChar();
        Thread charThread = new Thread(printChar);

        PrintInt printInt = new PrintInt();
        Thread intThread = new Thread(printInt);

        charThread.start();
        intThread.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("Main Thread");
        }
    }

}

class PrintChar implements Runnable {
    char x = 'a';

    @Override
    public void run() {
        try {

            while (x <= 'z') {
                System.out.println(x);
                x++;
                Thread.sleep(1);
            }

        } catch (InterruptedException _) {
        }
    }
}

class PrintInt implements Runnable {
    int y = 0;

    @Override
    public void run() {

        while (y <= 100) {
            System.out.println(y);
            y++;
            Thread.yield();
        }
    }
}