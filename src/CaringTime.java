import java.time.LocalDate;
import java.time.LocalDateTime;

public class CaringTime {
    LocalDateTime start;
    LocalDateTime stop;


    public CaringTime(LocalDateTime start, LocalDateTime stop){
        this.start = start;
        this.stop = stop;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getStop() {
        return stop;
    }

    public LocalDate getDay(){
        return start.toLocalDate();
    }
}