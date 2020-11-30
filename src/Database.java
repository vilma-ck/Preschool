import java.util.List;

/**
 * Created by Sara Carlsson
 * Date: 30/11/2020
 * Time:20:44
 * Project: Preeschool
 * Copywright: MIT
 */
public class Database {

    private List<Child> childList;
    private List<Caregiver> caregiverList;
    private List<Educator> educatorList;
    private List<List<Attendance>> attendanceList;

    public void addChild(Child c){
        this.childList.add(c);
    }
}
