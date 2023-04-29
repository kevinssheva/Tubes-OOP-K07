package src.Inventory;

import java.util.*;

import src.Objek.Objek;
import src.Objek.Furniture.Clock;
import src.Objek.Furniture.Furniture;
import src.Objek.Furniture.MejaKursi;
import src.Objek.Furniture.Toilet;
import src.Objek.Furniture.Bed.SingleBed;
import src.Objek.Furniture.Stove.GasStove;

public class Inventory<T extends Objek> {
    private Map<T, Integer> item;

    public Inventory() {
        item = new HashMap<T, Integer>();

        // add default item
        addItem((T) new Toilet());
        addItem((T) new Toilet());
        addItem((T) new GasStove());
        addItem((T) new SingleBed());
        addItem((T) new MejaKursi());
        addItem((T) new Clock());
    }

    public void addItem(T t) {
        // add item to the inventory, if item already exist then increase the quantity
        for (T objek : item.keySet()) {
            if (objek.getName().equals(t.getName())) {
                item.put(objek, item.get(objek) + 1);
                return;
            }
        }
        item.put(t, 1);
    }

    public boolean checkItem(T t) {
        return item.containsKey(t);
    }

    public void showInventory() {
        for (T key : item.keySet()) {
            System.out.println(key.getName() + " : " + item.get(key));
        }
    }

    public void showFurnitureOnly() {
        for (T key : item.keySet()) {
            if (key instanceof Furniture) {
                System.out.println(key.getName() + " : " + item.get(key));
            }
        }
    }

    public boolean checkItemByName(String name) {
        for (T key : item.keySet()) {
            if (key.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public T getItemByName(String name) {
        for (T key : item.keySet()) {
            if (key.getName().equals(name)) {
                return key;
            }
        }
        return null;
    }

    public void removeItem(T t) {
        int i;
        
        try {
            i = item.get(t);
        } catch (NullPointerException e) {
            i=0;
        }
        
        if (i<=1) item.remove(t);
        else item.replace(t, i-1);
    }
}
