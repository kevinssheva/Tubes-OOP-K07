package simplicity.Room;

import java.awt.Dimension;
import java.awt.Point;
import java.util.*;
import simplicity.Objek.Furniture.Furniture;
import simplicity.Manager.Manager;
import simplicity.Sim.Sim;

public class Room {
    private String roomName;
    private Dimension dimensi;
    private String north;
    private String south;
    private String east;
    private String west;
    private Furniture[][] roomMap;
    private Map<Furniture, List<Point>> furnitureList;
    private Map<String, Point> simMap;
    private boolean underConstruction;

    public Room(String name) {
        roomName = name;
        north = null;
        south = null;
        west = null;
        east = null;
        dimensi = new Dimension(6, 6);
        roomMap = new Furniture[dimensi.width][dimensi.height];
        furnitureList = new HashMap<Furniture, List<Point>>();
        simMap = new HashMap<String, Point>();
        underConstruction = false;
    }

    public Room(String name, boolean underConstruction) {
        roomName = name;
        north = null;
        south = null;
        west = null;
        east = null;
        dimensi = new Dimension(6, 6);
        roomMap = new Furniture[dimensi.width][dimensi.height];
        furnitureList = new HashMap<Furniture, List<Point>>();
        simMap = new HashMap<String, Point>();
        this.underConstruction = underConstruction;
    }

    public String getName() {
        return roomName;
    }

    public Dimension getDimensi() {
        return dimensi;
    }

    public String getNorth() {
        return north;
    }

    public String getSouth() {
        return south;
    }

    public String getEast() {
        return east;
    }

    public String getWest() {
        return west;
    }

    public Furniture[][] getRoomMap() {
        return roomMap;
    }

    public Map<Furniture, List<Point>> getFurnitureList() {
        return furnitureList;
    }

    public Map<String, Point> getSimMap() {
        return simMap;
    }

