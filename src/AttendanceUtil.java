import java.util.ArrayList;
import java.util.List;

public class AttendanceUtil {
    private List<Attendance> attendanceToday = new ArrayList<>();

    public AttendanceUtil(List<Child> childList) {
        for(Child c : childList)
            this.attendanceToday.add(new Attendance(c));
    }

    public void addAbsence(Child child){
        for(Attendance a: attendanceToday){
            if(a.getChild()==child) {
                a.setPresent(false);
                break;
            }
        }
    }

    public void printAttendance(){
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

    public void printAbsent(){
        String date = attendanceToday.get(0).getDate().toString();
        System.out.println("Frånvarande " + date + ":");
        for(Attendance a: attendanceToday) {
            if (!a.getPresent())
                System.out.println(a.getChild().getFirstName() + " " + a.getChild().getLastName());
        }
    }

    public void printPresent(){
        String date = attendanceToday.get(0).getDate().toString();
        System.out.println("Närvarande " + date + ":");
        for(Attendance a: attendanceToday) {
            if (a.getPresent())
                System.out.println(a.getChild().getFirstName() + " " + a.getChild().getLastName());
        }
    }

    public static void main(String[] args) {
        Child c = new Child("Pelle" , "Palm", "150318");
        Child c2 = new Child("Hella" , "Sten", "150318");
        Child c3 = new Child("Sara" , "Carlsson", "150318");

        List<Child> childList = new ArrayList<>();
        childList.add(c);
        childList.add(c2);
        childList.add(c3);

        AttendanceUtil u = new AttendanceUtil(childList);

        u.printAttendance();
        u.printAbsent();
        u.printPresent();
        System.out.println();

        u.addAbsence(c2);

        u.printAttendance();
        u.printPresent();
        u.printAbsent();

    }
}
