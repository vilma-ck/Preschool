import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-12-15
 * Project: Preschool_own_project
 * Copyright: MIT
 */
public class AdminTester {

    public static void main(String[] args) {
        Database db = new Database();
        AttendanceDAO attendanceDAO = db;

        List<List<Attendance>> attendences = attendanceDAO.getAttendanceList();

        for(List<Attendance> list: attendences){
            System.out.println("next list");
            for (Attendance attendance : list){
                System.out.println(attendance.getChild().getFirstName() + " " + attendance.getDate());
            }
        }


    }



}
