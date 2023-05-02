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
                        Manager.clearScreen();
                        System.out.print(Manager.getCurrentSim().getName() + " is " + Manager.getCurrentSim().getStatus());
                        long i = timeNow % 3;
                        if (i == 0) {
                            System.out.println(".");
                        } else if (i == 1) {
                            System.out.println("..");
                        } else {
                            System.out.println("...");
                        }
                        System.out.println("Time : " + timeNow);
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

    public static void trackDays(){ // to know when it changes days
        Thread day = new Thread() {
            public void run() {
                while (true) {
                    if (timeNow % 720 == 0) {
                        Manager.dailyReset();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
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
            if(Manager.isCurrentSimWorking()){
                // can't do anything since the sim is still working
            }else{
                Manager.listCanDo();
                Manager.doQuery();
            }

        }

        System.exit(0);
    }
}