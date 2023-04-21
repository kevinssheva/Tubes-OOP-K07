package src.Objek.Furniture;

import src.Objek.Objek;
import src.Sim.*;
import src.Objek.*;

public abstract class Furniture extends Objek{

    private String action;

    public Furniture(String name,String action, Integer price)
    {
        super(name,price);
        this.action = action;
    }

    public String getAction()
    {
        return action;
    }
    


}
