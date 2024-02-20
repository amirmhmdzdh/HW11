package ThreadHW11.Q1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class NumberListProgram {

    //*Fields*
    private static final List<Integer> evenList = new ArrayList<>();
    private static final List<Integer> oddList = new ArrayList<>();
    private static final List<Integer> combinedList = new ArrayList<>();
    private static int inputNumber = 0;
    private static final Scanner scanner = new Scanner(System.in);
    private static final Object lock = new Object();

    public static void main(String[] args) {
        getInputNumberFromUser();

        Thread evenThread = new Thread(new EvenNumberRunnable());
        Thread oddThread = new Thread(new OddNumberRunnable());

        evenThread.start();
        oddThread.start();

        try {
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void getInputNumberFromUser() {

        boolean isValidInteger = false;
        while (!isValidInteger) {
            System.out.println("Please Enter Number : ");
            try {
                inputNumber = scanner.nextInt();
                scanner.nextLine();
                isValidInteger = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter an Integer: ");
                scanner.nextLine();
            }
        }
    }

    static class EvenNumberRunnable implements Runnable {
        @Override
        public void run() {
            int finalInput = inputNumber;

            for (int i = 0; i <= inputNumber; i += 2) {
                if (i != finalInput) {
                    synchronized (lock) {
                        evenList.add(i);
                        System.out.println("evenInserterThread added " + evenList);
                        lock.notifyAll();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    evenList.add(i);
                    System.out.println("evenInserterThread added " + evenList);
                    combinedList.addAll(oddList);
                    combinedList.addAll(evenList);

                    System.out.println("Combined List: " + combinedList);

                }
            }
        }
    }

    static class OddNumberRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= inputNumber; i += 2) {
                synchronized (lock) {
                    oddList.add(i);
                    System.out.println("oddInserterThread added " + oddList);
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}