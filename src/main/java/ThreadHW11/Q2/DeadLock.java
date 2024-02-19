package ThreadHW11.Q2;

public class DeadLock {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();


    public static void main(String[] args) {

        // Thread thread1 = new Thread(new Runnable()

        Thread thread1 = new Thread(() -> {

            synchronized (lock1) {
                System.out.println("Thread 1 lock1 ");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock2) {
                    System.out.println("Thread 2 lock2");
                }
            }
        });

        //Thread thread2 = new Thread(new Runnable()

        Thread thread2 = new Thread(() -> {

            synchronized (lock2) {
                System.out.println(" Thread 2 lock2 ");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock1) {
                    System.out.println(" Thread 2 lock1 ");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
