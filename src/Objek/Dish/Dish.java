package src.Objek.Dish;
import src.Objek.Ingredients.*;
import java.util.*;
import src.Objek.*;

public class Dish extends Objek implements Edible
{
    List<Ingredients> listIngredients;
    Integer hungerValue;
    public Dish(String name,List<Ingredients> listIngredients,Integer hungerFValue)
    {
        super(name,null);
        this.listIngredients = listIngredients;
        this.hungerValue = hungerValue;
    }

    public List<Ingredients> getListIngredients()
    {
        return listIngredients;
    }

    public Integer getKekenyangan()
    {
        return hungerValue;
    }

}