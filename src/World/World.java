package src.World;
//import java.awt.Dimension;
import java.awt.Point;
import java.util.*;
import src.Home.*;

public class World {
    private Home[][] petaWorld;
    private ArrayList<Home> listRumah;

    public World() {
        this.petaWorld = new Home[64][64];
        this.listRumah = new ArrayList<Home>();
    }

    public ArrayList<Home> getListRumah() {
        return listRumah;
    }

    public void addHome() {
        // Menemukan titik yang masih kosong pada petaWorld
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                if (petaWorld[i][j] == null) {
                    Point temp = new Point(i, j);
                    Home rumahBaru = new Home(temp);
                    this.listRumah.add(rumahBaru);
                    petaWorld[i][j] = rumahBaru;
                    return;
                }
            }
        }
    }

    public void addHome(Point location) {
        if (checkCoordinate(location)) {
            Home rumahBaru = new Home(location);
            this.listRumah.add(rumahBaru);
            petaWorld[(int) location.getX()][(int) location.getY()] = rumahBaru;
        } else {
            System.out.println("Koordinat sudah terisi");
        }
    }

    public Boolean checkCoordinate(Point location) {
        if(location.getX() < 0 || location.getY() < 0 || location.getX() > 64 || location.getY() > 64){
            return false;
        }
        for (Home rumah : listRumah) {
            if(rumah.getLocation().equals(location)){
                return false;
            }
        }
        return true;
    }
}