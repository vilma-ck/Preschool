import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-12-15
 * Project: Preschool_own_project
 * Copyright: MIT
 */
public class AdminTester {

    public static void main(String[] args) {

        Duration d = Duration.between(LocalTime.parse("08:30"), LocalTime.parse("15:00"));
        long hours = d.toHours();
        long mins = d.minusHours(hours).toMinutes();

        System.out.println(hours + " " + mins);

        Database db = new Database();
        AttendanceDAO attendanceDAO = db;

        List<List<Attendance>> attendences = attendanceDAO.getMonths();

        for(List<Attendance> list: attendences){
            System.out.println("next list");
            for (Attendance attendance : list){
                System.out.println(attendance.getChild().getFirstName() + " " + attendance.getDate());
            }
        }


    }



}
