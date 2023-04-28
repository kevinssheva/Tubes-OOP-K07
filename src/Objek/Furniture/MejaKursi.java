package src.Objek.Furniture;

import java.awt.Dimension;

import src.Objek.Edible;
import src.Sim.*;

public class MejaKursi extends Furniture{
    public MejaKursi(){
        super("Meja dan Kursi", "Makan", 50, new Dimension(3, 3));
    }

    public void aksi(Sim a, Edible food) throws Exception {
        if(!a.checkInventory(food)){
            throw new Exception("Food not in inventory!");
        }
        a.setStatus(String.format("Memakan %s", food.getClass().getSimpleName()));
        a.deleteFromInventory(food);

        Thread eatThread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(30000);
                } catch (Exception e) {
                    a.addToInventory(food);
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