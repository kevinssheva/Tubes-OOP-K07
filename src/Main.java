package src;
import src.Manager.*;

public class Main
{
    public static long timeNow = 0; // time is in second

    public static void runTheTime()
    {
        while(true)
        {
            try{
            Thread.sleep(1000);
            }catch(Exception e)
            {

            }
            timeNow = timeNow + 1;
        }
    }

    public static void main(String args[])
    {
        runTheTime();

    }
}