package simplicity.Home;

import java.util.*;
import java.awt.Point;
import simplicity.Room.*;

public class Home {
    private String name;
    private List<Room> listRuangan;
    private Map<Point, Room> mapRuangan;
    private Point location;

    public Home(String name) {
        this.name = name;
        this.listRuangan = new ArrayList<Room>();
        this.mapRuangan = new HashMap<Point, Room>();
        Room room = new Room("Main Room");
        listRuangan.add(room);
        mapRuangan.put(new Point(0, 0), room);
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
            String underConstruction = room.getUnderConstruction() ? " (Under Construction)" : "";
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

    public Room getRoomByPoint(Point point) {
        for (Map.Entry<Point, Room> entry : mapRuangan.entrySet()) {
            if (entry.getKey().getX() == point.getX() && entry.getKey().getY() == point.getY()) {
                return entry.getValue();
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

    public Point getRoomPoint(Room room) {
        for (Map.Entry<Point, Room> entry : mapRuangan.entrySet()) {
            if (entry.getValue().equals(room)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void checkDirectionRoom(Room newRoom, Point point) {
        if (mapRuangan.containsKey(new Point(point.x - 1, point.y))) {
            newRoom.setWest(mapRuangan.get(new Point(point.x - 1, point.y)).getName());
            mapRuangan.get(new Point(point.x - 1, point.y)).setEast(newRoom.getName());
        }
        if (mapRuangan.containsKey(new Point(point.x + 1, point.y))) {
            newRoom.setEast(mapRuangan.get(new Point(point.x + 1, point.y)).getName());
            mapRuangan.get(new Point(point.x + 1, point.y)).setWest(newRoom.getName());
        }
        if (mapRuangan.containsKey(new Point(point.x, point.y - 1))) {
            newRoom.setSouth(mapRuangan.get(new Point(point.x, point.y - 1)).getName());
            mapRuangan.get(new Point(point.x, point.y - 1)).setNorth(newRoom.getName());
        }
        if (mapRuangan.containsKey(new Point(point.x, point.y + 1))) {
            newRoom.setNorth(mapRuangan.get(new Point(point.x, point.y + 1)).getName());
            mapRuangan.get(new Point(point.x, point.y + 1)).setSouth(newRoom.getName());
        }
    }

    public void addRuangan(String referenceRoom, String direction, String name) {
        System.out.println(
                "So you want to add a room to " + referenceRoom + " to the " + direction + " with name " + name);
        Room room = null;
        for (Room r : listRuangan) {
            if (r.getName().equals(referenceRoom)) {
                room = r;
                break;
            }
        }
        Point roomPoint = getRoomPoint(room);

        Room newRoom = new Room(name, true);
        if (direction.equals("north")) {
            if (room.getNorth() != null) {
                System.out.println("Ruangan sudah ada");
                return;
            }
            Point newRoomPoint = new Point(roomPoint.x, roomPoint.y + 1);

            mapRuangan.put(newRoomPoint, newRoom);
            // check every direction of the new room
            checkDirectionRoom(newRoom, newRoomPoint);

        } else if (direction.equals("south")) {
            if (room.getSouth() != null) {
                System.out.println("Ruangan sudah ada");
                return;
            }
            Point newRoomPoint = new Point(roomPoint.x, roomPoint.y - 1);
            mapRuangan.put(newRoomPoint, newRoom);
            // check every direction of the new room
            checkDirectionRoom(newRoom, newRoomPoint);
        } else if (direction.equals("east")) {
            if (room.getEast() != null) {
                System.out.println("Ruangan sudah ada");
                return;
            }
            Point newRoomPoint = new Point(roomPoint.x + 1, roomPoint.y);
            mapRuangan.put(newRoomPoint, newRoom);
            // check every direction of the new room
            checkDirectionRoom(newRoom, newRoomPoint);
        } else if (direction.equals("west")) {
            if (room.getWest() != null) {
                System.out.println("Ruangan sudah ada");
                return;
            }
            Point newRoomPoint = new Point(roomPoint.x - 1, roomPoint.y);
            mapRuangan.put(newRoomPoint, newRoom);
            // check every direction of the new room
            checkDirectionRoom(newRoom, newRoomPoint);
        } else {
            System.out.println("Masukkan arah yang benar");
            return;
        }
        listRuangan.add(newRoom);
    }

}
