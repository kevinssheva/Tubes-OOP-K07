package src.Job;

public class Job {
    private String name;
    private Integer dailyPay;

    public Job(String name, Integer dailyPay)
    {
        this.name = name;
        this.dailyPay = dailyPay;
    }

    public String getName()
    {
        return name;
    }

    public Integer getDailyPay() {
        return dailyPay;
    }
}
