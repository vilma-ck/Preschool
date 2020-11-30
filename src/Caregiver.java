import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-11-30
 * Project: Preeschool
 * Copyright: MIT
 */
public class Caregiver extends Person implements IContactInformation, IHandlingAbsence{

    private String eMailAddress;
    private String phoneNumber;
    private String postAddress;
    List<Child> children;

    public Caregiver(String firstName, String lastName, String personalNumber) {
        super(firstName, lastName, personalNumber);
        children = new ArrayList<Child>();
    }

    public void addChildren(Child child){
        children.add(child);
    }

    public void registerCaringTime(Child child, LocalTime start, LocalTime stop){

    }

    @Override
    public void addAbsence(Child child) {

    }

    @Override
    public String getEMailAddress() {
        return eMailAddress;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getPostAddress() {
        return postAddress;
    }
}
