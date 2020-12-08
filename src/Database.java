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



public class Database implements AttendanceDAO, Serializable, PersonDAO  {


    private List<Child> childList = new LinkedList<>();
    private List<Caregiver> caregiverList = new LinkedList<>();
    private List<Educator> educatorList = new LinkedList<>();
    private List<Attendance> attendanceToday = new ArrayList<>();
    private List<List<Attendance>> attendanceList;

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
