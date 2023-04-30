package src.World;
//import java.awt.Dimension;
import java.awt.Point;
import java.util.*;
import src.Home.*;
import src.Manager.*;

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

    public void addHome(Home home) {
        Random rand = new Random();
        int i = rand.nextInt(64);
        int j = rand.nextInt(64);
        while(petaWorld[i][j] != null){
             i = rand.nextInt(64);
             j = rand.nextInt(64);        
        }
        Point point = new Point(i,j);
        home.setLocation(point);
        this.listRumah.add(home);
        petaWorld[i][j] = home;
        return;

    } 

    // public Home addHome(String name) {
    //     // Menemukan titik yang masih kosong pada petaWorld
    //     for (int i = 0; i < 64; i++) {
    //         for (int j = 0; j < 64; j++) {
    //             if (petaWorld[i][j] == null) {
    //                 Point temp = new Point(i, j);
    //                 Home rumahBaru = new Home(name, temp);
    //                 this.listRumah.add(rumahBaru);
    //                 petaWorld[i][j] = rumahBaru;
    //                 return rumahBaru;
    //             }
    //         }
    //     }
    //     return null;
    // }

    // public void addHome(String name, Point location) {
    //     if (checkCoordinate(location)) {
    //         Home rumahBaru = new Home(name, location);
    //         this.listRumah.add(rumahBaru);
    //         petaWorld[(int) location.getX()][(int) location.getY()] = rumahBaru;
    //     } else {
    //         System.out.println("Koordinat sudah terisi");
    //     }
    // }

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

    public void showWorld()
    {
        
        System.out.println("World Map");
        for(int j = 0;j < 64;j++){
            for(int i= 0;i <66;i++){
                System.out.print("-");
            }
            System.out.println("");
            System.out.print("|");
            for(int i = 0;i < 64;i++){
                System.out.print(petaWorld[j][i] != null ? "H" : " ");
            }
            System.out.println("|");
        }

        for(int i= 0;i <66;i++){
            System.out.print("-");
        }
        
        System.out.println("");
    }
}