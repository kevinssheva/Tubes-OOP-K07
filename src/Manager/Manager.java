package src.Manager;

import java.awt.Point;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
    private static Sim currentSim = null;
    private static List<Sim> simList = new ArrayList<Sim>();
    private static boolean gameStarted = false;
    private static World world = new World();
    private static String[] arrayBuyable = {"Single Bed","Queen Size Bed","King Size Bed","Toilet","Electric Stove","Gas Stove","Table and Chair","Clock","Rice","Potato","Chicken","Beef","Carrot","Spinach","Peanut","Milk"};
    private static ArrayList<String> buyableList = new ArrayList<>(Arrays.asList(arrayBuyable));

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
                MejaKursi mejaKursi= new MejaKursi();
                putIntoInventory = mejaKursi;
                break;
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

        putArray.add(putIntoInventory);
        Thread putInventoryThread = new Thread(){
            public void run(){
                Random rand = new Random();
                long finalTime = Main.timeNow + rand.nextLong(45)+1;
                while(Main.timeNow < finalTime){
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        System.out.println("Error");
                    }
                }
                currentSim.addToInventory(putArray.get(0));
                System.out.println(putArray.get(0).getName() + " are already arrived at your inventory");
            }
        };

        putInventoryThread.start();

    }

    public static void queryBuyThings(){
        System.out.println("Here is the list of things that you can buy\n");
        System.out.println("- Single Bed\nPrice : 50\nDimension : 4 x 1\n");
        System.out.println("- Queen Size Bed\nPrice : 100\nDimension : 4 x 2\n");
        System.out.println("- King Size Bed\nPrice : 150\nDimension : 5 x 2\n");
        System.out.println("- Toilet\nPrice : 50\nDimension : 1 x 1\n");
        System.out.println("- Gas Stove\nPrice : 100\nDimension : 2 x 1\n");
        System.out.println("- Electrical Stove\nPrice : 200\nDimension : 1 x 1\n");
        System.out.println("- Table and Chair\nPrice : 50\nDimension : 3 x 3\n");
        System.out.println("- Jam\nPrice : 10\nDimension : 1 x 1\n");
    
        System.out.println("- Rice\nPrice : 5\nSatiety : 5\n");
        System.out.println("- Potato\nPrice : 3\nSatiety : 4\n");
        System.out.println("- Chicken\nPrice : 10\nSatiety : 8\n");
        System.out.println("- Beef\nPrice : 12\nSatiety : 15\n");
        System.out.println("- Carrot\nPrice : 3\nSatiety : 2\n");
        System.out.println("- Spinach\nPrice : 3\nSatiety : 2\n");
        System.out.println("- Peanut\nPrice : 2\nSatiety : 2\n");
        System.out.println("- Milk\nPrice : 2\nSatiety : 1\n");
        Scanner in = new Scanner(System.in);
        System.out.println("Please type things that you want or type Quit if you want to cancel this");
        String thingsName = in.nextLine();
        while(buyableList.contains(thingsName) == false && thingsName.equals("Quit") == false){
            System.out.println("There is no such thing. please type it another thing or type Quit if you want to cancel this");
            thingsName = in.nextLine();
        }

        if(thingsName.equals("Quit")){
            return;
        }else{
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
        clearScreen();
        System.out.println("Here is the list of help");
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
                exitTheGame();
                break;
        }
    }

    public static void exitTheGame() {
        System.out.println("Thank you for playing this game!");
        System.exit(0);
    }

    public static boolean getGameStarted() {
        return gameStarted;
    }

    public static void clickEnter() {
        // System.out.println("Press Enter to return to the main menu...");
        // Scanner scanner = new Scanner(System.in);
        // scanner.nextLine();
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
        System.out.println("Here is the list of thing that you can do");
        System.out.println("- Help");
        System.out.println("- Work");
        System.out.println("- Exercise");
        // sleep
        if(currentSim.getRoom().checkBed(currentSim)){
            System.out.println("- Sleep");
        }
        // eat
        if(currentSim.getRoom().checkMejaKursi(currentSim)){
            System.out.println("- Eat");
        }
        // cook
        if(currentSim.getRoom().checkStove(currentSim)){
            System.out.println("- Cook");
        }
        System.out.println("- Buy Things");
        System.out.println("- View Sim Info");
        System.out.println("- View Current Location");
        System.out.println("- View Inventory");
        System.out.println("- Upgrade House");
        System.out.println("- Move Room");
        System.out.println("- Edit Room");
        System.out.println("- Add Sim");
        System.out.println("- Change Sim");
        System.out.println("- List Object");
        System.out.println("- Go To Object");
        System.out.println("- Visit Other's Houses");
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
        clickEnter();
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
        System.out.println("Here is your list of room in your house");
        currentSim.getHome().showRoom();
        System.out.println("What room do you want to use as a reference?");
        Scanner in = new Scanner(System.in);
        String room = in.nextLine();
        Room referenceRoom = currentSim.getHome().getRoomByName(room);
        while (referenceRoom == null || referenceRoom.getUnderConstruction()) {
            System.out.println("Room not available, please try again");
            room = in.nextLine();
        }

        System.out.println("What room do you want to add?");
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

        Thread upgradeThread = new Thread(){
            public void run(){
                long finalTime = Main.timeNow + 20;
                while(Main.timeNow < finalTime){
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        System.out.println("Error");
                    }
                }
                currentSim.getHome().getRoomByName(roomNameFinal).setUnderConstruction(false);
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
        try {
            switch (direction) {
                case "North":
                    if (temp.getNorth().getUnderConstruction()) {
                        System.out.println("Room is under construction");
                        break;
                    }
                    currentSim.setRoom(temp.getNorth());
                    currentSim.getRoom().adjustSimMap(currentSim, new Point(5, 0));
                    temp.removeSimMap(currentSim);
                    break;
                case "South":
                    if (temp.getSouth().getUnderConstruction()) {
                        System.out.println("Room is under construction");
                        break;
                    }
                    currentSim.setRoom(temp.getSouth());
                    currentSim.getRoom().adjustSimMap(currentSim, new Point(0, 0));
                    temp.removeSimMap(currentSim);
                    break;
                case "East":
                    if (temp.getEast().getUnderConstruction()) {
                        System.out.println("Room is under construction");
                        break;
                    }
                    currentSim.setRoom(temp.getEast());
                    currentSim.getRoom().adjustSimMap(currentSim, new Point(0, 0));
                    temp.removeSimMap(currentSim);
                    break;
                case "West":
                    if (temp.getWest().getUnderConstruction()) {
                        System.out.println("Room is under construction");
                        break;
                    }
                    currentSim.setRoom(temp.getWest());
                    currentSim.getRoom().adjustSimMap(currentSim, new Point(0, 5));
                    temp.removeSimMap(currentSim);
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
        clickEnter();
        simList.add(sim);
        if (currentSim == null) {
            currentSim = sim;
        }
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
        System.out.println("Here is the list of object that you have");
        currentSim.getRoom().showFurniture();
        System.out.println("Which object do you want to go?");
        String objectName = in.nextLine();
        Point point = currentSim.getRoom().getFurnitureLocation(objectName);
        if (point == null) {
            System.out.println("The object with the name '" + objectName + "' does not exist in the room.");
            return;
        }
        currentSim.getRoom().adjustSimMap(currentSim, point);
        System.out.println("You are now in front of " + objectName);
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
                System.out.println("Please input how many seconds do you want to work.\nMake sure the input is in multiples of 120.\nIf you don't want to work,please type -1");
                int time = in.nextInt();
                while (time % 20 != 0 && time != -1) {
                    System.out.println("Please input the multiples of 120 or -1 if you don't want to work");
                    time = in.nextInt();
                }

                if (time != -1) {
                    currentSim.kerja(time);
                }

                clickEnter();
                break;
            case "Exercise":
                clearScreen();
                System.out.println("Please input how many seconds do you want to exercise.\nMake sure the input is in multiples of 20.\nIf you don't want to work,please type -1");
                int timeExercise = in.nextInt();
                while(timeExercise % 20 != 0 && timeExercise != -1){
                    System.out.println("Please input the multiples of 20 or -1 if you don't want to work");
                    timeExercise = in.nextInt();
                }
                if(timeExercise != -1){
                    currentSim.exercise(timeExercise);
                }

                clickEnter();
                break;
            case "Sleep":
                clearScreen();
                if(currentSim.getRoom().checkBed(currentSim)){
                    System.out.println("Please input how many seconds do you want to sleep.\nMake sure the input is in multiples of 240.\nIf you don't want to work,please type -1");
                    int timeSleep = in.nextInt();
                    while(timeSleep % 20 != 0 && timeSleep != -1){
                        System.out.println("Please input the multiples of 240 or -1 if you don't want to work");
                        timeSleep = in.nextInt();
                    }
                    if(timeSleep != -1){
                        currentSim.sleep(timeSleep);
                    }                                        
                }else{
                    System.out.println("I know that you actually couldn't work because your sim are not sitting on top of a Bed.\nPlease do not do this again!");
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
                break;
            case "List Object":
                break;
            case "Go To Object":
                break;
            case "Exit":
                exitTheGame();
                break;

            // add more action

        }
    }

    public static boolean isCurrentSimWorking() {
        return currentSim.getStatus().equals("Idle") == false;
    }
}
