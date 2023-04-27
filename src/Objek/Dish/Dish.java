package src.Objek.Dish;

import src.Objek.Ingredients.*;
import java.util.*;
import src.Objek.*;

public class Dish extends Objek implements Edible {
    private List<Ingredients> listIngredients;
    private Integer hungerValue;

    public Dish(String name, List<Ingredients> listIngredients, Integer hungerValue) {
        super(name);
        this.listIngredients = listIngredients;
        this.hungerValue = hungerValue;
    }

    public List<Ingredients> getListIngredients() {
        return listIngredients;
    }

    public Integer getKekenyangan() {
        return hungerValue;
    }

}
