package simplicity.Manager;

import java.awt.Point;
import java.util.*;

import simplicity.Sim.*;
import simplicity.App;
import simplicity.Home.Home;
import simplicity.Objek.Objek;
import simplicity.Objek.Furniture.*;
import simplicity.Objek.Furniture.Bed.Bed;
import simplicity.Objek.Furniture.Bed.*;
import simplicity.Objek.Furniture.Stove.*;
import simplicity.Objek.Ingredients.Ingredients;
import simplicity.Room.Room;
import simplicity.Save.Load;
import simplicity.Save.Save;
import simplicity.Job.*;
import simplicity.World.*;
import simplicity.Objek.*;

public class Manager {
    private static Sim currentSim = null;
    private static List<Sim> simList = new ArrayList<Sim>();
    private static boolean gameStarted = false;
    private static World world = World.getInstance();
    private static String[] arrayBuyable = { "Single Bed", "Queen Size Bed", "King Size Bed", "Toilet",
            "Electric Stove", "Gas Stove", "Table and Chair", "Clock", "TV", "Komputer", "Bookshelf", "Sajadah",
            "Shower",
            "Telescope", "Piano", "Rice", "Potato", "Chicken", "Beef", "Carrot",
            "Spinach", "Peanut", "Milk" };
    private static ArrayList<String> buyableList = new ArrayList<>(Arrays.asList(arrayBuyable));
    private static boolean gameLoaded = false;
    private static boolean isAddSimAvailable = true;

    public static void setIsAddSimAvailable(boolean isAddSimAvailable) {
        Manager.isAddSimAvailable = isAddSimAvailable;
    }

