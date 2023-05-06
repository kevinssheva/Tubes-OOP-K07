package simplicity.Objek.Furniture.Bed;

import java.awt.Dimension;
import simplicity.Objek.Furniture.Furniture;
import simplicity.Sim.*;

public abstract class Bed extends Furniture {

    public Bed(String name, Integer price, Dimension dimensi) {
        super(name, "Tidur", price, dimensi);
    }
}