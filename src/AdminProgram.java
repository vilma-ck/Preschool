import java.util.List;
import java.util.Scanner;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-12-15
 * Project: Preschool_own_project
 * Copyright: MIT
 */
public class AdminProgram {


    private final Database d = new Database();

    private AttendanceDAO attendanceDAO = d;
    private DatabaseDAO databaseDAO = d;
    private PersonDAO personDAO = d;

    private Scanner scan = new Scanner(System.in);

    private States state;

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
                // visa historik för barn
                // välj barn
                // välj månad
                // skapa närvarorapport, skapa fil som kan skickas
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

    private int showMeny(){
        state = States.ADMIN_MENY;
        System.out.println(state.getMessage(null));
        return scan.nextInt();
    }

    private void showAttendanceOnSpecificDay(int dayIndex){
        List<Attendance> chosenList = attendanceDAO.getAttendanceList().get(dayIndex);
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
        for(List<Attendance> day : attendanceDAO.getAttendanceList()){
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

}
