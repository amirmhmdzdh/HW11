package ThreadHW11.Q3;

public class Starvation implements Runnable {
    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + " is running");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Starvation starvation = new Starvation();

        //*THREAD*

        Thread thread0 = new Thread(starvation);
        Thread thread1 = new Thread(starvation);
        Thread thread2 = new Thread(starvation);
        Thread thread3 = new Thread(starvation);
        Thread thread4 = new Thread(starvation);

        //*PRIORITY*

        thread0.setPriority(10);
        thread1.setPriority(5);
        thread2.setPriority(4);
        thread3.setPriority(2);
        thread4.setPriority(1);


        //*START*

        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }
}
