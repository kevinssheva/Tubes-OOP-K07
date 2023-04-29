package src;

import java.util.Scanner;

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

        Scanner input = new Scanner(System.in);

        boolean gameStarted = false;

        while (!gameStarted) {
            System.out.println("Here is the menu");
            System.out.println("1. Start Game");
            System.out.println("2. Help");
            System.out.println("3. Exit");
            int inputCommand = input.nextInt();
            switch (inputCommand) {
                case 1:
                    gameStarted = true;
                    break;
                case 2:
                    Manager.help();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
        Manager.clearScreen();
        System.out.println("Hello, welcome to your very first game !\nBefore we start the game, we have to figure out your sim's characteritic first\nPlease your sim's attribute first");
        // Manager.timer(3);
        Manager.generateSim();

        while (Manager.canGameContinue()) {
            Manager.listCanDo();
            int inputCommand = input.nextInt();
            switch (inputCommand) {
                case 1:
                    Manager.viewSimInfo();
                    Manager.clickEnter();
                    break;
                case 2:
                    Manager.viewCurrentLocation();
                    Manager.clickEnter();
                    break;
                case 3:
                    Manager.viewInventory();
                    Manager.clickEnter();
                    break;
                case 6 :
                    Manager.generateSim();
                    Manager.clickEnter();
                    break;
                case 7 :
                    Manager.changeSim();
                    Manager.clickEnter();
                    break;
                case 8 :
                    Manager.listObject();
                    Manager.clickEnter();
                default:
                    break;
            }
        }

        // while(Manager.getGameStarted() == false)
        // {
        //     Manager.gameMenu();
        // }
        // // generate the first sim. Otherwise, the game won't start
        // Manager.generateSim();
        // World world = new World();
        // world.addHome();
        // Manager.showWorld(world);
        
        // while(Manager.canGameContinue())
        // {
        //     Manager.listCanDo();
        // }
    }
}