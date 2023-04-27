package src.Objek.Furniture.Stove;

import java.awt.Dimension;

import src.Objek.Dish.Dish;
import src.Objek.Furniture.Furniture;
import src.Objek.Ingredients.Ingredients;
import src.Sim.*;

public class Stove extends Furniture {
    public Stove(String name, Integer price, Dimension dimensi) {
        super(name, "Memasak", price, dimensi);
    }

    // Blm dibikin thread yh huehue (contoh deng mwehehe)
    public void aksi(Sim sim, Dish dish) throws Exception {
        for (Ingredients ing: dish.getListIngredients()) {
            if (!(sim.checkInventory(ing))) {
                throw new Exception("Dish ingredients not sufficient!");
            }
        }
        sim.setStatus("Memasak");
        Thread cookThread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(dish.getKekenyangan() * 1500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sim.setMood(10 + sim.getMood());
                System.out.println("Cooking done");
                for (Ingredients ing: dish.getListIngredients()) {
                    sim.deleteFromInventory(ing);
                }
                sim.addToInventory(dish);
                sim.setStatus("Idle");
            }
        };
        cookThread.start();
    }
}
