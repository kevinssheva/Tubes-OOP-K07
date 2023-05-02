package src.Objek.Furniture;

import java.awt.Dimension;

import src.Main;

public class Clock extends Furniture {
    public Clock() {
        super("Clock", "View Time", 10, new Dimension(1, 1));
    }

    public void aksi() {
        long time = Main.timeNow;
        long minutes = time / 60;
        long seconds = time % 60;
        System.out.println("Time now: " + minutes + ":" + seconds);
    }
}