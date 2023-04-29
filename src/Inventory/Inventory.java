package src.Inventory;

import java.util.*;

import src.Objek.Furniture.Clock;
import src.Objek.Furniture.MejaKursi;
import src.Objek.Furniture.Toilet;
import src.Objek.Furniture.Bed.SingleBed;
import src.Objek.Furniture.Stove.GasStove;

public class Inventory {
    private Map<Object, Integer> item;

    public Inventory() {
        item = new HashMap<Object, Integer>();
        
        SingleBed kasurSingle = new SingleBed();
        Toilet toilet = new Toilet();
        GasStove komporGas = new GasStove();
        Clock jam = new Clock();
        MejaKursi mejaKursiMakan = new MejaKursi();
        addItem(kasurSingle);
        addItem(toilet);
        addItem(komporGas);
        addItem(jam);
        addItem(mejaKursiMakan);
    }

    public void addItem(Object object) {
        int cnt;
        try {
            cnt = item.get(object);
        } catch (NullPointerException e) {
            cnt = 0;
        }

        item.put(object, cnt + 1);
    }

    public boolean checkItem(Object object) {
        return item.containsKey(object);
    }

    public void showInventory() {
        for (Object key : item.keySet()) {
            System.out.println(key.getClass() + " : " + item.get(key));
        }
    }

    public void removeItem(Object o) {
        int i;
        
        try {
            i = item.get(o);
        } catch (NullPointerException e) {
            i=0;
        }
        
        if (i<=1) item.remove(o);
        else item.replace(o, i-1);
    }

}
