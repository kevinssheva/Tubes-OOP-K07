package src.Room;

import java.awt.Dimension;
import java.awt.Point;
import java.util.*;

import src.Objek.Furniture.Furniture;
import src.Sim.Sim;

public class Room {
    private String roomName;
    private Dimension dimensi;
    private Room north;
    private Room south;
    private Room east;
    private Room west;
    private Furniture[][] roomMap;
    private Map<Furniture, List<Point>> furnitureList;
    private Map<Sim, Point> simMap;

    public Room(String name) {
        roomName = name;
        north = null;
        south = null;
        west = null;
        east = null;
        dimensi = new Dimension(6, 6);
        roomMap = new Furniture[dimensi.width][dimensi.height];
        furnitureList = new HashMap<Furniture, List<Point>>();
        simMap = new HashMap<Sim, Point>();
    }

    public String getName() {
        return roomName;
    }

    public Dimension getDimensi() {
        return dimensi;
    }

    public Room getNorth() {
        return north;
    }

    public Room getSouth() {
        return south;
    }

    public Room getEast() {
        return east;
    }

    public Room getWest() {
        return west;
    }

    public Furniture[][] getRoomMap() {
        return roomMap;
    }

    public Map<Furniture, List<Point>> getFurnitureList() {
        return furnitureList;
    }

    public Map<Sim, Point> getSimMap() {
        return simMap;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public void setRoomMap(Furniture[][] roomMap) {
        this.roomMap = roomMap;
    }

    public void adjustSimMap(Sim sim, Point newLocation) {
        simMap.put(sim, newLocation);
    }

    public void setFurnitureList(Map<Furniture, List<Point>> furnitureList) {
        this.furnitureList = furnitureList;
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
                        System.out.println("Furniture melebihi batas ruangan");
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
                System.out.println("Furniture berhasil ditempatkan");
            }
        } else {
            throw new IllegalArgumentException("Furniture tidak dapat ditempatkan");
        }
    }

    public Point getFurnitureLocation(String name) {
        for (Map.Entry<Furniture, List<Point>> entry : furnitureList.entrySet()) {
            if (entry.getKey().getName().equals(name)) {
                if (entry.getValue().size() > 1) {
                    System.out.println("Pilih lokasi furniture yang ingin dituju : ");
                    for (int i = 0; i < entry.getValue().size(); i++) {
                        System.out.println(i + 1 + ". (" + entry.getValue().get(i).x + ", "
                                + entry.getValue().get(i).y + ")" );
                    }
                    Scanner scanRoom = new Scanner(System.in);
                    int input = scanRoom.nextInt();
                    while (input < 1 || input > entry.getValue().size()) {
                        System.out.println("Input salah, masukkan ulang: ");
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

        //print simMap and roomMap in 2d array
        System.out.println("Sim Map: ");
        for (int i = 0; i < dimensi.width; i++) {
            for (int j = 0; j < dimensi.height; j++) {
                if (simMap.containsValue(new Point(i, j))) {
                    for (Map.Entry<Sim, Point> entry : simMap.entrySet()) {
                        if (entry.getValue().equals(new Point(i, j))) {
                            System.out.print(" " + entry.getKey().getName().charAt(0) + " ");
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