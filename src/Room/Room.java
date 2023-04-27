package src.Room;

import java.awt.Dimension;
import java.awt.Point;
import java.util.*;

import src.Objek.Furniture.Furniture;
import src.Sim.Sim;

public class Room {
    private Dimension dimensi;
    private Room north;
    private Room south;
    private Room east;
    private Room west;
    private Furniture[][] roomMap;
    private Map<Furniture, List<Point>> furnitureList;
    private Map<Sim, Point> simMap;

    public Room() {
        //tambahin disini
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

    public void addFurniture(Furniture furniture, Point location) {
        Furniture map = roomMap[location.x][location.y];
        if (map == null) {
            Dimension dimensi = furniture.getDimensi();
            Boolean isAvailable = true;
            for (int i = 0; i < dimensi.width; i++) {
                for (int j = 0; j < dimensi.height; j++) {
                    if (roomMap[location.x + i][location.y + j] != null) {
                        isAvailable = false;
                    }
                }
            }

            if (isAvailable) {
                for (int i = 0; i < dimensi.width; i++) {
                    for (int j = 0; j < dimensi.height; j++) {
                        roomMap[location.x + i][location.y + j] = furniture;
                    }
                }
                List<Point> list = new ArrayList<Point>();
                list.add(location);
                furnitureList.put(furniture, list);
            }
        }
        return;
    }
}
