
import java.util.Scanner;


class MyThread extends Thread {
    private String threadName;
    private volatile boolean running = true;

    MyThread(String name) {
        threadName = name;
    }


    @Override
    public void run() {
        int i = 0;
        while (running) {
            System.out.println(threadName + " - progress: " + i);
            i++;
            try {

                Thread.sleep(600);
            } catch (InterruptedException e) {
                System.out.println(threadName + " byl prerusen.");
            }
        }
        System.out.println(threadName + " skoncilo");
    }


    public void stopRunning() {
        running = false;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        MyThread thread1 = new MyThread("Thread 1");
        MyThread thread2 = new MyThread("Thread 2");


        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Spustit vlákna");
            System.out.println("2. Zastavit vlákna");
            System.out.println("3. Konec");

            System.out.print("Zadejte volbu: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:

                    thread1.start();
                    thread2.start();
                    break;

                case 2:

                    thread1.stopRunning();
                    thread2.stopRunning();
                    try {

                        thread1.join();
                        thread2.join();
                    } catch (InterruptedException e) {
                        System.out.println("vlákno preruseno");
                    }
                    break;

                case 3:

                    thread1.stopRunning();
                    thread2.stopRunning();
                    try {

                        thread1.join();
                        thread2.join();
                    } catch (InterruptedException e) {
                        System.out.println("Vlákno preruseno");
                    }
                    System.out.println("Konec");
                    scanner.close();
                    return;

                default:
                    System.out.println("Error");
            }
        }
    }
}
