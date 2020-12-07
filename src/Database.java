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
public class Database implements Serializable, DatabaseDAO {

    private List<Child> childList = new ArrayList<>();
    private List<Caregiver> caregiverList = new ArrayList<>();
    private List<Educator> educatorList = new ArrayList<>();

    AttendanceHandling attendanceHandling;
    PersonHandling personHandling;

    public void addChild(Child c) {
        this.childList.add(c);
    }

    @Override
    public void deleteChild(Child child) {
        this.childList.remove(child);
    }

    public void addCaregiver(Caregiver caregiver) {
        this.caregiverList.add(caregiver);
    }

    @Override
    public void deleteCaregiver(Caregiver caregiver) {
        this.caregiverList.remove(caregiver);
    }

    public void addEducator(Educator educator) {
        this.educatorList.add(educator);
    }

    @Override
    public void deleteEducator(Educator educator) {
        this.educatorList.remove(educator);
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


    public void serialize(List list, String fileName) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(list);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List deSerialize(List list, String fileName) {
        List<Child> childList = null;
        List<Caregiver> caregiverList = null;
        List<Educator> educatorList = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            if (list == childList) {
                list = (List<Child>) in.readObject();
                in.close();
            }
            else if (list == caregiverList){
                list = (List<Caregiver>) in.readObject();
                in.close();
            }
            else if (list == educatorList){
                list = (List<Educator>) in.readObject();
                in.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }




}
