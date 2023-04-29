package src.Manager;

import java.awt.Point;
import java.util.*;
import src.Sim.*;
import src.Main;
import src.Home.Home;
import src.Objek.Objek;
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
    private static World world = new World();

    public static void buyThings(String thing) {
        List<Objek> putArray = new ArrayList<Objek>();
        Objek putIntoInventory = null;
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

    public static void testing() {
        System.out.println("Testing from the manager's static method");
    }

    public static void help() {
        clearScreen();
        System.out.println("Here is the list of help");
        System.out.println("Click enter to proceed");
        clickEnter();
    }

    public static void gameMenu() {
        System.out.print("\033[H\033[2J"); // this is for clearscreen
        System.out.flush();
        Scanner in = new Scanner(System.in);
        System.out.println("Here is the menu");
        System.out.println("1. Start Game");
        System.out.println("2. Help");
        System.out.println("3. Exit");
        int cmd = in.nextInt();
        System.out.print("\033[H\033[2J"); // this is for clearscreen
        System.out.flush();
        switch (cmd) {
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

    public static boolean getGameStarted() {
        System.out.println(
                "Hello, welcome to your very first game !\nBefore we start the game, we have to figure out your sim's characteritic first\nPlease your sim's attribute first");
        return gameStarted;
    }

    public static void clickEnter() {
        try {
            System.in.read();
        } catch (Exception E) {

        }
        System.out.print("\033[H\033[2J"); // this is for clearscreen
        System.out.flush();
    }

    public static void showWorld(World world) {
        // call the method from world
        world.showWorld();
        System.out.println("Click enter to proceed");
        clickEnter();
    }

    public static void listCanDo() {
        System.out.print("\033[H\033[2J"); // this is for clearscreen
        System.out.flush();
        System.out.println("Here is the list of thing that you can do");
        System.out.println("- View Sim Info");
        System.out.println("- View Current Location");
        System.out.println("- View Inventory");
        System.out.println("- Upgrade House");
        System.out.println("- Edit Room");
        System.out.println("- Add Sim");
        System.out.println("- Change Sim");
        System.out.println("- List Object");
        System.out.println("- Go To Object");
        // don't forget to add action that sim can only do with interaction with object

    }

    // MULAI SINI

    public static World getWorld() {
        return world;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J"); // this is for clearscreen
        System.out.flush();
    }

    public static void timer(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            System.out.println("Error");
        }
    }

    // METHOD METHOD COMMAND
    public static void viewSimInfo() {
        clearScreen();
        // View Sim Info for Current Sim
        System.out.println("Here is the information of your sim");
        System.out.println("Name : " + currentSim.getName());
        System.out.println("Job : " + currentSim.getJob().getName());
        System.out.println("Kesehatan : " + currentSim.getHealth());
        System.out.println("Kekenyangan : " + currentSim.getSatiety());
        System.out.println("Mood : " + currentSim.getMood());
        System.out.println("Uang : " + currentSim.getMoney());
    }

    public static void viewCurrentLocation() {
        clearScreen();
        // View Current Location
        System.out.println("Your current location is " + currentSim.getRoom().getName());
        System.out.println("House : " + currentSim.getHome().getName());
        currentSim.getRoom().printRoom();
    }

    public static void viewInventory() {
        clearScreen();
        // View Inventory
        System.out.println("Here is your inventory");
        currentSim.getInventory().showInventory();
    }

    public static void editRoom() {
        clearScreen();
        // Edit Room
        System.out.println("Here is your furniture");
        currentSim.getInventory().showFurnitureOnly();
        System.out.println("Which furniture do you want to put in your room?");

        Scanner in = new Scanner(System.in);

        Furniture furniture = null;
        String furnitureName = "";
        int x = 0, y = 0;

        try {
            System.out.print("Type the name of the furniture : ");
            furnitureName = in.nextLine();

            Objek item = currentSim.getInventory().getItemByName(furnitureName);
            furniture = (Furniture) item;

            System.out.println("Where do you want to put the " + furniture.getName() + " ?");
            System.out.print("X : ");
            x = in.nextInt();
            System.out.print("Y : ");
            y = in.nextInt();
        } catch (NullPointerException e) {
            System.out.println("The furniture with the name '" + furnitureName + "' does not exist in the inventory.");
            return;
        } catch (ClassCastException e) {
            System.out.println("The item with the name '" + furnitureName + "' is not a furniture.");
            return;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for coordinates. Please enter integer values for X and Y.");
            return;
        }
        try {
            currentSim.getRoom().addFurniture(furniture, new Point(x, y));
        } catch (IllegalArgumentException e) {
            System.out.println("The coordinates are out of bounds.");
            return;
        }
        currentSim.getInventory().removeItem(furniture);
    }

    public static void generateSim() {
        clearScreen();
        Scanner in = new Scanner(System.in);
        String name;
        Job job = null;
        System.out.println("What name would you like to give for your sim?");
        name = in.nextLine();
        System.out.println("Here's the list of job that we have. But, your sim's job will be choosen randomly");
        System.out.println("1.Clown \n2.Chef\n3.Police\n4.Programmer\n5.Doctor");
        Random rand = new Random();
        int chooseJob = rand.nextInt(5) + 1;
        switch (chooseJob) {
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
        Home home = new Home(name + "'s Home");
        world.addHome(home);
        Sim sim = new Sim(name, job, 80, 80, 80, 100, "Idle", home);
        System.out.println("Your sim's job is " + sim.getJob().getName());
        System.out.println("Your sim has been generated! ");
        System.out.println("Click enter to proceed");
        clickEnter();
        simList.add(sim);
        currentSim = sim;
    }

    public static void changeSim() {
        clearScreen();
        System.out.println("Here is the list of sim that you have");
        for (int i = 0; i < simList.size(); i++) {
            System.out.println(i + 1 + ". " + simList.get(i).getName());
        }
        System.out.println("Which sim do you want to choose?");
        Scanner in = new Scanner(System.in);
        int chooseSim = in.nextInt();
        currentSim = simList.get(chooseSim - 1);
        System.out.println("You have changed your sim to " + currentSim.getName());
    }

    public static void listObject() {
        clearScreen();
        currentSim.getRoom().printRoom();
        currentSim.getRoom().showFurniture();
    }
}
