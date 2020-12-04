/**
 * Created by Sara Carlsson
 * Date: 30/11/2020
 * Time:14:07
 * Project: Preeschool
 * Copywright: MIT
 */
public class Person {

    private String firstName;
    private String lastName;
    private String personalNumber;

    Person(String firstName, String lastName, String personalNumber){

        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
