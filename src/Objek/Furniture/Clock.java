package src.Objek.Furniture;
import src.Main;

public class Clock extends Furniture
{
    public Clock()
    {
        super("Jam","Melihat Waktu",10);
    }

    public void aksi()
    {
        Long secondrn = System.currentTimeMillis() - Main.startMillis;
        Long Hour = secondrn/3600;
        secondrn = secondrn%3600;
        Long minute = secondrn/60;
        secondrn = secondrn%60;
        Long second = secondrn;
        System.out.println("The time right now is " + (Hour < 10 ? "0" : "") + Hour + ":" + (minute < 10 ? "0":"") + minute + ":" + (second < 10 ? "0" : "") + second );
    }
}