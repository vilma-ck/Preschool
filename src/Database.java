import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sara Carlsson
 * Date: 30/11/2020
 * Time:20:44
 * Project: Preeschool
 * Copywright: MIT
 */
public class Database implements AttendanceDAO, Serializable  {

    private List<Child> childList = new LinkedList<>();
    private List<Caregiver> caregiverList = new LinkedList<>();
    private List<Educator> educatorList = new LinkedList<>();
    private List<Attendance> attendanceToday = new ArrayList<>();
    private List<List<Attendance>> attendanceList;

    public void addChild(Child c) {
        this.childList.add(c);
    }

    public void addCaregiver(Caregiver caregiver) {
        this.caregiverList.add(caregiver);
    }

    public void addEducator(Educator educator) {
        this.educatorList.add(educator);
    }

    public List<Child> getChildList() {
        return childList;
    }

    public List<Caregiver> getCaregiverList() {
        return caregiverList;
    }

    public List<Educator> getEducatorList() {
        return educatorList;
    }

    public List<List<Attendance>> getAttendanceList() {
        return attendanceList;
    }

    public void serialize(List list, String fileName) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(list);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deSerialize(List <? extends Person >list, String fileName) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            list = (List<Person>) in.readObject();
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setAttendance() {
        for(Child c : getChildList()) {
            this.attendanceToday.add(new Attendance(c));
        }
    }

    @Override
    public void addAbsence(Child child) {
        for(Attendance a: attendanceToday){
            if(a.getChild()==child) {
                a.setPresent(false);
                break;
            }
        }
    }

    @Override
    public void printAttendance() {
        String date = attendanceToday.get(0).getDate().toString();
        String present;
        System.out.println("Datum: " + date);
        for(Attendance a: attendanceToday){
            if(!a.getPresent())
                present = "Frånvarande";
            else
                present = "Närvarande";
            System.out.println(a.getChild().getFirstName() + " " + a.getChild().getLastName() +
                    " " + present );
        }
    }

    @Override
    public void printAbsent() {
        String date = attendanceToday.get(0).getDate().toString();
        System.out.println("Frånvarande " + date + ":");
        for(Attendance a: attendanceToday) {
            if (!a.getPresent())
                System.out.println(a.getChild().getFirstName() + " " + a.getChild().getLastName());
        }
    }

    @Override
    public void printPresent() {
        String date = attendanceToday.get(0).getDate().toString();
        System.out.println("Närvarande " + date + ":");
        for(Attendance a: attendanceToday) {
            if (a.getPresent())
                System.out.println(a.getChild().getFirstName() + " " + a.getChild().getLastName());
        }
    }
}
