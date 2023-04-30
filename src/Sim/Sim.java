package src.Sim;

import java.awt.Point;
import src.Inventory.*;
import src.Job.*;
import src.Objek.Objek;
import src.Objek.Furniture.Furniture;
import src.Room.*;
import src.Main;
import src.Home.*;
import src.Manager.*;

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
    private Integer workToday = 0;  // catet waktu kerja yg udah dilakuin hari itu

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
        eatCheck();
        sleepCheck();
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
        eatCheck();
        sleepCheck();
    }

    public Integer getSatiety() {
        return satiety;
    }

    public void setSatiety(Integer satiety) {
        this.satiety = Math.min(satiety,100);
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

    public Integer getWorkToday() {
        return workToday;
    }

    public void setHealth(Integer health) {
        this.health = Math.min(100, health);
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setMoney(Integer money) {
        this.money = money;
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
    
    public void setWorkToday(Integer workToday) {
        this.workToday = workToday;
    }

    public void exercise(Integer time) {
        if (time % 20 != 0)
            return;
        setStatus("Exercise");
        
        Thread exerciseThread = new Thread() {
            public void run() {
                long finalTime = Main.timeNow + time;
                while(Main.timeNow < finalTime){
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        exerciseThread.start();

        try{
            exerciseThread.join();
            setHealth(health + 10);
            setSatiety(satiety + 10);
            System.out.println("Exercise done");
            setStatus("Idle");
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void sleep(Integer time){
        if(time % 240 == 0){
            setStatus("Sleeping...");
            Thread sleepThread = new Thread(){
                public void run() {
                    long finalTime = Main.timeNow + time;
                    while (Main.timeNow < finalTime) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            System.out.println("Error");
                        }
                    }
                }
            };
            sleepThread.start();
            try{
                sleepThread.join();
                setMood(getMood()+ (time/240)*30);
                setHealth(getHealth() + (time/240)*20);
                System.out.println("Sleeping done");
                setStatus("Idle");
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }


    public void kerja(Integer time) {
        if (time % 120 == 0) {
            setStatus(String.format("Working as %s...", job.getName()));
            Thread workThread = new Thread() {
                public void run() {
                    long finalTime = Main.timeNow + time;
                    while (Main.timeNow < finalTime) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            System.out.println("Error");
                        }
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
                setWorkToday(getWorkToday() + (time / 120));
                System.out.println("Work session done");
                if(getWorkToday() == 4){
                    setMoney(getMoney() + job.getDailyPay());
                    System.out.println("Work finished for current day");
                }
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
    
    public void eatCheck() {
        Thread t = new Thread() {
            public void run() {
                boolean eating = false;
                Thread th = new Thread() {
                    public void run() {
                        long nextPoop = Main.timeNow + 240;
                        boolean havePooped = false;
                        while (Main.timeNow < nextPoop) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (status == "Buang Air") havePooped = true;
                        }
                        if (!(havePooped)) {
                            mood -= 5;
                            health -= 5;
                        }
                    }
                };
                while (1) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (status == "Makan") {
                        eating = true;
                    }
                    if (eating && status == "Idle") {
                        eating = false;
                        th.start();
                    }
                }
            }
        };
        t.start();
    }
    
    private void sleepCheck() {
        Thread t = new Thread() {
            public void run() {
                long targetTime;
                long lastSleep = Main.timeNow;
                while (1) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (status == "Tidur" || status == "Sleeping...") {
                        lastSleep = Main.timeNow;
                    }
                    if (lastSleep+600 <= Main.timeNow) {
                        lastSleep = Main.timeNow;
                        mood -= 5;
                        health -= 5;
                    }
                }
            }
        };
        t.start();
    }

    public static void main(String[] args) {

    }

}
