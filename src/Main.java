
import java.util.List;
import java.util.Scanner;

/**
 * Created by Lisa Ramel
 * Date: 2020-12-04
 * Time: 14:03
 * Project: Preeschool
 * Copywrite: MIT
 */
public class Main {
/*
1. Välj Vårdnadshavare eller Pedagog ex (1,2)

Vårdnadshavare
1. Välj barn
1. Registerara frånvaro
2. Anmäla omsorgstider
3. Visa pedagogers uppgifter

Pedagog
1. Lägga till frånvaro
2. Registrera nytt barn

 */

    public static void main(String[] args) {

        Database d = new Database();

        AttendanceDAO attendanceDAO = d;
        DatabaseDAO databaseDAO = d;
        PersonDAO personDAO = d;

        Scanner scan = new Scanner(System.in);

        States s = States.LOGIN;
        s.output(null);

        int input = scan.nextInt();
        String name;


        //Om användaren valde att logga in som vårdnadshavare (1)
        if (input == 1) {
            s = States.USERNAME;
            s.output(null);

            name  = scan.next();
            Caregiver caregiver = personDAO.getCaregiver(name);

            s = States.CAREGIVER;
            s.output(caregiver);

            // väljer barn
            input = scan.nextInt();



            //Om användaren valde ett barn (1)
            if (input <= caregiver.getChildren().size()) {

                Child child = caregiver.getChildren().get(input-1);
                s = States.CHOSE_CHILD;
                s.output(child);
                input = scan.nextInt();

                //Om användaren valde omsorgstider (1)
                if (input == 1) {
                    s = States.CHILD_ATTENDANCE;
                    s.output(child);

                    String time;

                    String[] week = {"måndag", "tisdag", "onsdag", "torsdag", "fredag"};

                    for(String day: week){
                        System.out.println("Var god ange lämningstid och hämtningstid på " + day);
                        time = scan.next();
                        String start = time.substring(0, time.indexOf(","));
                        String stop = time.substring(time.indexOf(",")+1);
                        child.addCaringTime(day, start, stop);
                    }




                }

                //Om användaren valde frånvaro (2)
                else if (input == 2) {
                    s = States.CHILD_ABSENCE;
                    s.output(child);

                    attendanceDAO.addAbsence(child);

                    /*


                    s = States.CAREGIVER;
                    s.output(caregiver);
                    input = scan.nextInt();

                     */

                }

            //Om användaren valde kontaktuppgifter (2)
            } else if (input == 2) {
                s = States.EDUCATOR_INFO;
                s.output(null);
            }

        //Om användaren valde att logga in som pedagog (2)
        } else if (input == 2) {
            s = States.USERNAME;
            s.output(null);

            /*
            name  = scan.next();
            Caregiver caregiver = personDAO.getCaregiver(name);

            s = States.CAREGIVER;
            s.output(caregiver);
             */
            name = scan.next();
            Educator educator = personDAO.getEducator(name);

            s = States.EDUCATOR;
            s.output(educator);


            input = scan.nextInt();

            //Om användaren valde att registrera frånvaro för ett barn
            if (input == 1) {
                s = States.EDUCATOR_ABSENCE;
                s.output(null);

            //Om användaren vill lägga till ett barn
            } else if (input == 2) {
                s = States.REGISTER_CHILD;
                s.output(null);

            }
        }
    }

}



