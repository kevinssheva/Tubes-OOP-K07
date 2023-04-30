package src.Home;

import java.util.*;
import java.awt.Point;
import src.Room.*;
import src.Sim.Sim;

public class Home {
    private String name;
    private List<Room> listRuangan;
    private Point location;

    public Home(String name) {
        this.name = name;
        this.listRuangan = new ArrayList<Room>();
        Room room = new Room("Main Room");
        listRuangan.add(room);
    }

    public Home(String name, Point location) {
        this.location = location;
        this.listRuangan = new ArrayList<Room>();
        Room room = new Room("Main Room");
        listRuangan.add(room);
    }

    public String getName() {
        return name;
    }

    public List<Room> getListRuangan() {
        return listRuangan;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public void showRoom() {
        int i = 1;
        for (Room room : listRuangan) {
            String underConstruction = room.getUnderConstruction()? " (Under Construction)" : "";
            System.out.println(i++ + ". " + room.getName() + underConstruction);
        }
    }

    public Room getRoomByName(String name) {
        for (Room room : listRuangan) {
            if (room.getName().equals(name)) {
                return room;
            }
        }
        return null;
    }

    public boolean checkRoom(String name) {
        for (Room room : listRuangan) {
            if (room.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void addRuangan(String referenceRoom, String direction, String name) {
        System.out.println("So you want to add a room to " + referenceRoom + " to the " + direction + " with name " + name);
        Room room = null;
        for (Room r : listRuangan) {
            if (r.getName().equals(referenceRoom)) {
                room = r;
                break;
            }
        }
        Room newRoom = new Room(name, true);
        if (direction.equals("north")) {
            if (room.getNorth() != null) {
                System.out.println("Ruangan sudah ada");
                return;
            }
            room.setNorth(newRoom);
            newRoom.setSouth(room);
        } else if (direction.equals("south")) {
            if (room.getSouth() != null) {
                System.out.println("Ruangan sudah ada");
                return;
            }
            room.setSouth(newRoom);
            newRoom.setNorth(room);
        } else if (direction.equals("east")) {
            if (room.getEast() != null) {
                System.out.println("Ruangan sudah ada");
                return;
            }
            room.setEast(newRoom);
            newRoom.setWest(room);
        } else if (direction.equals("west")) {
            if (room.getWest() != null) {
                System.out.println("Ruangan sudah ada");
                return;
            }
            room.setWest(newRoom);
            newRoom.setEast(room);
        } else {
            System.out.println("Masukkan arah yang benar");
            return;
        }
        listRuangan.add(newRoom);
    }


}
