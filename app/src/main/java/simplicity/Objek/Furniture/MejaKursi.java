package simplicity.Objek.Furniture;

import java.awt.Dimension;

import simplicity.Objek.Edible;
import simplicity.Objek.Objek;
import simplicity.Sim.*;

public class MejaKursi extends Furniture {
    public MejaKursi() {
        super("Table and Chair", "Makan", 50, new Dimension(3, 3));
    }

    public void aksi(Sim a, Edible food) throws Exception {
        if (!a.checkInventory((Objek) food)) {
            throw new Exception("Food not in inventory!");
        }
        a.setStatus(String.format("Memakan %s", food.getClass().getSimpleName()));
        a.deleteFromInventory((Objek) food);

        Thread eatThread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(30000);
                } catch (Exception e) {
                    a.addToInventory((Objek) food);
                    e.printStackTrace();
                }
                a.setSatiety(food.getKekenyangan() + a.getSatiety());
                System.out.println("Eating done");
                a.setStatus("Idle");
            }
        };
        eatThread.start();
    }
}