import java.io.Serializable;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-12-16
 * Project: Preschool_own_project
 * Copyright: MIT
 */
public class AttendanceHistoryMonth implements Serializable {

    private List<List<Attendance>> attendanceByMonth = new ArrayList<>();
    private Month month;

    public List<List<Attendance>> getAttendanceByMonth() {
        return attendanceByMonth;
    }

    public Month getMonth() {
        return month;
    }

}
