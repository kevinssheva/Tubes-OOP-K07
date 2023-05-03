package src.Objek.Furniture;

import java.awt.Dimension;
import src.Sim.*;

public class Toilet extends Furniture {
    public Toilet() {
        super("Toilet", "Buang Air", 50, new Dimension(1, 1));
    }

    // public void aksi(Sim a) {
    //     a.setStatus("Buang Air");
    //     Thread sleepThread = new Thread() {
    //         public void run() {
    //             try {
    //                 Thread.sleep(10000);
    //             } catch (Exception e) {
    //                 e.printStackTrace();
    //             }
    //             a.setSatiety(a.getSatiety() - 20);
    //             a.setMood(a.getMood() + 10);
    //             System.out.println("Defecate done");
    //             a.setStatus("Idle");
    //         }
    //     };
    //     sleepThread.start();
    // }
}