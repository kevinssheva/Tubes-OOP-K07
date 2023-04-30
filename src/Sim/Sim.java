package src.Sim;

import java.awt.Point;
import src.Inventory.*;
import src.Job.*;
import src.Objek.Objek;
import src.Objek.Furniture.Furniture;
import src.Room.*;
import src.Main;
import src.Home.*;

public class Sim {
    private String name;
    private Job job = null;
    private Integer money;
    private Integer mood;
    private Integer health;
    private String status;
    private Integer satiety;
    private Inventory<Objek> inventory;
    private Room currentRoom;
    private Home currentHome; // misal kalo berkunjung currentHome nya yang ganti bukan home nya
    private Home home; // punya rumah sendiri

    public Sim(String name, Job job, Integer satiety, Integer money, Integer mood, Integer health, String status,
            Home home) {
        this.name = name;
        this.job = job;
        this.money = money;
        this.mood = mood;
        this.health = health;
        this.status = status;
        this.satiety = satiety;
        this.inventory = new Inventory<>();
        this.home = home;
        currentRoom = home.getListRuangan().get(0);
        currentRoom.adjustSimMap(this, new Point(0, 0));
        this.currentHome = home;
    }

    public Sim(String name, Job job, Integer money, String status) {
        this.name = name;
        this.job = job;
        this.money = 80;
        this.mood = 80;
        this.health = 80;
        this.status = status;
        this.satiety = 80;
        this.inventory = new Inventory<>();
    }

    public Integer getSatiety() {
        return satiety;
    }

    public void setSatiety(Integer satiety) {
        this.satiety = satiety;
    }

    public Integer getHealth() {
        return health;
    }

    public Integer getMoney() {
        return money;
    }

    public Integer getMood() {
        return mood;
    }

    public Job getJob() {
        return job;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public Room getRoom() {
        return currentRoom;
    }

    public Home getHome() {
        return home;
    }

    public Inventory<Objek> getInventory() {
        return inventory;
    }

    public void setHealth(Integer health) {
        this.health = Math.min(100, health);
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setMoney(Integer money) {
        this.money = Math.min(100, money);
    }

    public void setMood(Integer mood) {
        this.mood = Math.min(100, mood);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRoom(Room r) {
        this.currentRoom = r;
    }

    public void setHome(Home h) {
        this.home = h;
    }

    public void exercise(Integer time) {
        if (time % 20000 != 0)
            return;
        setStatus("Exercise");
        
        Thread exerciseThread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(time * 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                setHealth(health + 10);
                setSatiety(satiety + 10);
                System.out.println("Exercise done");
                setStatus("Idle");
            }
        };
        exerciseThread.start();
    }

    public void sleep(Integer time, Sim a) {
        setStatus(String.format("Sleeping..."));
        Thread sleepThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int siklus = time / 240;
                    long startTime = Main.timeNow;
                    long updateTime = 240;
                    while ((Main.timeNow - startTime) <= time) {
                        updateTime -= (Main.timeNow - startTime);
                        while (siklus > 0) {
                            a.setMood(getMood() + 30);
                            a.setHealth(getHealth() + 20);
                            siklus -= 1;
                        }
                    }
                } catch (Exception e) {

                }
            }
        });
        sleepThread.start();
    }

    public void kerja(Integer time) {
        if (time % 20 == 0) {
            setStatus(String.format("Working as %s...", job.getName()));
            Thread workThread = new Thread() {
                public void run() {
                    try {
                        Thread.sleep(time * 1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // System.out.println(getMoney());
                    // System.out.println(job.getDailyPay() * (time / 20));
                }
            };
            workThread.start();

            try {
                workThread.join();
                setSatiety(getSatiety() - (10 * (time / 30)));
                setMood(getMood() - (10 * (time / 30)));
                setMoney(getMoney() + (job.getDailyPay() * (time / 20)));
                System.out.println("Work done");
                setStatus("Idle");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void berkunjung(Home otherHome) {
        Thread visitThread = new Thread() {
            public void run() {
                try {
                    setStatus("Visiting another house...");
                    long startTime = Main.timeNow;
                    long updateTime = 30;
                    double time = getHome().getLocation().distance(otherHome.getLocation());
                    while ((Main.timeNow - startTime) <= time) {
                        updateTime -= (Main.timeNow - startTime);
                        if (updateTime <= 0) {
                            setMood(getMood() + 10);
                            setSatiety(getSatiety() - 10);
                        }
                    }
                    setStatus("Idle");
                } catch (Exception e) {

                }
            }
        };
        visitThread.start();
    }

    public void checkTime() {
        long timeInSeconds = Main.timeNow;
        int secondsPerRealDay = 86400;
        int secondsPerSimplicityDay = 720;
        int simDay = (int) timeInSeconds / 720;
        timeInSeconds = timeInSeconds % 720;
        int simHour = (int) timeInSeconds / 30;
        timeInSeconds = timeInSeconds % 30;
        int simMins = (int) timeInSeconds * 2;
        System.out.println("Day " + simDay);
        System.out.println(simHour + " " + simMins);
    }

    public boolean stillAlive() {
        return mood > 0 && health > 0 && satiety > 0;
    }

    public boolean checkInventory(Objek o) {
        return inventory.checkItem(o);
    }

    public void showInventory() {
        inventory.showInventory();
    }

    public void addToInventory(Objek o) {
        inventory.addItem(o);
    }

    public void deleteFromInventory(Objek o) {
        inventory.removeItem(o);
    }

    public void installFurniture(Furniture f, Point location) {
        if (inventory.checkItem(f)) {
            currentRoom.addFurniture(f, location);
            inventory.removeItem(f);
        }
    }

    public static void main(String[] args) {

    }

}
