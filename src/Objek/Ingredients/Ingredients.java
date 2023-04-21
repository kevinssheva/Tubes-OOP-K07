package src.Objek.Ingredients;
import src.Objek.*;

public class Ingredients extends Objek implements Edible
{
    Integer hungerQuantity;
    public Ingredients(String name, Integer hungerQuantity, Integer price)
    {
        super(name,price);
        this.hungerQuantity = hungerQuantity;
    }

    public Integer getKekenyangan()
    {
        return getKekenyangan();
    }

}