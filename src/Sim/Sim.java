package src.Sim;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import src.Inventory.*;
import src.Job.*;
import src.Objek.Objek;
import src.Objek.Furniture.Furniture;
import src.Room.*;
import src.Main;
import src.Home.*;
import src.Manager.*;
import src.Objek.Dish.*;

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
    private Integer workToday = 0; // catet waktu kerja yg udah dilakuin hari itu
    private Integer currentWorkTotal = 0; // catet waktu kerja total buat ganti job
    private Map<String, Long> actionList = new HashMap<String, Long>();
    private Integer traveledTime = 0;
    
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

    public Integer getTraveledTime(){
        return traveledTime;
    }
    public void setTraveledTime(Integer traveledTime){
        this.traveledTime = traveledTime;
    }
    public Integer getSatiety() {
        return satiety;
    }

    public void setSatiety(Integer satiety) {
        this.satiety = Math.min(satiety, 100);
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

    public Home getCurrentHome() {
        return currentHome;
    }

    public Inventory<Objek> getInventory() {
        return inventory;
    }

    public Integer getWorkToday() {
        return workToday;
    }

    public Integer getCurrentWorkTotal() {
        return currentWorkTotal;
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

    public void setCurrentHome(Home h) {
        this.currentHome = h;
    }

    public void setWorkToday(Integer workToday) {
        this.workToday = workToday;
    }

    public void setCurrentWorkTotal(Integer currentWorkTotal) {
        this.currentWorkTotal = currentWorkTotal;
    }

    public boolean checkAction(String action) {
        return actionList.containsKey(action);
    }

    public void addAction(String action, Long time) {
        actionList.put(action, time);
    }

    public void removeAction(String action) {
        actionList.remove(action);
    }

    public void eat(){
        setStatus("Eating");
        Thread eatThread = new Thread(){
            public void run(){
                long finalTime = Main.timeNow + 30;
                while (Main.timeNow < finalTime) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        eatThread.start();
        try{
            eatThread.join();
            setStatus("Idle");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void watchTV(Integer time){
        setStatus("Watching TV");
        Thread watchTVThread = new Thread(){
            public void run(){
                long finalTime = Main.timeNow + time;
                while(Main.timeNow < finalTime){
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        watchTVThread.start();
        try{
            watchTVThread.join();
            setMood(mood + (time/30)*20);
            System.out.println("Watch TV done");
            setStatus("Idle");
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void stargaze(boolean isNight){
        setStatus("Stargazing");
        Thread stargazeThread = new Thread(){
            public void run(){
                long finalTime = Main.timeNow + 30;
                while(Main.timeNow < finalTime){
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        stargazeThread.start();
        try{
            stargazeThread.join();
            if(isNight){
                setMood(mood+70);
            }else{
                // since it's noon, your sim are only wasting his or her health.
            }
            setHealth(health-30);
            setStatus("Idle");
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
    public void playGame(Integer time){
        setStatus("Playing Game");
        Thread playGameThread = new Thread(){
            @Override
            public void run(){
                long finalTime = Main.timeNow + time;
                while (Main.timeNow < finalTime){
                    try{
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        playGameThread.start();
        try{
            playGameThread.join();
            setMood(mood + ((time/30)*20));
            setSatiety(satiety - ((time/30)*5));
            System.out.println("Play Game done");
            setStatus("Idle");
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    
    public void sholat() {
        setStatus("Sholat");
        Thread sholatThread = new Thread() {
            public void run() {
                long finalTime = Main.timeNow + 10;
                while (Main.timeNow < finalTime) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        sholatThread.start();
        try {
            sholatThread.join();
            setMood(mood + 10);
            System.out.println("Sholat done");
            setStatus("Idle");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void mandi() {
        setStatus("Taking a shower");
        Thread mandiThread = new Thread() {
            public void run() {
                long finalTime = Main.timeNow + 10;
                while (Main.timeNow < finalTime) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        mandiThread.start();
        try {
            mandiThread.join();
            setMood(mood + 5);
            setHealth(health + 5);
            System.out.println("Shower done");
            setStatus("Idle");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void poop(){
        setStatus("Using the toilet");
        Thread poopThread = new Thread(){
            public void run(){
                long finalTime = Main.timeNow + 10;
                while(Main.timeNow < finalTime){
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        poopThread.start();
        try{
            poopThread.join();
            setSatiety(satiety - 20);
            setMood(mood + 10);
            System.out.println("Using toilet done");
            setStatus("Idle");
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void exercise(Integer time) {
        if (time % 20 != 0)
            return;
        setStatus("Exercising");

        Thread exerciseThread = new Thread() {
            public void run() {
                long finalTime = Main.timeNow + time;
                while (Main.timeNow < finalTime) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        exerciseThread.start();

        try {
            exerciseThread.join();
            setHealth(health + (time/20)*5);
            setSatiety(satiety - (time/20)*5);
            setMood(mood + (time/20)*10);
            System.out.println("Exercise done");
            setStatus("Idle");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void visit(long time){
        setStatus("Visiting to other's home...");
        Thread visitThread = new Thread(){
            public void run(){
                long finalTime = Main.timeNow + time;
                while(Main.timeNow < finalTime){
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        System.out.println("Error");
                    }
                    traveledTime += 1;
                    if(traveledTime == 30){
                        setMood(mood+10);
                        setSatiety(satiety-10);
                        traveledTime = 0;
                    }
                }
            }
        };
        visitThread.start();
        try{
            visitThread.join();
            setStatus("Idle");
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void sleep(Integer time) {
        if (time % 240 == 0) {
            setStatus("Sleeping...");
            Thread sleepThread = new Thread() {
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
            try {
                sleepThread.join();
                setMood(getMood() + (time / 240) * 30);
                setHealth(getHealth() + (time / 240) * 20);
                System.out.println("Sleeping done");
                setStatus("Idle");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void kerja(Integer time) {
        setStatus(String.format("Working as %s...", job.getName()));
        System.out.println("Work session started for " + time + " seconds");
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
            setWorkToday(getWorkToday() + (time / 60));
            setCurrentWorkTotal((time / 60) + getCurrentWorkTotal());
            System.out.println("Work session done");
            if (getWorkToday() == 4) {
                setMoney(getMoney() + job.getDailyPay());
                System.out.println("Work finished for current day");
            }
            setStatus("Idle");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void cook(String dishName) {
        long time = 0;
        Dish dish = null;
        switch (dishName) {
            case "Chicken Rice":
                inventory.removeItem(inventory.getItemByName("Chicken"));
                inventory.removeItem(inventory.getItemByName("Rice"));
                dish = new Dish("Chicken Rice", 16);
                break;
            case "Curry Rice":
                inventory.removeItem(inventory.getItemByName("Potato"));
                inventory.removeItem(inventory.getItemByName("Rice"));
                inventory.removeItem(inventory.getItemByName("Carrot"));
                inventory.removeItem(inventory.getItemByName("Beef"));
                dish = new Dish("Curry Rice", 30);
                break;
            case "Soy Milk":
                inventory.removeItem(inventory.getItemByName("Peanut"));
                inventory.removeItem(inventory.getItemByName("Milk"));
                dish = new Dish("Soy Milk", 5);
                break;
            case "Stir-fried Vegetables":
                inventory.removeItem(inventory.getItemByName("Carrot"));
                inventory.removeItem(inventory.getItemByName("Spinach"));
                dish = new Dish("Stir-fried Vegetables", 5);
                break;
            case "Beef Steak":
                inventory.removeItem(inventory.getItemByName("Potato"));
                inventory.removeItem(inventory.getItemByName("Beef"));
                dish = new Dish("Beef Steak", 22);
                break;
        }
        time = Math.round(1.5 * dish.getKekenyangan());
        final long timeThread = time;
        setStatus("Cooking " + dish.getName() + "...");
        Thread cookThread = new Thread() {
            public void run() {
                long finalTime = Main.timeNow + timeThread;
                while (Main.timeNow < finalTime) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Error");
                    }
                }
            }
        };

        cookThread.start();
        try {
            cookThread.join();
            inventory.addItem(dish);
            System.out.println("Your sim has just finished cooking " + dish.getName());
            setStatus("Idle");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void showTime() {
        System.out.println("Current time: " + Main.timeNow);
        System.out.println("Your ongoing action : ");
        for (Map.Entry<String, Long> entry : actionList.entrySet()) {
            Long timeLeft = entry.getValue() - Main.timeNow;
            if(timeLeft > 0 ) System.out.println(entry.getKey() + " : " + timeLeft + " seconds left");
            else System.out.println(entry.getKey() + " has been arrived");
        }
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
                while (true) {
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
                                    if (status == "Buang Air")
                                        havePooped = true;
                                }
                                if (!(havePooped)) {
                                    mood -= 5;
                                    health -= 5;
                                }
                            }
                        };
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
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (status == "Tidur" || status == "Sleeping...") {
                        lastSleep = Main.timeNow;
                    }
                    if (lastSleep + 600 <= Main.timeNow) {
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
