package src.Sim;
import src.Inventory.*;
import src.Job.*;
import src.Objek.Furniture.Furniture;

import java.awt.Point;

import src.*;
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
    private Room currentRoom; //belum ada set get nya
    private Home home; //belum ada set get nya

    public Sim(String name,Job job,Integer satiety, Integer money, Integer mood, Integer health, String status)
    {
        this.name = name;
        this.job = job;
        this.money = money;
        this.mood = mood;
        this.health = health;
        this.status = status;
        this.satiety = satiety;
        this.inventory = new Inventory();
    }

    public Sim(String name,Job job,Integer money,String status)
    {
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

    public void setHealth(Integer health) {
        this.health = Math.max(100,health);
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setMoney(Integer money) {
        this.money = Math.max(100,money);
    }

    public void setMood(Integer mood) {
        this.mood = Math.max(100,mood);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void exercise(Integer time)
    {
        if(time%20000 == 0)
        {
            setStatus("Exercising...");
            Thread thread = new Thread()
            {
                public void run()
                {
                    try
                    {
                        while((System.currentTimeMillis() - Main.startMillis) <= time)
                        {
                        }

                        setStatus("Idle");

                    }catch (Exception e)
                    {

                    }
                }
            };

            thread.start();
            System.out.println(status);
            System.out.println(status);
        }

    }

    public boolean stillAlive()
    {
        return mood > 0 && health > 0 && satiety > 0;
    }

    public boolean checkInventory(Object o)
    {
        return  inventory.checkItem(o);
    }

    public void addToInventory(Object o)
    {
        inventory.addItem(o);
    }

    public void deleteFromInventory(Object o)
    {
        inventory.removeItem(o);
    }
    
    public void installFurniture(Furniture f, Point location)
    {
        //check inventory
        if (inventory.checkItem(f)) {
            currentRoom.addFurniture(f, location);
            // ngurangin dulu di inventory
            return;
        }
        return;        
    }

    public void work(Integer time)
    {
        if(time%120000 == 0)
        {
            setStatus("Working as a " + job.getName());
            Thread thread = new Thread()
            {
                public void run()
                {
                    try
                    {
                        while((System.currentTimeMillis() - Main.startMillis) <= time)
                        {
                            if((System.currentTimeMillis()-Main.startMillis)%240000 == 0)
                            {
                                money = getMoney() + job.getDailyPay();
                            }
                        }

                        setStatus("Idle");
                    }catch (Exception e)
                    {

                    }
                }
            };

            thread.start();
        }
    }


    public static void main(String[] args)
    {

    }

}
