package src.Manager;

import src.Sim.*;
import java.util.*;
import src.Objek.*;
import src.Main;
import src.Objek.Furniture.*;
import src.Objek.Ingredients.Ingredients;
import src.*;

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
                Bed singleBed = new Bed("Single Bed", "Sleep", 50);
                putIntoInventory = singleBed;
                break;
            case "Queen Size Bed":
                Bed queenSizeBed = new Bed("Queen Size Bed", "Sleep", 100);
                putIntoInventory = queenSizeBed;
                break;
            case "King Size Bed":
                Bed kingSize = new Bed("King Size", "Sleep", 150);
                putIntoInventory = kingSize;
                break;
            case "Toilet":
                Toilet toilet = new Toilet();
                putIntoInventory = toilet;
                break;
            case "Electric Stove":
                Stove eStove = new Stove("Electic Stove", 200);
                putIntoInventory = eStove;
                break;
            case "Gas Stove":
                Stove gStove = new Stove("Gas Stove", 100);
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
        int count = 0;
        for (Sim sim : simList) {
            if (sim.getClass().equals("Idle"))
                ;
            else {
                count = count + 1;
            }
        }
        return count > 0;
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

}
