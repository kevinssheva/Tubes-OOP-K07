package src.Sim;

import java.awt.Point;
import src.Inventory.*;
import src.Job.*;
import src.Objek.Furniture.Furniture;
import src.Room.*;
import src.Home.*;

public class Sim {
    private String name;
    private Job job = null;
    private Integer money;
    private Integer mood;
    private Integer health;
    private String status;
    private Integer satiety;
    private Inventory inventory;
    private Room currentRoom;
    private Home home;

    public Sim(String name, Job job, Integer satiety, Integer money, Integer mood, Integer health, String status) {
        this.name = name;
        this.job = job;
        this.money = money;
        this.mood = mood;
        this.health = health;
        this.status = status;
        this.satiety = satiety;
        this.inventory = new Inventory();
    }

    public Sim(String name, Job job, Integer money, String status) {
        this.name = name;
        this.job = job;
        this.money = 80;
        this.mood = 80;
        this.health = 80;
        this.status = status;
        this.satiety = 80;
        this.inventory = new Inventory();
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
        if (time % 20000 != 0) return;
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

    public void kerja(Integer time){
        if(time%120 == 0)
        {
            long timeMillis = time * 1000;
            setStatus(String.format("Working as %s...", job.getName()));
            Thread thread = new Thread()
            {
                public void run()
                {
                    try
                    {
                        long startTime = System.currentTimeMillis();
                        long updateTime = 30000;
                        while((System.currentTimeMillis() - startTime) <= timeMillis){
                            updateTime -= (System.currentTimeMillis() - startTime);
                            if(updateTime <= 0){
                                setMood(getMood()-10);
                                setSatiety(getSatiety()-10);
                            }    
                        }
                        setMood(getMood()-10);
                        setSatiety(getSatiety()-10);
                        setMoney(getMoney()+(job.getDailyPay()*(time/120)));
                        setStatus("Idle");

                    }catch (Exception e)
                    {

                    }
                }
            };

            thread.start();
            System.out.println(status);
        }
    }

    public void berkunjung(Integer time, Home otherHome){
        if(time%30 == 0)
        {
            long timeMillis = time * 1000;
            setStatus("Visiting another house...");
            Thread thread = new Thread()
            {
                public void run()
                {
                    try
                    {
                        long startTime = System.currentTimeMillis();
                        // delay(home.distance(otherHome));                            //buat lama jalannya sim
                        // setStatus("In another house...");
                        long updateTime = 30000;
                        while((System.currentTimeMillis() - startTime) <= timeMillis){ //nanti tambah nungguin aksi sim di rumah lain
                            updateTime -= (System.currentTimeMillis() - startTime);
                            if(updateTime <= 0){
                                setMood(getMood()+10);
                                setSatiety(getSatiety()-10);
                            }    
                        }
                        setMood(getMood()+10);
                        setSatiety(getSatiety()-10);
                        // setStatus("Going back home...");
                        // delay(home.distance(otherHome));                            //buat lama jalannya sim
                        setStatus("Idle");

                    }catch (Exception e)
                    {

                    }
                }
            };

            thread.start();
            System.out.println(status);
        }
    }

    public boolean stillAlive() {
        return mood > 0 && health > 0 && satiety > 0;
    }

    public boolean checkInventory(Object o) {
        return inventory.checkItem(o);
    }
    
    public void showInventory() {
        inventory.showInventory();
    }

    public void addToInventory(Object o) {
        inventory.addItem(o);
    }

    public void deleteFromInventory(Object o) {
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
