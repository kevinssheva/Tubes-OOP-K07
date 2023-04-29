package src.Manager;

import java.util.*;
import src.Sim.*;
import src.Main;
import src.Objek.Furniture.*;
import src.Objek.Furniture.Bed.Bed;
import src.Objek.Furniture.Bed.*;
import src.Objek.Furniture.Stove.*;
import src.Objek.Ingredients.Ingredients;
import src.Room.Room;
import src.Job.*;
import src.World.*;

public class Manager {
    private static Sim currentSim;
    private static List<Sim> simList = new ArrayList<Sim>();
    private static boolean gameStarted = false;

    public static void addSim(Sim sim) {
        simList.add(sim);
    }

    public static void changeSim(Sim sim) {
        currentSim = sim;
    }

    public static void buyThings(String thing) {
        List<Object> putArray = new ArrayList<Object>();
        Object putIntoInventory = new Object();
        switch (thing) {
            case "Single Bed":
                SingleBed singleBed = new SingleBed();
                putIntoInventory = singleBed;
                break;
            case "Queen Size Bed":
                QueenBed queenBed = new QueenBed();
                putIntoInventory = queenBed;
                break;
            case "King Size Bed":
                Bed kingBed = new KingBed();
                putIntoInventory = kingBed;
                break;
            case "Toilet":
                Toilet toilet = new Toilet();
                putIntoInventory = toilet;
                break;
            case "Electric Stove":
                ElectricStove eStove = new ElectricStove();
                putIntoInventory = eStove;
                break;
            case "Gas Stove":
                GasStove gStove = new GasStove();
                putIntoInventory = gStove;
                break;
            case "Table and Chair":
                // hasnt implemented yet
            case "Clock":
                Clock clock = new Clock();
                putIntoInventory = clock;
                break;
            case "Rice":
                Ingredients rice = new Ingredients("Rice", 5, 5);
                putIntoInventory = rice;
                break;
            case "Potato":
                Ingredients potato = new Ingredients("Potato", 4, 5);
                putIntoInventory = potato;
                break;
            case "Chicken":
                Ingredients chicken = new Ingredients("Chicken", 8, 10);
                putIntoInventory = chicken;
                break;
            case "Beef":
                Ingredients beef = new Ingredients("Beef", 15, 12);
                putIntoInventory = beef;
                break;
            case "Carrot":
                Ingredients carrot = new Ingredients("Caroot", 2, 3);
                putIntoInventory = carrot;
                break;
            case "Spinach":
                Ingredients spinach = new Ingredients("Spinach", 2, 3);
                putIntoInventory = spinach;
                break;
            case "Peanut":
                Ingredients peanut = new Ingredients("Peanut", 2, 3);
                putIntoInventory = peanut;
                break;
            case "Milk":
                Ingredients milk = new Ingredients("Milk", 2, 2);
                putIntoInventory = milk;
                break;
        }

        long upperbound = Main.timeNow + 10; // don't forget to change this
        Sim receiver = currentSim; // in case the currentsim changed;
        putArray.add(putIntoInventory);
        Thread temp = new Thread() {
            public void run() {
                while (Main.timeNow < upperbound) {

                }
                receiver.addToInventory(putArray.get(0));
            }
        };
        temp.start();
    }

    public static boolean isThereAnyWorkingSim() {
        boolean isThereAnyWorkingSim = false;
        for (Sim sim : simList) {
            if (!sim.getStatus().equals("Idle")) {
                isThereAnyWorkingSim = true;
                break;
            }
        }
        return isThereAnyWorkingSim;
    }

    public static boolean canGameContinue() {
        int count = 0;
        for (Sim sim : simList) {
            if (sim.stillAlive()) {
                count = count + 1;
            }
        }

        return count > 0;
    }

    public static void moveRoom(Room r) {
        currentSim.setRoom(r);
    }

    public static void testing()
    {
        System.out.println("Testing from the manager's static method");
    }

    public static void help()
    {
        System.out.println("Here is the list of help");
        System.out.println("Click enter to proceed");
        clickEnter();
    }

    public static void gameMenu()
    {
        System.out.print("\033[H\033[2J");  // this is for clearscreen
        System.out.flush();  
        Scanner in = new Scanner(System.in);
        System.out.println("Here is the menu");
        System.out.println("1. Start Game");
        System.out.println("2. Help");
        System.out.println("3. Exit");
        int cmd = in.nextInt();
        System.out.print("\033[H\033[2J");  // this is for clearscreen
        System.out.flush();
        switch(cmd){
            case 1:
                gameStarted = true;
                break;
            case 2:
                help();
                break;
            case 3:
                System.exit(0);
                break;
        }
    }

    public static boolean getGameStarted()
    {
        System.out.println("Hello, welcome to your very first game !\nBefore we start the game, we have to figure out your sim's characteritic first\nPlease your sim's attribute first");
        return gameStarted;
    }

    public static void generateSim()
    {
        System.out.print("\033[H\033[2J");  // this is for clearscreen
        System.out.flush();  
        Scanner in = new Scanner(System.in);
        String name;
        Job job = null;
        System.out.println("What name would you like to give for your sim?");
        name = in.nextLine();
        System.out.println("Here's the list of job that we have");
        System.out.println("1.Clown \n2.Chef\n3.Police\n4.Programmer\n5.Doctor\nChoose your sim's job");
        int chooseJob = in.nextInt();
        while(chooseJob < 0 || chooseJob > 5)
        {
            System.out.println("Your input is incorrect. Please choose other number");
            chooseJob = in.nextInt();
        }
        switch(chooseJob){
            case 1:
                job = new Job("Badut Sulap");
                break;
            case 2:
                job = new Job("Koki");
                break;
            case 3:
                job = new Job("Polisi");
                break;
            case 4:
                job = new Job("Programmer");
                break;
            case 5:
                job = new Job("Dokter");
                break;
        }
        Sim sim = new Sim(name,job,80,80,80,100,"Idle");
        System.out.println("Your sim has been generated! ");
        clickEnter();
        simList.add(sim);
    }

    public static void clickEnter()
    {
        try{
            System.in.read();
        }catch(Exception E)
        {

        }
        System.out.print("\033[H\033[2J");  // this is for clearscreen
        System.out.flush();  
    }

    public static void showWorld(World world)
    {
        for(int j = 0;j < 64;j++)
        {
            for(int i = 0;i < 64;i++)
            {
                if(Home[j][i] == null)
                {
                    System.out.print(" ");
                }else
                {
                    System.out.print("H");
                }
            }
            System.out.println("");
        }
    }

}