    public boolean getUnderConstruction() {
        return underConstruction;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public void setWest(String west) {
        this.west = west;
    }

    public void setRoomMap(Furniture[][] roomMap) {
        this.roomMap = roomMap;
    }

    public void setUnderConstruction(boolean underConstruction) {
        this.underConstruction = underConstruction;
    }

    public void adjustSimMap(String sim, Point newLocation) {
        simMap.put(sim, newLocation);
    }

    public void addSim(String sim) {
        Random rand = new Random();
        int x = rand.nextInt(6);
        int y = rand.nextInt(6);
        Point temp = new Point(x, y);
        while (simMap.containsValue(temp)) {
            x = rand.nextInt(6);
            y = rand.nextInt(6);
            temp = new Point(x, y);
        }
        adjustSimMap(sim, temp);
    }

    public void removeSimMap(String sim) {
        simMap.remove(sim);
    }

    public void setFurnitureList(Map<Furniture, List<Point>> furnitureList) {
        this.furnitureList = furnitureList;
    }

    public void removeFurniture(String name) {
        List<Point> points = null;
        Furniture furniture = null;
        for (Map.Entry<Furniture, List<Point>> entry : furnitureList.entrySet()) {
            if (entry.getKey().getName().equals(name)) {
                points = entry.getValue();
                furniture = entry.getKey();
                break;
            }
        }
        if (furniture == null) {
            System.out.println("Furniture doesn't exist");
            return;
        }
        Dimension furnitureDimension = furniture.getDimensi();
        // choose furniture if there are more than one
        if (points.size() > 1) {
            System.out.println("Select the location of the furniture that you want to remove : ");
            for (int i = 0; i < points.size(); i++) {
                System.out.println(i + 1 + ". (" + points.get(i).x + ", " + points.get(i).y + ")");
            }
            Scanner scanRoom = new Scanner(System.in);
            int input = scanRoom.nextInt();
            while (input < 1 || input > points.size()) {
                System.out.println("Your input is wrong. Please type your input again : ");
                input = scanRoom.nextInt();
            }
            Point location = points.get(input - 1);
            for (int i = 0; i < furnitureDimension.height; i++) {
                for (int j = 0; j < furnitureDimension.width; j++) {
                    roomMap[location.y + i][location.x + j] = null;
                }
            }
            points.remove(input - 1);
            furnitureList.put(furniture, points);
            System.out.println("The furniture has been removed successfully");
        } else {
            Point location = points.get(0);
            for (int i = 0; i < furnitureDimension.height; i++) {
                for (int j = 0; j < furnitureDimension.width; j++) {
                    roomMap[location.y + i][location.x + j] = null;
                }
            }
            furnitureList.remove(furniture);
            System.out.println("The furniture has been removed successfully");
        }
        Manager.getCurrentSim().getInventory().addItem(furniture);
    }

    public void addFurniture(Furniture furniture, Point location) {
        Furniture checkMap = roomMap[location.y][location.x];
        if (checkMap == null) {
            Dimension furnitureDimension = furniture.getDimensi();
            Boolean isAvailable = true;
            for (int i = 0; i < furnitureDimension.height; i++) {
                for (int j = 0; j < furnitureDimension.width; j++) {
                    try {
                        if (roomMap[location.y + i][location.x + j] != null) {
                            isAvailable = false;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("The furniture cannot be placed since it's gonna be outside of the room");
                        isAvailable = false;
                    }
                }
            }

            if (isAvailable) {
                for (int i = 0; i < furnitureDimension.height; i++) {
                    for (int j = 0; j < furnitureDimension.width; j++) {
                        roomMap[location.y + i][location.x + j] = furniture;
                    }
                }
                // Add point to furnitureList
                List<Point> points = furnitureList.get(furniture);
                if (points == null) {
                    points = new ArrayList<Point>();
                }
                points.add(location);
                furnitureList.put(furniture, points);
                System.out.println("The furniture has been successfully placed");
            }
        } else {
            throw new IllegalArgumentException("Furniture cannot be placed");
        }
    }

    public Point getFurnitureLocation(String name) {
        for (Map.Entry<Furniture, List<Point>> entry : furnitureList.entrySet()) {
            if (entry.getKey().getName().equals(name)) {
                if (entry.getValue().size() > 1) {
                    System.out.println("Select the location of furniture that you want to g : ");
                    for (int i = 0; i < entry.getValue().size(); i++) {
                        System.out.println(i + 1 + ". (" + entry.getValue().get(i).x + ", "
                                + entry.getValue().get(i).y + ")");
                    }
                    Scanner scanRoom = new Scanner(System.in);
                    int input = scanRoom.nextInt();
                    while (input < 1 || input > entry.getValue().size()) {
                        System.out.println("Your input is wrong. Please type your input again : ");
                        input = scanRoom.nextInt();
                    }
                    return entry.getValue().get(input - 1);
                } else {
                    return entry.getValue().get(0);
                }
            }
        }
        return null;
    }

    public void printRoom() {
        System.out.println("Room map");
        for (int i = 0; i < dimensi.width; i++) {
            for (int j = 0; j < dimensi.height; j++) {
                if (roomMap[i][j] != null) {
                    System.out.print(" " + roomMap[i][j].getName().charAt(0) + " ");
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }

        // print simMap and roomMap in 2d array
        System.out.println("Sim Map: ");
        for (int i = 0; i < dimensi.width; i++) {
            for (int j = 0; j < dimensi.height; j++) {
                if (simMap.containsValue(new Point(i, j))) {
                    for (Map.Entry<String, Point> entry : simMap.entrySet()) {
                        if (entry.getValue().equals(new Point(i, j))) {
                            System.out.print(" " + entry.getKey().charAt(0) + " ");
                        }
                    }
                } else if (roomMap[i][j] != null) {
                    System.out.print(" " + roomMap[i][j].getName().charAt(0) + " ");
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }

    }

    public void showFurniture() {
        if (furnitureList.isEmpty()) {
            System.out.println("You don't have any furniture yet");
            return;
        }
        System.out.println("Furniture List: ");
        for (Map.Entry<Furniture, List<Point>> entry : furnitureList.entrySet()) {
            System.out.println(entry.getKey().getName() + " : ");
            for (Point point : entry.getValue()) {
                System.out.println("(" + point.getX() + ", " + point.getY() + ")");
            }
        }
    }

    public boolean isSimOnFurniture(String sim, Class<? extends Furniture> furnitureClass) {
        for (Map.Entry<String, Point> simEntry : simMap.entrySet()) {
            if (simEntry.getKey().equals(sim)) {
                for (Map.Entry<Furniture, List<Point>> furnitureEntry : furnitureList.entrySet()) {
                    if (furnitureClass.isInstance(furnitureEntry.getKey())) {
                        for (Point point : furnitureEntry.getValue()) {
                            if (point.equals(simEntry.getValue())) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Room room = new Room("Kamar 1");
        System.out.println("Room Name: " + room.getName());
        // print furniture list
        System.out.println("Furniture List: ");
        for (Map.Entry<Furniture, List<Point>> entry : room.getFurnitureList().entrySet()) {
            System.out.println(entry.getKey().getName() + " : ");
            for (Point point : entry.getValue()) {
                System.out.println("(" + point.getX() + ", " + point.getY() + ")");
            }
        }
        room.printRoom();
    }
}