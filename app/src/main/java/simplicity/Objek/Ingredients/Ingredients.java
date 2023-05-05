package simplicity.Objek.Ingredients;

import simplicity.Objek.*;

public class Ingredients extends Objek implements Edible, Buyable {
    private Integer hungerQuantity;
    private Integer price;

    public Ingredients(String name, Integer hungerQuantity, Integer price) {
        super(name);
        this.hungerQuantity = hungerQuantity;
        this.price = price;
    }

    public Integer getKekenyangan() {
        return hungerQuantity;
    }

    public Integer getPrice() {
        return price;
    }

}