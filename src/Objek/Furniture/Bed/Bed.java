package src.Objek.Furniture.Bed;

import java.awt.Dimension;
import src.Objek.Furniture.Furniture;
import src.Sim.*;

public abstract class Bed extends Furniture {

    public Bed(String name, Integer price, Dimension dimensi) {
        super(name, "Tidur", price, dimensi);
    }

    public void aksi(Sim a, Integer time) { // i think this is useless :D
        a.setStatus("Tidur"); 
        Thread sleepThread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(time * 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                a.setMood((time/240 * 30) + a.getMood());
                a.setHealth((time/240 * 20) + a.getHealth());
                System.out.println("Sleeping done");
                a.setStatus("Idle");
            }
        };
        sleepThread.start();
    }

    public static void main(String[] args) {

    }
}