package src.Room;

import java.awt.Dimension;
import java.awt.Point;
import java.util.*;

import src.Objek.Furniture.Furniture;
import src.Objek.Furniture.Bed.SingleBed;
import src.Objek.Furniture.Toilet;
import src.Objek.Furniture.Stove.GasStove;
import src.Objek.Furniture.MejaKursi;
import src.Objek.Furniture.Clock;
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

    //default room
    public Room(){
        SingleBed kasurSingle = new SingleBed();
        Toilet toilet = new Toilet();
        GasStove komporGas = new GasStove();
        Clock jam = new Clock();
        MejaKursi mejaKursiMakan = new MejaKursi();

        addFurniture(kasurSingle, new Point(0,0));
        addFurniture(toilet, new Point(5,0));
        addFurniture(komporGas, new Point(5,3));
        addFurniture(jam, new Point(0,5));
        addFurniture(mejaKursiMakan, new Point(1,3));
    }

    public Room(String roomName){
        this.roomName = roomName;
        this.dimensi.height = 6;
        this.dimensi.width = 6;
        this.north = null;
        this.south = null;
        this.west = null;
        this.east = null;
        Map<Furniture, List<Point>> furnitureList = new HashMap<Furniture, List<Point>>();
    }

    public Room(String roomName, Room north, Room south, Room east, Room west) {
        this.roomName = roomName;
        this.dimensi.height = 6;
        this.dimensi.width = 6;
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
        Map<Furniture, List<Point>> furnitureList = new HashMap<Furniture, List<Point>>();
    }

    public Room(String roomName, Room north, Room south, Room east, Room west, Map<Furniture, List<Point>> furnitureList) {
        this.roomName = roomName;
        this.dimensi.height = 6;
        this.dimensi.width = 6;
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
        this.furnitureList = furnitureList;
    }

    public String getRoomName(){
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

    public void setRoomName(String roomName){
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

    public void setFurnitureList(Map<Furniture, List<Point>> furnitureList){
        this.furnitureList = furnitureList;
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