    public static boolean getIsAddSimAvailable() {
        return isAddSimAvailable;
    }

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
                MejaKursi mejaKursi = new MejaKursi();
                putIntoInventory = mejaKursi;
                break;
            case "Clock":
                Clock clock = new Clock();
                putIntoInventory = clock;
                break;
            case "TV":
                TV tv = new TV();
                putIntoInventory = tv;
                break;
            case "Komputer":
                Komputer komputer = new Komputer();
                putIntoInventory = komputer;
                break;
            case "Bookshelf":
                putIntoInventory = new Bookshelf();
                break;
            case "Sajadah":
                Sajadah sajadah = new Sajadah();
                putIntoInventory = sajadah;
                break;
            case "Shower":
                Shower shower = new Shower();
                putIntoInventory = shower;
                break;
            case "Telescope":
                Telescope telescope = new Telescope();
                putIntoInventory = telescope;
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
            case "Piano":
                putIntoInventory = new Piano();
                break;
        }
        if (((Buyable) putIntoInventory).getPrice() > currentSim.getMoney()) {
            System.out.println("Your money is not sufficient");
            clickEnter();
            return;
        }
        putArray.add(putIntoInventory);
        currentSim.setMoney(currentSim.getMoney() - ((Buyable) putIntoInventory).getPrice());
        Thread putInventoryThread = new Thread() {
            public void run() {
                Random rand = new Random();
                long finalTime = App.timeNow + rand.nextInt(45) + 1;
                Manager.currentSim.addAction("Buy " + thing, finalTime);
                while (App.timeNow < finalTime) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Error");
                    }
                }
                currentSim.addToInventory(putArray.get(0));
                System.out.println(putArray.get(0).getName() + " are already arrived at your inventory");
            }
        };

        putInventoryThread.start();

    }


    public static void queryBuyThings() {
        System.out.println("Here is the list of things that you can buy\n");
        System.out.println("- Single Bed\nPrice : 50\nDimension : 4 x 1\n");
        System.out.println("- Queen Size Bed\nPrice : 100\nDimension : 4 x 2\n");
        System.out.println("- King Size Bed\nPrice : 150\nDimension : 5 x 2\n");
        System.out.println("- Toilet\nPrice : 50\nDimension : 1 x 1\n");
        System.out.println("- Gas Stove\nPrice : 100\nDimension : 2 x 1\n");
        System.out.println("- Electric Stove\nPrice : 200\nDimension : 1 x 1\n");
        System.out.println("- Table and Chair\nPrice : 50\nDimension : 3 x 3\n");
        System.out.println("- Clock\nPrice : 10\nDimension : 1 x 1\n");
        System.out.println("- TV\nPrice : 30\nDimension : 1x1\n");
        System.out.println("- Komputer\nPrice : 20\nDimension : 1 x 1\n");
        System.out.println("- Bookshelf\nPrice : 25\nDimension : 3 x 1\n");
        System.out.println("- Sajadah\nPrice : 30\nDimension : 2 x 1\n");
        System.out.println("- Shower\nPrice : 75\nDimension : 1 x 1\n");
        System.out.println("- Telescope\nPrice : 70\nDimension : 1 x 1\n");
        System.out.println("- Piano\nPrice : 50\nDimension : 2 x 1\n");
        System.out.println("- Rice\nPrice : 5\nSatiety : 5\n");
        System.out.println("- Potato\nPrice : 3\nSatiety : 4\n");
        System.out.println("- Chicken\nPrice : 10\nSatiety : 8\n");
        System.out.println("- Beef\nPrice : 12\nSatiety : 15\n");
        System.out.println("- Carrot\nPrice : 3\nSatiety : 2\n");
        System.out.println("- Spinach\nPrice : 3\nSatiety : 2\n");
        System.out.println("- Peanut\nPrice : 2\nSatiety : 2\n");
        System.out.println("- Milk\nPrice : 2\nSatiety : 1\n");
        Scanner in = new Scanner(System.in);
        System.out.println("Please type the item you want to buy or type Quit if you want to cancel this");
        String thingsName = in.nextLine();
        while (buyableList.contains(thingsName) == false && thingsName.equals("Quit") == false) {
            System.out.println(
                    "There is no such thing. please type it another thing or type Quit if you want to cancel this");
            thingsName = in.nextLine();
        }

        if (thingsName.equals("Quit")) {
            return;
        } else {
            buyThings(thingsName);
        }
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
        while (true) {
            clearScreen();
            System.out.println("Welcome to Sim-Plicity! A world where sims live.");
            System.out.println("Sims can work, cook, eat, sleep, buy things, upgrade their house and more!");
            System.out.println("Control your sims' actions and guide them through their everyday lives.");
            System.out.println("And don't forget to make sure your sims are happy and healthy!\n");

            System.out.println("Here is the list of all ingame commands:");
            System.out.println("- Help                  - Buy Things");
            System.out.println("- Work                  - View Sim Info");
            System.out.println("- Exercise              - View Current Location");
            System.out.println("- Sleep                 - View Inventory");
            System.out.println("- Eat                   - Upgrade House");
            System.out.println("- Cook                  - Move Room");
            System.out.println("- View Clock            - Edit Room");
            System.out.println("- Watch TV              - Add Sim");
            System.out.println("- Play Game             - Change Sim");
            System.out.println("- Read Book             - List Object");
            System.out.println("- Sholat                - Go To Object");
            System.out.println("- Play Piano            - Visit Other's Houses");
            System.out.println("- Take a Shower         - Return Home");
            System.out.println("- Use the Toilet        - Exit");
            System.out.println("- Use the Telescope");
            System.out.println("\nType in the command for details or type 'Back' to go back");

            Scanner in = new Scanner(System.in);
            String query = in.nextLine();
            switch (query) {
                case "Help":
                    clearScreen();
                    System.out.println(
                            "Shows up this help menu.\nThis menu can be accessed in the main menu as well as ingame.");
                    clickEnter();
                    break;
                case "Work":
                    clearScreen();
                    System.out.println("Puts your sim to work for the amount of time given");
                    System.out.println(
                            "Effects:\n-10 satiety\n-10 mood\nfor every 30 seconds\n+X money (depending on sim's job) for every daily work done\n");
                    System.out
                            .println("Sims can choose to work for 2 minutes(120s) or 4 minutes(240s) in one session.");
                    System.out.println("A sim's daily work is completed after 4 minutes of working in total for each day.");
                    System.out.println("A sim can only work for up to 4 minutes(240s) a day.");
                    System.out.println(
                            "\nThis action is an active action and requires full participation from the sim.\nThe sim will not be able to do other active actions.");
                    clickEnter();
                    break;
                case "Exercise":
                    clearScreen();
                    System.out.println("Make your sim do exercise for the amount of time given");
                    System.out.println(
                            "Effects:\n+5 health\n-5 satiety\n+10 mood\nfor every 20 seconds\n");
                    System.out.println("Sims can choose to work for any time length with a multiple of 20 seconds.");
                    System.out.println(
                            "\nThis action is an active action and requires full participation from the sim.\nThe sim will not be able to do other active actions.");
                    clickEnter();
                    break;
                case "Sleep":
                    clearScreen();
                    System.out.println("Puts your sim to sleep for the amount of time given");
                    System.out.println("Effects:\n+30 mood\n+20 health\nfor every 4 minutes\n");
                    System.out.println("Sims can choose to sleep for any time length with a multiple of 20 seconds.");
                    System.out.println(
                            "Not sleeping for too long will decrease mood and health by -5 for every 10 minutes without sleep.");
                    System.out.println("\nSleeping will require your sim to be near a Bed");
                    System.out.println(
                            "This action is an active action and requires full participation from the sim.\nThe sim will not be able to do other active actions.");
                    clickEnter();
                    break;
                case "Eat":
                    clearScreen();
                    System.out.println("Lets your sim eat a food item from the inventory.");
                    System.out.println(
                            "Effects:\n+X satiety (depending on the food item)\n");
                    System.out.println(
                            "\nEating will require your sim to be near a Table and Chair\nand takes 30 seconds to finish");
                    System.out.println(
                            "This action is an active action and requires full participation from the sim.\nThe sim will not be able to do other active actions.");
                    clickEnter();
                    break;
                case "Cook":
                    clearScreen();
                    System.out.println("Lets your sim cook a dish using ingredients from the inventory.");
                    System.out.println("Effects:\n+10 mood\n");
                    System.out.println(
                            "\nCooking will require your sim to be near a Stove\nand takes (1.5 x dish satiety level) seconds to finish");
                    System.out.println(
                            "This action is an active action and requires full participation from the sim.\nThe sim will not be able to do other active actions.");
                    clickEnter();
                    break;
                case "View Clock":
                    clearScreen();
                    System.out.println("Shows the current ingame time as well as currently ongoing actions.");
                    clickEnter();
                    break;
                case "Watch TV":
                    clearScreen();
                    System.out.println("Lets your sim watch TV for the amount of time given");
                    System.out.println("Effects:\n+20 mood\nfor every 30 seconds\n");
                    System.out.println("Watching TV will require your sim to be near a TV");
                    System.out.println(
                            "This action is an active action and requires full participation from the sim.\nThe sim will not be able to do other active actions.");
                    clickEnter();
                    break;
                case "Play Game":
                    clearScreen();
                    System.out.println("Lets your sim play a game for the amount of time given");
                    System.out.println("Effects:\n+20 mood\n-5 satiety\nfor every 30 seconds");
                    System.out.println("Playing game will require your sim to be near a Komputer");
                    System.out.println(
                            "This action is an active action and requires full participation from the sim.\nThe sim will not be able to do other active actions.");
                    clickEnter();
                    break;
                case "Read Book":
                    clearScreen();
                    System.out.println("Lets your sim read books for the amount of time given");
                    System.out.println("Effects:\n+10 mood\n-5 satiety\nfor every 30 seconds\n");
                    System.out.println(
                            "Your sim must be near a Bookshelf to read books");
                    System.out.println(
                            "This action is an active action and requires full participation from the sim.\nThe sim will not be able to do other active actions.");
                    clickEnter();
                    break;
                case "Sholat":
                    clearScreen();
                    System.out.println("Lets your sim pray to Allah");
                    System.out.println("Effects:\n+10 mood\n");
                    System.out.println(
                            "\nSholat will require your sim to be near a Sajadah\nand takes 10 seconds to finish");
                    System.out.println(
                            "This action is an active action and requires full participation from the sim.\nThe sim will not be able to do other active actions.");
                    clickEnter();
                    break;
                case "Play Piano":
                    clearScreen();
                    System.out.println("Lets your sim play the piano. ");
                    System.out.println("Effects:\n+10 mood\n-5 satiety\nfor every 30 seconds\n");
                    System.out.println(
                            "Your sim must be near a Piano to play it");
                    System.out.println(
                            "This action is an active action and requires full participation from the sim.\nThe sim will not be able to do other active actions.");
                    clickEnter();
                    break;
                case "Take a Shower":
                    clearScreen();
                    System.out.println("Lets your sim take a shower");
                    System.out.println("Effects:\n+5 mood\n+5 health\n");
                    System.out.println(
                            "\nTaking a shower will require your sim to be near a Shower\nand takes 10 seconds to finish");
                    System.out.println(
                            "This action is an active action and requires full participation from the sim.\nThe sim will not be able to do other active actions.");
                    clickEnter();
                    break;
                case "Use the Toilet":
                    clearScreen();
                    System.out.println("Lets your sim use the toilet");
                    System.out.println("Effects:\n-20 satiety\n+10 mood\n");
                    System.out.println(
                            "Sims need to use the toilet once for each time they have eaten food.");
                    System.out.println(
                            "Not using the toilet for 4 minutes after eating will decrease their mood and health by -5.");
                    System.out.println(
                            "\nUsing the toilet will require your sim to be near a Toilet\nand takes 10 seconds to finish");
                    System.out.println(
                            "This action is an active action and requires full participation from the sim.\nThe sim will not be able to do other active actions.");
                    clickEnter();
                    break;
                case "Use the Telescope":
                    clearScreen();
                    System.out.println("Lets your sim use the telescope and stargaze at the stars");
                    System.out.println("Effects:\n-30 health \n+70 mood (only if done at night)");
                    System.out.println(
                            "Using the telescope will require your sim to be near a Telescope\nand takes 30 seconds to finish");
                    System.out.println(
                            "This action is an active action and requires full participation from the sim.\nThe sim will not be able to do other active actions.");
                    clickEnter();
                    break;
                case "Buy Things":
                    clearScreen();
                    System.out.println("Lets your sim buy things from the shop.");
                    System.out.println("Sims can buy things such as food ingredients and furniture.");
                    System.out.println("Items bought will arrive at a random time of (1..5)*30s");
                    System.out.println("The items will automatically be stored in the sims' inventory.");
                    clickEnter();
                    break;
                case "View Sim Info":
                    clearScreen();
                    System.out.println("Lets you see the current stats of the sim.");
                    System.out.println("Stats include name, job, health, satiety, mood, and money.");
                    clickEnter();
                    break;
                case "Change Sim Job":
                    clearScreen();
                    System.out.println("Lets you change the current job of the sim.");
                    System.out.println(
                            "Changing jobs will require the sim to have already worked in their current job for a minimum of 12 minutes");
                    System.out.println(
                            "as well as pay money as much as the new job's daily salary divided by 2 rounded down.");
                    clickEnter();
                    break;
                case "View Current Location":
                    clearScreen();
                    System.out.println("Lets you see the current location of the sim,");
                    System.out.println("including the house and the room that the sim is currently in.");
                    clickEnter();
                    break;
                case "View Inventory":
                    clearScreen();
                    System.out.println("Lets you see the what the sim has in their inventory");
                    clickEnter();
                    break;
                case "Upgrade House":
                    clearScreen();
                    System.out.println("Lets your sim upgrade their house by adding a new room.");
                    clickEnter();
                    break;
                case "Edit Room":
                    clearScreen();
                    System.out.println("Lets you place a furniture in the room from the sim's inventory.");
                    clickEnter();
                    break;
                case "Add Sim":
                    clearScreen();
                    System.out.println("Lets you create another sim in the game world.");
                    System.out.println("You can choose the name and job of the new sim.");
                    System.out.println("The new sim will have a new house to live in the world.");
                    clickEnter();
                    break;
                case "Change Sim":
                    clearScreen();
                    System.out.println("Lets you choose another sim in the game world to control.");
                    clickEnter();
                    break;
                case "List Object":
                    clearScreen();
                    System.out.println("Shows a list of all objects in the current room.");
                    clickEnter();
                    break;
                case "Go To Object":
                    clearScreen();
                    System.out.println("Lets your sim move to an object in the current room");
                    clickEnter();
                    break;
                case "Visit Other's Houses":
                    clearScreen();
                    System.out.println("Lets your sim visit another sim's house.");
                    System.out.println(
                            "Effects:\n+10 mood\n-10 satiety\nfor every 30 seconds spent outside a house\n");
                    System.out.println(
                            "Visiting requires the sim to walk to the other house for (house distance) amount of seconds.");
                    System.out.println(
                            "Time spent outside refers to the time it takes for the sim to walk to another house.");
                    System.out.println(
                            "Walking is an active action and requires full participation from the sim.\nThe sim will not be able to do other active actions.");
                    System.out.println(
                            "After arriving, the sim becomes idle and can act normally except for upgrade house and edit room.");
                    clickEnter();
                    break;
                case "Return Home":
                    clearScreen();
                    System.out.println("Lets your sim visit go back to their own house.");
                    System.out.println(
                            "Effects:\n+10 mood\n-10 satiety\nfor every 30 seconds spent outside a house\n");
                    System.out.println(
                            "Returning requires the sim to walk to the their house for (house distance) amount of seconds.");
                    System.out.println(
                            "Walking is an active action and requires full participation from the sim.\nThe sim will not be able to do other active actions.");
                    System.out.println(
                            "After arriving, the sim becomes idle and can act normally.");
                    clickEnter();
                case "Exit":
                    clearScreen();
                    System.out.println("Exits the game.");
                    clickEnter();
                    break;
                case "Back":
                    return;
            }
        }
    }

    public static void gameMenu() {
        System.out.print("\033[H\033[2J"); // this is for clearscreen
        System.out.flush();
        Scanner in = new Scanner(System.in);
        System.out.println("-------- SIM-PLICITY --------");
        System.out.println("\nMenu:");
        System.out.println("1. Start Game");
        System.out.println("2. Load Game");
        System.out.println("3. Help");
        System.out.println("4. Exit");
        System.out.print("input (num): ");
        int cmd = in.nextInt();
        System.out.print("\033[H\033[2J"); // this is for clearscreen
        System.out.flush();
        switch (cmd) {
            case 1:
                gameStarted = true;
                break;
            case 2:
                loadGame();
                Manager.clickEnter();
                break;
            case 3:
                help();
                break;
            case 4:
                exitTheGame();
                break;
        }
    }

    public static void loadGame() {
        Load.load("data/data.json");
    }

    public static void exitTheGame() {
        System.out.println("Thank you for playing this game!");
        System.exit(0);
    }

    public static boolean getGameStarted() {
        return gameStarted;
    }

    public static void clickEnter() {
        System.out.println("Click enter to proceed");
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
        clickEnter();
    }

    public static void listCanDo() {
        System.out.print("\033[H\033[2J"); // this is for clearscreen
        System.out.flush();
        System.out.println(" ___  ____  __  __     ____  __    ____  ___  ____  ____  _  _ ");
        System.out.println("/ __)(_  _)(  \\/  )___(  _ \\(  )  (_  _)/ __)(_  _)(_  _)( \\/ )");
        System.out.println("\\__ \\ _)(_  )    ((___))___/ )(__  _)(_( (__  _)(_   )(   \\  / ");
        System.out.println("(___/(____)(_/\\/\\_)   (__)  (____)(____)\\___)(____) (__)  (__) ");
        System.out.println("Here is the list of thing that you can do");
        System.out.println("- Help");
        System.out.println("- Work");
        System.out.println("- Exercise");
        // sleep
        if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), Bed.class)) {
            System.out.println("- Sleep");
        }
        // eat
        if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), MejaKursi.class)) {
            System.out.println("- Eat");
        }
        // cook
        if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), Stove.class)) {
            System.out.println("- Cook");
        }
        if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), Clock.class)) {
            System.out.println("- View Clock");
        }
        if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), TV.class)) {
            System.out.println("- Watch TV");
        }
        if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), Komputer.class)) {
            System.out.println("- Play Game");
        }
        if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), Bookshelf.class)) {
            System.out.println("- Read Book");
        }
        if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), Sajadah.class)) {
            System.out.println("- Sholat");
        }
        if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), Piano.class)) {
            System.out.println("- Play Piano");
        }
        if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), Shower.class)) {
            System.out.println("- Take a Shower");
        }
        if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), Toilet.class)) {
            System.out.println("- Use the Toilet");
        }
        if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), Telescope.class)) {
            System.out.println("- Use the telescope");
        }
        System.out.println("- Buy Things");
        System.out.println("- View Sim Info");
        // change job
        if (currentSim.getCurrentWorkTotal() >= 12) {
            System.out.println("- Change Sim Job");
        }
        System.out.println("- View Current Location");
        System.out.println("- View Inventory");

        if (!currentSim.checkAction("Upgrade House") && currentSim.getCurrentHome() == currentSim.getHome()) {
            System.out.println("- Upgrade House");
        }
        System.out.println("- Move Room");
        if (currentSim.getCurrentHome().getName().equals(currentSim.getHome().getName())) {
            System.out.println("- Edit Room");
        }
        System.out.println("- Add Sim");
        System.out.println("- Change Sim");
        System.out.println("- List Object");
        System.out.println("- Go To Object");
        System.out.println("- Visit Other's Houses");
        if (currentSim.getCurrentHome() != currentSim.getHome()) {
            System.out.println("- Return Home");
        }
        System.out.println("- Save Game");
        System.out.println("- Exit");
        // don't forget to add action that sim can only do with interaction with object

    }

    // MULAI SINI

    public static World getWorld() {
        return world;
    }

    public static Sim getCurrentSim() {
        return currentSim;
    }

    public static List<Sim> getSimList() {
        return simList;
    }

    public static boolean getGameLoaded() {
        return gameLoaded;
    }

    public static void setWorld(World world) {
        Manager.world = world;
    }

    public static void setCurrentSim(Sim currentSim) {
        Manager.currentSim = currentSim;
    }

    public static void setSimList(List<Sim> simList) {
        Manager.simList = simList;
    }

    public static void setGameStarted(boolean gameStarted) {
        Manager.gameStarted = gameStarted;
    }

    public static void setGameLoaded(boolean gameLoaded) {
        Manager.gameLoaded = gameLoaded;
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
        System.out.println("Health : " + currentSim.getHealth());
        System.out.println("Satiety : " + currentSim.getSatiety());
        System.out.println("Mood : " + currentSim.getMood());
        System.out.println("Money : " + currentSim.getMoney());
        clickEnter();
    }

    public static void changeSimJob() {
        clearScreen();
        if (currentSim.getCurrentWorkTotal() < 12) {
            System.out.println(
                    "You can't change your job just yet. You still have some work to do for the next 12 minutes. Keep hustling!");
            System.out.println("Current work time : " + currentSim.getCurrentWorkTotal() + " minutes");
            clickEnter();
            return;
        } else {
            System.out.println("Here's the list of job that we have along with their daily salary");
            System.out.println("1.Clown : 15\n2.Chef : 30\n3.Police : 35\n4.Programmer : 45\n5.Doctor : 50");
            System.out.println(
                    "Make sure your wallet's ready! Changing job will require you to pay half of the new job's salary(rounded down)");
            System.out.println("Please input the number of the job you want to choose, or -1 to cancel the job change");
            Scanner in = new Scanner(System.in);
            int chooseJob = in.nextInt();
            while ((chooseJob > 5 || chooseJob < 1) && chooseJob != -1) {
                System.out.println("Please input numbers that are on the list or -1 to cancel");
                chooseJob = in.nextInt();
            }
            if (chooseJob == -1) {
                return;
            }
            Job newJob;
            switch (chooseJob) {
                case 1:
                    newJob = new Job("Badut Sulap");
                    break;
                case 2:
                    newJob = new Job("Koki");
                    break;
                case 3:
                    newJob = new Job("Polisi");
                    break;
                case 4:
                    newJob = new Job("Programmer");
                    break;
                default:
                    newJob = new Job("Dokter");
                    break;
            }
            if (currentSim.getMoney() > newJob.getDailyPay() / 2) {
                currentSim.setMoney(currentSim.getMoney() - (newJob.getDailyPay() / 2));
                currentSim.setJob(newJob);
                currentSim.setCurrentWorkTotal(0);
                System.out.println("Congratulations! You have successfully switched to " + newJob.getName());
            } else {
                System.out.println(
                        "Woah there! Looks like you're a bit short on cash. You can't switch to " + newJob.getName()
                                + " just yet. Why not explore some other job options for now?");
            }
        }

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
        clickEnter();

    }

    public static void upgradeHouse() {
        clearScreen();
        // Upgrade House
        if(currentSim.getMoney() < 1500){
            System.out.println("Your money is not sufficient enough to upgrade your house");
            clickEnter();
            return;
        }
        if (currentSim.checkAction("Upgrade House")) {
            System.out.println("You are still upgrading your house, please wait until it's done");
            clickEnter();
            return;
        }
        System.out.println("Here is your list of room in your house");
        currentSim.getHome().showRoom();
        System.out.println("What room do you want to use as a reference? (input the room name)");
        Scanner in = new Scanner(System.in);
        String room = in.nextLine();
        Room referenceRoom = currentSim.getHome().getRoomByName(room);
        while (referenceRoom == null || referenceRoom.getUnderConstruction()) {
            System.out.println("Room not available, please try again");
            room = in.nextLine();
            referenceRoom = currentSim.getHome().getRoomByName(room);
        }

        System.out.println("What room do you want to add? (input new room name)");
        String newRoom = in.nextLine();
        while (currentSim.getHome().checkRoom(newRoom)) {
            System.out.println("Room already exist, please try again");
            newRoom = in.nextLine();
        }

        System.out.println("What direction do you want to add the room? (north / south / east / west)");
        String direction = in.nextLine();
        currentSim.getHome().addRuangan(room, direction, newRoom);
        currentSim.getHome().showRoom();

        final String roomNameFinal = newRoom;

        Thread upgradeThread = new Thread() {
            public void run() {
                long finalTime = App.timeNow + 1080;
                Manager.currentSim.addAction("Upgrade House", finalTime);
                while (App.timeNow < finalTime) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Error");
                    }
                }
                currentSim.getHome().getRoomByName(roomNameFinal).setUnderConstruction(false);
                currentSim.removeAction("Upgrade House");
            }
        };
        upgradeThread.start();
    }

    public static void moveRoom() {
        clearScreen();
        // Move Room
        Scanner in = new Scanner(System.in);
        System.out.println("Where do you want to go? (North / South / East / West)");
        String direction = in.nextLine();
        Room temp = currentSim.getRoom();
        Home home = currentSim.getCurrentHome();
        try {
            switch (direction) {
                case "North":
                    if ((home.getRoomByName(temp.getNorth())).getUnderConstruction()) {
                        System.out.println("Room is under construction");
                        break;
                    }
                    currentSim.setRoom(home.getRoomByName(temp.getNorth()));
                    currentSim.getRoom().adjustSimMap(currentSim.getName(), new Point(5, 0));
                    temp.removeSimMap(currentSim.getName());
                    break;
                case "South":
                    if (home.getRoomByName(temp.getSouth()).getUnderConstruction()) {
                        System.out.println("Room is under construction");
                        break;
                    }
                    currentSim.setRoom(home.getRoomByName(temp.getSouth()));
                    currentSim.getRoom().adjustSimMap(currentSim.getName(), new Point(0, 0));
                    temp.removeSimMap(currentSim.getName());
                    break;
                case "East":
                    if (home.getRoomByName(temp.getEast()).getUnderConstruction()) {
                        System.out.println("Room is under construction");
                        break;
                    }
                    currentSim.setRoom(home.getRoomByName(temp.getEast()));
                    currentSim.getRoom().adjustSimMap(currentSim.getName(), new Point(0, 0));
                    temp.removeSimMap(currentSim.getName());
                    break;
                case "West":
                    if (home.getRoomByName(temp.getWest()).getUnderConstruction()) {
                        System.out.println("Room is under construction");
                        break;
                    }
                    currentSim.setRoom(home.getRoomByName(temp.getWest()));
                    currentSim.getRoom().adjustSimMap(currentSim.getName(), new Point(0, 5));
                    temp.removeSimMap(currentSim.getName());
                    break;
                default:
                    System.out.println("Invalid Direction");
                    break;
            }
            System.out.println("You are now in " + currentSim.getRoom().getName());
            currentSim.getRoom().printRoom();
        } catch (NullPointerException e) {
            System.out.println("There is no room in that direction");
            currentSim.setRoom(temp);
        }
        clickEnter();
    }

    public static void putFurniture() {
        clearScreen();
        Scanner in = new Scanner(System.in);
        System.out.println("Here is your furniture");
        currentSim.getInventory().showFurnitureOnly();
        System.out.println("Which furniture do you want to put in your room?");

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

    public static void removeFurniture() {
        clearScreen();
        Scanner in = new Scanner(System.in);
        System.out.println("Here is your furniture");
        currentSim.getRoom().showFurniture();
        System.out.println("Which furniture do you want to remove from your room?");
        String furnitureName = in.nextLine();
        currentSim.getRoom().removeFurniture(furnitureName);
    }

    public static void editRoom() {
        clearScreen();
        // Edit Room
        Scanner in = new Scanner(System.in);
        System.out.println("Do you want to put or remove furniture?");
        System.out.println("1. Put Furniture");
        System.out.println("2. Remove Furniture");
        int choice = in.nextInt();
        if (choice == 1) {
            putFurniture();
        } else if (choice == 2) {
            removeFurniture();
        } else {
            System.out.println("Invalid Input");
        }
        clickEnter();
    }

    public static boolean checkName(String name) {
        for (Sim sim : simList) {
            if (sim.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public static void generateSim() {
        clearScreen();
        if (!isAddSimAvailable) {
            System.out.println("You have reached the maximum number of sim today");
            clickEnter();
            return;
        }
        Scanner in = new Scanner(System.in);
        String name;
        Job job = null;
        System.out.println("What name would you like to give for your sim?");
        name = in.nextLine();
        while (!checkName(name)) {
            System.out.println("The name is already taken. Please choose another name");
            name = in.nextLine();
        }
        System.out.println("Here's the list of job that we have. But, your sim's job will be choosen randomly");
        System.out.println("1.Clown \n2.Chef\n3.Police\n4.Programmer\n5.Doctor");
        Random rand = new Random();
        int chooseJob = rand.nextInt(5) + 1;
        switch (chooseJob) {
            case 1:
                job = new Job("Clown");
                break;
            case 2:
                job = new Job("Chef");
                break;
            case 3:
                job = new Job("Police");
                break;
            case 4:
                job = new Job("Programmer");
                break;
            case 5:
                job = new Job("Doctor");
                break;
        }
        Home home = new Home(name + "'s Home");
        world.addHome(home);
        Sim sim = new Sim(name, job, 80, 100, 80, 80, "Idle", home);
        System.out.println("Your sim's job is " + sim.getJob().getName());
        System.out.println("Your sim has been generated! ");
        clickEnter();
        simList.add(sim);
        if (currentSim == null) {
            currentSim = sim;
        }
        isAddSimAvailable = false;
    }

    public static void changeSim() {
        clearScreen();
        System.out.println("Here is the list of sim that you have");
        for (int i = 0; i < simList.size(); i++) {
            System.out.println(i + 1 + ". " + simList.get(i).getName() + (!simList.get(i).stillAlive() ? " ( Deceased ) "  : " ") + (simList.get(i).equals(currentSim) ? " ( Current Sim )" : " "));
        }
        System.out.println("Which sim do you want to choose?");
        Scanner in = new Scanner(System.in);
        int chooseSim = in.nextInt();
        while(chooseSim < 0 || chooseSim > simList.size()){
            System.out.println("There is no sim at that index. Please choose the other one");
            chooseSim = in.nextInt();
        }
        currentSim = simList.get(chooseSim - 1);

        System.out.println("You have changed your sim to " + currentSim.getName());
        clickEnter();

    }

    public static void listObject() {
        clearScreen();
        currentSim.getRoom().printRoom();
        currentSim.getRoom().showFurniture();
    }

    public static void goToObject() {
        clearScreen();
        Scanner in = new Scanner(System.in);
        if(currentSim.getRoom().getFurnitureList().size() == 0){
            System.out.println("There is no object in the room, please add them first");
            return;
        }
        System.out.println("Here is the list of objects in the room");
        currentSim.getRoom().showFurniture();
        System.out.println("Which object do you want to go?");
        String objectName = in.nextLine();
        Point point = currentSim.getRoom().getFurnitureLocation(objectName);
        if (point == null) {
            System.out.println("The object with the name '" + objectName + "' does not exist in the room.");
            return;
        }
        currentSim.getRoom().adjustSimMap(currentSim.getName(), point);
        System.out.println("You are now in top of " + objectName);
    }

    public static void doQuery() {
        Scanner in = new Scanner(System.in);
        String query = in.nextLine();
        switch (query) {
            case "Help":
                help();
                break;
            case "Work":
                clearScreen();
                if (currentSim.getWorkToday() == 4) {
                    System.out.println("This sim has completed their daily work. They cannot work anymore today.");
                    clickEnter();
                    break;
                }
                System.out.println(
                        "Please input how many seconds do you want to work.\nMake sure the input is in multiples of 120.\nIf you don't want to work,please type -1");
                int time = in.nextInt();
                while (time % 120 != 0 && time != -1) {
                    System.out.println("Please input the multiples of 120 or -1 if you don't want to work");
                    time = in.nextInt();
                }

                if (time == -1) {
                    break;
                }

                int timeToWork = Math.min(time, 240 - currentSim.getWorkToday() * 60);
                currentSim.kerja(timeToWork);

                clickEnter();
                break;
            case "Exercise":
                clearScreen();
                System.out.println(
                        "Please input how many seconds do you want to exercise.\nMake sure the input is in multiples of 20.\nIf you don't want to work,please type -1");
                int timeExercise = in.nextInt();
                while (timeExercise % 20 != 0 && timeExercise != -1) {
                    System.out.println("Please input the multiples of 20 or -1 if you don't want to work");
                    timeExercise = in.nextInt();
                }
                if (timeExercise != -1) {
                    currentSim.exercise(timeExercise);
                }

                clickEnter();
                break;
            case "Sleep":
                clearScreen();
                if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), Bed.class)) {
                    System.out.println(
                            "Please input how many seconds do you want to sleep.\nMake sure the input is more than 180.\nIf you don't want to work,please type -1");
                    int timeSleep = in.nextInt();
                    while (timeSleep % 240 != 0 && timeSleep != -1) {
                        System.out.println("Please input the multiples of 240 or -1 if you don't want to work");
                        timeSleep = in.nextInt();
                    }
                    if (timeSleep != -1) {
                        currentSim.sleep(timeSleep);
                    }
                } else {
                    System.out.println(
                            "You know that you actually couldn't Sleep because your sim are not sitting on top of a Bed.\nPlease do not do this again!");
                }
                clickEnter();
                break;
            case "Eat":
                clearScreen();
                if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), MejaKursi.class)) {
                    currentSim.getInventory().showEdibleOnly();
                    chooseFood();

                } else {
                    System.out.println(
                            "you know that you actually couldn't eat because your sim are not sitting on top of a Table and Chair.\nPlease do not do this again!");
                }
                clickEnter();
                break;
            case "Cook":
                clearScreen();
                if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), Stove.class)) {
                    printListDish();
                    doQueryCook();
                } else {
                    System.out.println(
                            "you know that you actually couldn't cook because your sim are not near a stove.\nPlease do not do this again!");
                }
                clickEnter();
                break;
            case "View Clock": {
                clearScreen();
                if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), Clock.class)) {
                    currentSim.showTime();
                } else {
                    System.out.println(
                            "you know that you actually couldn't view the clock because your sim are not near a clock.\nPlease do not do this again!");
                }
                clickEnter();
                break;
            }
            case "Watch TV":
                clearScreen();
                if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), TV.class)) {
                    doQueryWatchTV();
                } else {
                    System.out.println(
                            "you know that you actually couldn't watch the TV because your sim are not near a TV.\nPlease do not do this again!");
                }
                clickEnter();
                break;

            case "Play Piano":
                clearScreen();
                if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), Piano.class)) {
                    doQueryPlayPiano();
                } else {
                    System.out.println(
                            "you can't play the piano because you're not near one.\nPlease do not do this again!");
                }
                clickEnter();
                break;

            case "Play Game":
                clearScreen();
                if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), Komputer.class)) {
                    doQueryPlayGame();
                } else {
                    System.out.println(
                            "You know that you actually couldn't play game because your sim is nor near a Komputer.\nPlease do not do this again!");
                }
                clickEnter();
                break;

            case "Read Book":
                clearScreen();
                if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), Bookshelf.class)) {
                    doQueryReadBook();
                } else {
                    System.out.println(
                            "you can't read books because you're not near a bookshelf.\nPlease do not do this again!");
                }
                clickEnter();
                break;

            case "Sholat":
                clearScreen();
                if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), Sajadah.class)) {
                    System.out.println("You will sholat for 10 seconds\nPlease take a moment to calm yourself");
                    currentSim.sholat();
                } else {
                    System.out.println(
                            "you know that you actually couldn't sholat because your sim are not near a sajadah.\nPlease do not do this again!");
                }
                clickEnter();
                break;
            case "Take a Shower":
                clearScreen();
                if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), Shower.class)) {
                    currentSim.mandi();
                } else {
                    System.out.println(
                            "you know that you actually couldn't take a Shower because your sim are not near a shower.\nPlease do not do this again!");
                }
                clickEnter();
                break;
            case "Use the Toilet":
                clearScreen();
                if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), Toilet.class)) {
                    currentSim.poop();
                } else {
                    System.out.println(
                            "you know that you actually couldn't use a Toilet because your sim are not near a toilet.\nPlease do not do this again!");
                }
                clickEnter();
                break;
            case "Use the Telescope":
                clearScreen();
                if (currentSim.getRoom().isSimOnFurniture(currentSim.getName(), Telescope.class)) {
                    currentSim.stargaze(isNight());
                } else {
                    System.out.println(
                            "you know that you actually couldn't use a Telescope because your sim are not near a Telescope.\nPlease do not do this again!");
                }
                clickEnter();
                break;
            case "Buy Things":
                clearScreen();
                queryBuyThings();
                break;
            case "View Sim Info":
                viewSimInfo();
                break;
            case "Change Sim Job":
                changeSimJob();
                clickEnter();
                break;
            case "View Current Location":
                world.showWorld();
                System.out.println("\n");
                currentSim.getRoom().printRoom();
                clickEnter();
                break;
            case "View Inventory":
                viewInventory();
                break;
            case "Upgrade House":
                upgradeHouse();
                clickEnter();
                break;
            case "Edit Room":
                if (!currentSim.getCurrentHome().getName().equals(currentSim.getHome().getName())) {
                    System.out.println("You can only edit your own house!");
                    break;
                }
                editRoom();
                break;
            case "Move Room":
                moveRoom();
                break;
            case "Add Sim":
                generateSim();
                break;
            case "Change Sim":
                changeSim();
                clickEnter();
                break;
            case "List Object":
                currentSim.getRoom().showFurniture();
                clickEnter();
                break;
            case "Go To Object":
                clearScreen();
                goToObject();
                clickEnter();
                break;
            case "Visit Other's Houses":
                clearScreen();
                listHome();
                doQueryVisitHome();
                clickEnter();
                break;
            case "Return Home":
                clearScreen();
                if(currentSim.getCurrentHome() == currentSim.getHome()){
                    System.out.println("You are already in your house!");
                    break;
                }
                currentSim.visit(calculateTime(currentSim.getCurrentHome(), currentSim.getHome()));
                currentSim.getRoom().removeSimMap(currentSim.getName());
                currentSim.setCurrentHome(currentSim.getHome());
                currentSim.setRoom(currentSim.getHome().getListRuangan().get(0));
                currentSim.getRoom().addSim(currentSim.getName());
                System.out.println(currentSim.getName() + " has returned back home!");
                clickEnter();
            case "Save Game" :
                Save.save();
                clickEnter();
                break;
            case "Exit":
                exitTheGame();
                break;

            // add more action

        }
    }

    public static void doQueryPlayGame() {
        System.out.println(
                "Please insert how long do you want your sim to play game. Please type in the multiples of 30 or -1 if you want to cancel");
        Scanner in = new Scanner(System.in);
        Integer time = in.nextInt();
        while (time < 0 || time % 30 != 0) {
            System.out.println(
                    "You print the wrong input. Please type in the multiples of 30 or -1 if you want to cancel");
            time = in.nextInt();
        }

        if (time == -1) {
            return;
        }

        currentSim.playGame(time);
    }

    public static boolean isNight() {
        return (App.timeNow - 180) % 720 < 6;
    }

    public static void listHome() {
        System.out.println("This is the list of home in this world");
        for (Sim sim : simList) {
            System.out.println("- " + sim.getName() + "'s house in coordinate of (" + (sim.getHome().getLocation().x)
                    + "," + (sim.getHome().getLocation().y) + ")");
        }
    }

    public static void doQueryReadBook() {
        System.out.println(
                "Please insert how long do you want your sim to read books. Please type in the multiples of 30 or -1 if you want to cancel");
        Scanner in = new Scanner(System.in);
        Integer time = in.nextInt();
        while (time < 0 || time % 30 != 0) {
            System.out.println(
                    "You print the wrong input. Please type in the multiples of 30 or -1 if you want to cancel");
            time = in.nextInt();
        }

        if (time == -1) {
            return;
        }

        currentSim.readBook(time);
    }

    public static void doQueryWatchTV() {
        System.out.println(
                "Please insert how long do you want your sim to watch TV. Please type in the multiples of 30 or -1 if you want to cancel");
        Scanner in = new Scanner(System.in);
        Integer time = in.nextInt();
        while (time < 0 || time % 30 != 0) {
            System.out.println(
                    "You print the wrong input. Please type in the multiples of 30 or -1 if you want to cancel");
            time = in.nextInt();
        }

        if (time == -1) {
            return;
        }

        currentSim.watchTV(time);
    }

    public static void doQueryPlayPiano() {
        System.out.println(
                "Please insert how long do you want your sim to play piano. Please type in the multiples of 30 or -1 if you want to cancel");
        Scanner in = new Scanner(System.in);
        Integer time = in.nextInt();
        while (time < 0 || time % 30 != 0) {
            System.out.println(
                    "You print the wrong input. Please type in the multiples of 30 or -1 if you want to cancel");
            time = in.nextInt();
        }

        if (time == -1) {
            return;
        }

        currentSim.playPiano(time);
    }

    public static void doQueryVisitHome() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please type the sim's name that you will visit or Quit if you want to cancel");
        String name = in.nextLine();
        while ((!isThereSimNamed(name) && !name.equals("Quit")) || currentSim.getName().equals(name)) {
            if (currentSim.getName().equals(name)) {
                System.out.println("That is your own home, dumbass");
            } else
                System.out.println("There is no sim named " + name);
            System.out.println("Please type the correct one");
            name = in.nextLine();
        }

        if (name.equals("Quit")) {
            return;
        }

        for (Home home : world.getListRumah()) {
            if (home.getName().equals(name + "'s Home")) {
                currentSim.visit(calculateTime(currentSim.getCurrentHome(), home));
                currentSim.getRoom().removeSimMap(currentSim.getName());
                currentSim.setCurrentHome(home);
                currentSim.setRoom(home.getListRuangan().get(0));
                currentSim.getRoom().addSim(currentSim.getName());
                System.out.println(currentSim.getName() + " has moved to " + name + "'s home!");
                break;
            }
        }
    }

    public static Long calculateTime(Home a, Home b) {
        Point loca = a.getLocation();
        Point locb = b.getLocation();
        Integer x = loca.x - locb.x;
        Integer y = loca.y - locb.y;
        return Math.round(Math.sqrt((Integer) (x * x + y * y)));
    }

    public static boolean isThereSimNamed(String name) {
        for (Sim sim : simList) {
            if (sim.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static void doQueryCook() {
        // check for each dish
        if (canSimCookAnyDish() == false) {
            System.out.println("Your sim cannot cook anything. Please buy some ingredients first!");
            return;
        }
        System.out.println(
                "\nBut, you are going to need some ingredients to cook the dish!.\nHere's the actual list of food that you can cook : ");
        System.out.println((canSimCookThisDish("Chicken Rice") ? "- Chicken Rice\n" : "")
                + (canSimCookThisDish("Curry Rice") ? "- Curry Rice\n" : "")
                + (canSimCookThisDish("Soy Milk") ? "- Soy Milk\n" : "")
                + (canSimCookThisDish("Stir-fried Vegetables") ? "- Stir-fried Vegetables\n" : "")
                + (canSimCookThisDish("Beef Steak") ? "- Beef Steak\n" : ""));
        Scanner in = new Scanner(System.in);
        System.out
                .println("Please type the dish that you want to cook or type Quit instead if you want to cancel this");
        String dish = in.nextLine();
        while (isThereThisDish(dish) == false && canSimCookThisDish(dish) == false && dish.equals("Quit") == false) {
            if (isThereThisDish(dish) == false) {
                System.out.println(
                        "There is no such thing like that. Please input the correct dish that you want to cook!");
            } else if (canSimCookThisDish(dish) == false) {
                System.out.println(
                        "You cannot cook that since you don't have all needed ingredients. Please do not do that again!");
            }
            dish = in.nextLine();
        }

        if (dish.equals("Quit")) {
            return;
        } else {
            currentSim.cook(dish);
        }

    }

    public static boolean canSimCookThisDish(String theDish) {
        switch (theDish) {
            case "Chicken Rice":
                return currentSim.getInventory().checkItemByName("Chicken")
                        && currentSim.getInventory().checkItemByName("Rice");
            case "Curry Rice":
                return currentSim.getInventory().checkItemByName("Rice")
                        && currentSim.getInventory().checkItemByName("Potato")
                        && currentSim.getInventory().checkItemByName("Carrot")
                        && currentSim.getInventory().checkItemByName("Beef");
            case "Soy Milk":
                return currentSim.getInventory().checkItemByName("Milk")
                        && currentSim.getInventory().checkItemByName("Peanut");
            case "Stir-fried Vegetables":
                return currentSim.getInventory().checkItemByName("Carrot")
                        && currentSim.getInventory().checkItemByName("Spinach");
            case "Beef Steak":
                return currentSim.getInventory().checkItemByName("Potato")
                        && currentSim.getInventory().checkItemByName("Beef");
        }
        return false;
    }

    public static boolean isThereThisDish(String theDish) {
        return theDish.equals("Chicken Rice") || theDish.equals("Curry Rice") || theDish.equals("Soy Milk")
                || theDish.equals("Stir-fried Vegetables") || theDish.equals("Beef Steak");
    }

    public static boolean canSimCookAnyDish() {
        return canSimCookThisDish("Chicken Rice") || canSimCookThisDish("Curry Rice") || canSimCookThisDish("Soy Milk")
                || canSimCookThisDish("Stir-fried Vegetables") || canSimCookThisDish("Beef Steak");
    }

    public static void printListDish() {
        clearScreen();
        System.out.println("These are the list of dishes that you can cook");
        System.out.println("1. Chicken Rice\n2. Curry Rice\n3. Nut Milk\n4. Stir Fried Vegetable\n5. Steak");
    }

    public static void chooseFood() {
        System.out.println("Please choose your food or type Quit if you want to cancel");
        Scanner in = new Scanner(System.in);
        String food = in.nextLine();

        while (currentSim.getInventory().getItemByName(food) == null && food.equals("Quit") == false) {
            System.out.println("Your input is wrong.Please choose your food or type Quit if you want to cancel");
            food = in.nextLine();
        }

        if (food.equals("Quit")) {

        } else {
            currentSim.eat();
            currentSim.setSatiety(currentSim.getSatiety()
                    + (Integer) ((Edible) currentSim.getInventory().getItemByName(food)).getKekenyangan());
            System.out.println("You has eaten " + currentSim.getInventory().getItemByName(food).getName()
                    + " and your satiety increased by "
                    + (Integer) ((Edible) currentSim.getInventory().getItemByName(food)).getKekenyangan());
            currentSim.getInventory().removeItem(currentSim.getInventory().getItemByName(food));
        }
    }

    public static boolean isCurrentSimWorking() {
        return currentSim.getStatus().equals("Idle") == false;
    }

    public static void resetWork() {
        for(Sim sim : simList){
            sim.setWorkToday(0);
        }
    }

    public static void killSim(Sim sim) {
        simList.remove(sim);
    }

    public static void updateSimInventory() {
        for (Sim sim : simList) {
            sim.updateInventoryAfterLoad();
            sim.setCurrentRoom(sim.getHome().getListRuangan().get(0));
        }
    }

    public static void updateRoomFurniture() {
        for (Home home : world.getListRumah()) {
            for (Room room : home.getListRuangan()) {
                room.updateRoomMapAfterLoad();
            }
        }
    }
}
