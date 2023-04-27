package src.Objek.Furniture;

import src.*;
import src.Sim.*;

public class Bed extends Furniture {

    public Bed(String bedName, String aksi, Integer price) {
        super(bedName, aksi, price);
    }

    public void aksi(Sim a, Integer time) {
        if ((time) % 240 != 0)
            return;
        Thread temp = new Thread() {
            public void run() {
                a.setStatus("Sleeping");
                while ((System.currentTimeMillis() - Main.startMillis) / 1000 <= time) {
                    if ((System.currentTimeMillis() - Main.startMillis) % 240000 == 0) {
                        a.setMood(30 + a.getMood());
                        a.setHealth(20 + a.getHealth());
                    }

                }
                a.setStatus("Idle");
            }
        };

        temp.start();
    }

    public static void main(String[] args) {

    }
}