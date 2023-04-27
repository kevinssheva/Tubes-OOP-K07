package src.Objek.Furniture;

import src.Main;
import src.Sim.*;

public class Stove extends Furniture {
    public Stove(String name, Integer price) {
        super(name, "Memasak", price);
    }
    
    // Blm dibikin thread yh huehue (contoh deng mwehehe)
    public void aksi(Sim s, Dish d) throws Exception
    {
        for (Ingredients ing: d.getListIngredients()) {
            if (!(s.checkInventory(ing))) {
                throw new Exception("Dish ingredients not sufficient!");
            }
        }
        s.setStatus("Cooking....");
        for (Ingredients ing: d.getListIngredients()) {
            s.deleteFromInventory(ing);
        }
        s.addToInventory(d);
        s.setStatus("Idle");
    }
}
