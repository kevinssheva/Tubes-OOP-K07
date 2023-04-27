package src.Objek;

public class Objek implements Buyable {
    private String name;
    private Integer price;

    public Objek(String name, Integer price)
    {
        this.name = name;
        this.price = price;
    }

    public Objek(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return  name;
    }

    public Integer getPrice()
    {
        return price;
    }
}
