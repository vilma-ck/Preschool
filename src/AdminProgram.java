import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-12-15
 * Project: Preschool_own_project
 * Copyright: MIT
 */
public class AdminProgram {



    private AttendanceDAO attendanceDAO;
    private PersonDAO personDAO;

    private Scanner scan;

    private States state;

    public AdminProgram(AttendanceDAO attendanceDAO, PersonDAO personDAO, Scanner scan, States state){
        this.attendanceDAO = attendanceDAO;
        this.personDAO = personDAO;
        this.scan = scan;
        this.state = state;
    }

    public void adminLoop(int input) throws InterruptedException {
        String userName = "admin001";

        state = States.GIVE_USERNAME;
        System.out.println(state.getMessage(null));
        String name = scan.next();

        while (!name.equals(userName)) {
            System.out.println("Var god försök igen: ");
            name = scan.next();
        }

        input = showMeny();



        while(true){
            if(input == 1){
                int day = chooseDayToView();
                showAttendanceOnSpecificDay(day);
                input = showMeny();
            } else if(input == 2){
                int childIndex = chooseChild();
                Child child = personDAO.getChildList().get(childIndex);
                List<List<String>> attendanceCompilation = viewChildCompleteAttendance(child);
                System.out.println("Vill du sammanställa en närvarorapport " + child.getFirstName() + "? \n1. ja, 2. nej");
                input = scan.nextInt();
                if(input==1){
                    createAttendanceReport(attendanceCompilation);
                }
                input = showMeny();
            } else if(input == 3){
                Thread.sleep(1000);
                state = States.LOG_OUT;
                System.out.println(state.getMessage(null));
                break;
            }
            else {
                System.out.println("Okänt kommando, var god försök igen.");
            }
        }
    }

    private void createAttendanceReport(List<List<String>> attendanceCompilation) {
        System.out.println(attendanceCompilation.size());
        System.out.println(attendanceCompilation.get(0));
        String fileName = "src/"+ attendanceCompilation.get(0).get(0) + "_" + attendanceCompilation.get(0).get(1) +  ".csv";
        String fileHead = "VECKODAG,DATUM,NÄRVARANDE,ANTAL TIMMAR";

        try(PrintWriter toFile = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))){
            toFile.println(fileHead);

            for (int i = 1; i < attendanceCompilation.size(); i++) {
                //System.out.println(attendanceCompilation.get(i));
                StringBuilder sb = new StringBuilder();
                for (String string : attendanceCompilation.get(i)){
                    sb.append(string + ",");
                }
                toFile.println(sb.toString());
            }

        } catch(IOException e){
            e.printStackTrace();
        }

    }

    private int showMeny(){
        state = States.ADMIN_MENY;
        System.out.println(state.getMessage(null));
        return scan.nextInt();
    }

    private void showAttendanceOnSpecificDay(int dayIndex){
        List<Attendance> chosenList = attendanceDAO.getMonths().get(dayIndex);
        System.out.println(chosenList.get(0).getDate());
        for(Attendance attendance : chosenList){
            StringBuilder sb = new StringBuilder(attendance.getChild().getFirstName() + " ");
            if(attendance.getPresent()){
                sb.append("närvarande");
            } else {
                sb.append("frånvarande");
            }
            System.out.println(sb.toString());
        }
    }

    private int chooseDayToView(){
        System.out.println("Vilken dag vill du se?");
        int counter = 1;
        for(List<Attendance> day : attendanceDAO.getMonths()){
            System.out.println(counter + " " + day.get(0).getDate());
            counter ++;
        }
        return scan.nextInt()-1;
    }

    private int chooseChild(){
        System.out.println("vilkets barn närvaro vill du se?");
        int counter = 1;
        for(Child child : personDAO.getChildList()){
            System.out.println(counter + " " + child.getFirstName());
            counter++;
        }
        return scan.nextInt()-1;
    }

    private List<List<String>> viewChildCompleteAttendance(Child child){
        List<List<String>> attendanceCompilation = new ArrayList<>();

        List<String> childID = new ArrayList<>();
        childID.add(child.getFirstName());
        childID.add(child.getLastName());
        attendanceCompilation.add(childID);
        for (List<Attendance> day : attendanceDAO.getMonths()){
            for(Attendance attendance : day){
                if(attendance.getChild().getFirstName().equals(child.getFirstName())){
                    List<String> attendanceLine = new LinkedList<>();
                    StringBuilder sb = new StringBuilder(attendance.getDate().getDayOfWeek() + " " + attendance.getDate() + " ");
                    attendanceLine.add(attendance.getDate().getDayOfWeek().toString());
                    attendanceLine.add(attendance.getDate().toString());
                    if(attendance.getPresent()){
                        sb.append("närvarande ");
                        CaringTime ct = child.getCaringTime(attendance.getDate().getDayOfWeek().getValue()-1);
                        Duration d = ct.getDuration();
                        long hours = d.toHours();
                        long mins = d.minusHours(hours).toMinutes();
                        sb.append(hours + " timmar");
                        attendanceLine.add("närvarande");
                        if(mins > 0){
                            sb.append(" och " + mins + " minuter");
                            attendanceLine.add(hours + " timmar och " + mins + " minuter");
                        } else {
                            attendanceLine.add(hours + " timmar");
                        }
                    } else {
                        sb.append("frånvarande");
                        attendanceLine.add("frånvarande");
                    }
                    System.out.println(sb.toString());
                    attendanceCompilation.add(attendanceLine);
                }
            }
        }
        return attendanceCompilation;
    }


    private int chooseMonth(Child child){
        System.out.println("Vilken månad vill du se närvaro för " + child.getFirstName() + "?");
        int counter = 1;
        int index = 0;
        String currentMonth = attendanceDAO.getMonths().get(0).get(0).getDate().getMonth().toString();
        System.out.println(counter + " " + currentMonth);
        for(List<Attendance> day : attendanceDAO.getMonths()){
            if(!currentMonth.equals(day.get(0).getDate().getMonth().toString())){
                currentMonth = day.get(0).getDate().getMonth().toString();
                counter ++;
                System.out.println(counter + " " + currentMonth);
            }
            index++;
        }
        return index;
    }

}
