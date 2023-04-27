package src.Job;

public class Job {
    private String name;
    private Integer dailyPay;

    public Job(String name) {
        this.name = name;
        switch (name) {
            case "Badut Sulap":
                dailyPay = 15;
                break;
            case "Koki":
                dailyPay = 30;
                break;
            case "Polisi":
                dailyPay = 35;
                break;
            case "Programmer":
                dailyPay = 45;
                break;
            case "Dokter":
                dailyPay = 50;
                break;
            default:
                break;
        }
    }

    public Job(String name, Integer dailyPay) {
        this.name = name;
        this.dailyPay = dailyPay;
    }

    public String getName() {
        return name;
    }

    public Integer getDailyPay() {
        return dailyPay;
    }
}
