/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-11-30
 * Project: Preeschool
 * Copyright: MIT
 */
public interface AttendanceDAO {

    void setAttendance();
    void addAbsence(Child child);
    void printAttendance();
    void printAbsent();
    void printPresent();

}
