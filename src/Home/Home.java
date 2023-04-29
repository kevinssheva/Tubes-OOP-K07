package src.Home;

import java.util.*;
import java.awt.Point;
import src.Room.*;

public class Home {
    private List<Room> listRuangan;
    private Point location;

    public Home(Point location) {
        this.location = location;
        this.listRuangan = new ArrayList<Room>();
        Room room = new Room("Main Room");
        listRuangan.add(room);
    }

    public List<Room> getListRuangan() {
        return listRuangan;
    }

    public Point getLocation() {
        return location;
    }

    public void addRuangan(Room room, String direction, String name) {
        Room newRoom = new Room(name);
        if (direction == "north") {
            if (room.getNorth() != null) {
                System.out.println("Ruangan sudah ada");
                return;
            }
            room.setNorth(newRoom);
            newRoom.setSouth(room);
        } else if (direction == "south") {
            if (room.getSouth() != null) {
                System.out.println("Ruangan sudah ada");
                return;
            }
            room.setSouth(newRoom);
            newRoom.setNorth(room);
        } else if (direction == "east") {
            if (room.getEast() != null) {
                System.out.println("Ruangan sudah ada");
                return;
            }
            room.setEast(newRoom);
            newRoom.setWest(room);
        } else if (direction == "west") {
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
