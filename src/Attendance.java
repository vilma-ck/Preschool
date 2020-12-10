import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Sara Carlsson
 * Date: 30/11/2020
 * Time:20:40
 * Project: Preeschool
 * Copywright: MIT
 */
public class Attendance implements Serializable {

    private Child child;
    private LocalDate date;
    private Boolean isPresent;

    public Attendance(Child child) {
        this.child = child;
        this.date = LocalDate.now();
        this.isPresent = true;
    }

    public Child getChild() {
        return child;
    }

    public void setPresent(Boolean present) {
        isPresent = present;
    }

    public Boolean getPresent() {
        return isPresent;
    }

    public LocalDate getDate() {
        return date;
    }
}
