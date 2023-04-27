package src.Objek.Furniture;

import java.awt.Dimension;
import src.Objek.*;

public abstract class Furniture extends Objek implements Buyable {

    private Integer price;
    private String action;
    private Dimension dimensi;

    public Furniture(String name, String action, Integer price, Dimension dimensi) {
        super(name);
        this.price = price;
        this.action = action;
        this.dimensi = dimensi;
    }

    public Integer getPrice() {
        return price;
    }

    public Dimension getDimensi() {
        return dimensi;
    }

    public String getAction() {
        return action;
    }
}
