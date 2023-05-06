package simplicity.Inventory;

import java.util.*;

import simplicity.Objek.Objek;
import simplicity.Objek.Furniture.Furniture;
import simplicity.Objek.Dish.*;
import simplicity.Objek.Ingredients.*;

public class Inventory {
    private Map<Objek, Integer> item;

    public Inventory() {
        item = new HashMap<Objek, Integer>();
    }

    public Map<Objek, Integer> getItems() {
        return item;
    }

    public void addItem(Objek t) {
        // add item to the inventory, if item already exist then increase the quantity
        for (Objek objek : item.keySet()) {
            if (objek.getName().equals(t.getName())) {
                item.put(objek, item.get(objek) + 1);
                return;
            }
        }
        item.put(t, 1);
    }

    public void addItem(Objek t, int quantity) {
        item.put(t, quantity);        
    }

    public boolean checkItem(Objek t) {
        return item.containsKey(t);
    }

    public void showInventory() {
        for (Objek key : item.keySet()) {
            System.out.println(key.getName() + " : " + item.get(key));
        }
    }

    public void showFurnitureOnly() {
        for (Objek key : item.keySet()) {
            if (key instanceof Furniture) {
                System.out.println(key.getName() + " : " + item.get(key));
            }
        }
    }

    public void showEdibleOnly() {
        for (Objek key : item.keySet()) {
            if (key instanceof Ingredients || key instanceof Dish) {
                System.out.println(key.getName() + " : " + item.get(key));
            }
        }
    }

    public boolean checkItemByName(String name) {
        for (Objek key : item.keySet()) {
            if (key.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Objek getItemByName(String name) {
        for (Objek key : item.keySet()) {
            if (key.getName().equals(name)) {
                return key;
            }
        }
        return null;
    }

    public void removeItem(Objek t) {
        int i;

        try {
            i = item.get(t);
        } catch (NullPointerException e) {
            i = 0;
        }

        if (i <= 1)
            item.remove(t);
        else
            item.replace(t, i - 1);
    }
}
