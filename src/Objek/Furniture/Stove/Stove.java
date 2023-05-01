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

}
