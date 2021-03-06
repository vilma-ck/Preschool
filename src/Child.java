import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-11-30
 * Project: Preeschool
 * Copyright: MIT
 */
public class Child extends Person {

    private static int childrenNr = 0;
    private int id;

    List<Caregiver> caregivers = new ArrayList<>();
    List<CaringTime> caringTimes = new ArrayList<>();

    public Child(String firstName, String lastName, String personalNumber) {
        super(firstName, lastName, personalNumber);
        id = childrenNr ++;
    }

    public void addCaregiver(Caregiver c){
        caregivers.add(c);
    }

    public void addCaringTime(String inputDate, String inputStartTime, String inputStopTime){
        LocalTime start = LocalTime.parse(inputStartTime);
        LocalTime stop = LocalTime.parse(inputStopTime);
        caringTimes.add(new CaringTime(inputDate, start, stop));
    }

    public List<Caregiver> getCaregivers() {
        return caregivers;
    }

    public List<CaringTime> getCaringTimes(){
        return caringTimes;
    }

    public CaringTime getCaringTime(int indexInWeek){
        return caringTimes.get(indexInWeek);
    }
}
