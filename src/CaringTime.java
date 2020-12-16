import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;

public class CaringTime implements Serializable {
    private LocalTime start;
    private LocalTime stop;
    private String weekday;

    public CaringTime(String weekday, LocalTime start, LocalTime stop){
        this.weekday = weekday;
        this.start = start;
        this.stop = stop;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getStop() {
        return stop;
    }

    public String getDay(){
        return weekday;
    }

    public Duration getDuration(){
        return Duration.between(start, stop);
    }

}