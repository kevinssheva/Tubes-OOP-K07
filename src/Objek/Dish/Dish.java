package src.Objek.Dish;

import src.Objek.*;

public class Dish extends Objek implements Edible {
    private Integer hungerValue;

    public Dish(String name, Integer hungerValue) {
        super(name);
        this.hungerValue = hungerValue;
    }


    public Integer getKekenyangan() {
        return hungerValue;
    }

}
