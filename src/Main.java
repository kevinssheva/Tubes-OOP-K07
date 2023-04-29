package src;

import java.util.Scanner;
import src.World.*;
import src.Manager.*;
import src.Home.*;

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
        while(Manager.getGameStarted() == false)
        {
            Manager.gameMenu();
        }
        // generate the first sim. Otherwise, the game won't start
        Manager.generateSim();
        // world will automatically generated in the manager
        while(Manager.canGameContinue())
        {
            Manager.listCanDo();
            Manager.doQuery();
        }
    }
}