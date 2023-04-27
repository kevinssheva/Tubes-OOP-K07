package src;

import src.Manager.*;

public class Main {
    public static long timeNow = 0; // time is in second

    public static void runTheTime() {
        Thread temp = new Thread() {
            public void run() {
                while (true) {
                    if (Manager.isThereAnyWorkingSim()) {
                        timeNow++;
                        System.out.println(timeNow);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        temp.start();
    }

    public static void main(String args[]) {
        runTheTime();
        System.out.println("Testing");
    }
}