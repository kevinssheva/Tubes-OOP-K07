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

public class Manager {
    private static Sim currentSim;
    private static List<Sim> simList = new ArrayList<Sim>();

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

}
