package src.Objek.Furniture;

import src.Objek.Objek;
import src.Sim.*;

import java.awt.Dimension;

import src.Objek.*;

public abstract class Furniture extends Objek{

    private String action;
    private Dimension dimensi;

    public Furniture(String name,String action, Integer price)
    {
        super(name,price);
        this.action = action;
    }

    public Dimension getDimensi()
    {
        return dimensi;
    }

    public String getAction()
    {
        return action;
    }
}
