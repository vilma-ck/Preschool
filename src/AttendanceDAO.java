import java.util.List;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-11-30
 * Project: Preeschool
 * Copyright: MIT
 */
public interface AttendanceDAO {

    void setAttendance();
    void addAbsence(Child child);
    void addChildInAttendance(Child child);
    void addAttendanceTodayInList(List<Attendance> list);
    List<Attendance> getAttendanceToday();
    List<List<Attendance>> getMonths();

}
