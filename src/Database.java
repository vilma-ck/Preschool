import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sara Carlsson
 * Date: 30/11/2020
 * Time:20:44
 * Project: Preeschool
 * Copywright: MIT
 */
public class Database implements Serializable, PersonDAO {

    private List<Child> childList = new LinkedList<>();
    private List<Caregiver> caregiverList = new LinkedList<>();
    private List<Educator> educatorList = new LinkedList<>();
    private List<List<Attendance>> attendanceList;

    public Database() throws IOException {
    }

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

    public void deSerialize(List list, String fileName) {
        list = new LinkedList<>();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            list = (List<Person>) in.readObject();
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getContactInformation(IContactInformation person) {
        StringBuilder sb = new StringBuilder();
        sb.append("E-mejladress: " + person.getEmailAddress() + '\n');
        sb.append("Telefonnummer: " + person.getPhoneNumber() + '\n');
        sb.append("Postadress: " + person.getPostAddress());

        return sb.toString();
    }

    @Override
    public Child getChild(String name) {
        for(Child c : childList){
            if(c.getFirstName().equalsIgnoreCase(name) || c.getLastName().equalsIgnoreCase(name)){
                return c;
            }
        }
        return null;
    }

    public Caregiver getCaregiver(String name){
        for (Caregiver c: caregiverList){
            if(c.getFirstName().equalsIgnoreCase(name) || c.getLastName().equalsIgnoreCase(name)){
                return c;
            }
        }
        return null;
    }

    @Override
    public Educator getEducator(String name) {
        for(Educator e: educatorList){
            if(e.getFirstName().equalsIgnoreCase(name) || e.getLastName().equalsIgnoreCase(name)){
                return e;
            }
        }
        return null;
    }

}
