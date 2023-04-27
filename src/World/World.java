package src.World;
//import java.awt.Dimension;
import java.awt.Point;
import java.util.*;
import src.Home.*;

public class World {
    //private Dimension dimension;                                  // keknya agak dobel pake dimension ternyata
    //private Home[][] petaWorld;                                   // pake point keknya bisa ga pake petanya
    private ArrayList<Home> listRumah;
    //private ArrayList<Point> listPointRumah;

    public World() {
        //this.dimension = new Dimension(64, 64);
        //this.petaWorld = new Home[64][64];
        this.listRumah = new ArrayList<Home>();
    }

    public ArrayList<Home> getListRumah() {
        return listRumah;
    }

    public void addHome(Home rumahBaru) {
        if(checkCoordinate(rumahBaru.getLocation())){
            this.listRumah.add(rumahBaru);
        }
    }

    public Boolean checkCoordinate(Point location) {
        //return petaWorld[x][y] == null;
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