package src.Objek.Furniture;
import src.Sim.*;
import src.Main;

public class Toilet extends Furniture
{
    public Toilet()
    {
        super("Toilet","Buang Air",50);
    }

    public void Aksi(Sim a)
    {
        Thread temp = new Thread()
        {
            public void run()
            {
                a.setStatus("Buang Air");
                while((System.currentTimeMillis() - Main.startMillis)/1000 <= 10 )
                {

                }
                a.setSatiety(a.getSatiety()-20);
                a.setMood(a.getMood()  + 10);
            }
        };

        temp.start();
    }

}