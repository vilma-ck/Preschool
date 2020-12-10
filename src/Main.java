
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

    private Database d = new Database();

    private AttendanceDAO attendanceDAO = d;
    private DatabaseDAO databaseDAO = d;
    private PersonDAO personDAO = d;

    private Scanner scan = new Scanner(System.in);

    private States s;

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

    public Main() throws InterruptedException {
        s = States.LOGIN;

        s.output(null);

        int input = scan.nextInt();


        while (true) {
            if (input == 1) {
                Thread.sleep(1000);
                caregiverView(input);
                s = States.LOGIN;
                s.output(null);
                input = scan.nextInt();
                //break;
            } else if (input == 2) {
                Thread.sleep(1000);
                educatorView(input);
                s = States.LOGIN;
                s.output(null);
                input = scan.nextInt();
                //break;
            } else if (input == 3) {
                System.out.println("Programmet avslutas");
                break;
            } else {
                System.out.println("Ogiltigt kommando, var god försök igen.");
                input = scan.nextInt();
            }
        }
    }

    public Caregiver lookupCaregiver(String name) {
        Caregiver caregiver = personDAO.getCaregiver(name);
        while (caregiver == null) {
            System.out.println("Var god försök igen: ");
            name = scan.next();
            caregiver = personDAO.getCaregiver(name);
        }
        return caregiver;
    }

    public void caregiverView(int input) throws InterruptedException {
        String name;
        //Om användaren valde att logga in som vårdnadshavare (1)

        s = States.USERNAME;
        s.output(null);
        name = scan.next();
        Caregiver caregiver = lookupCaregiver(name);
        while (true) {
            Child child;
            child = caregiver.getChildren().get(0);

            if (caregiver.getChildren().size() > 1) {
                Thread.sleep(1000);
                s = States.CAREGIVER;
                s.output(caregiver);
                // väljer barn
                input = scan.nextInt();
                //Om användaren valde ett barn (1)
                if (input <= caregiver.getChildren().size()) {
                    child = caregiver.getChildren().get(input - 1);
                }
            }
            Thread.sleep(1000);
            s = States.CHOSE_CHILD;
            s.output(child);

            input = scan.nextInt();

            //Om användaren valde omsorgstider (1)
            if (input == 1) {
                Thread.sleep(1000);
                s = States.CHILD_ATTENDANCE;
                s.output(child);
                s.addCaringTime(child, scan);
            }

            //Om användaren valde frånvaro (2)
            else if (input == 2) {
                Thread.sleep(1000);
                s = States.CHILD_ABSENCE;
                s.output(child);
                attendanceDAO.addAbsence(child);

            }

            //Om användaren valde kontaktuppgifter (3)
            else if (input == 3) {
                Thread.sleep(1000);
                s = States.EDUCATOR_INFO;
                List<Educator> educatorList = databaseDAO.getEducatorList();
                s.output(educatorList);
            } else if (input == 4) {
                Thread.sleep(1000);
                s = States.SHUT_DOWN;
                s.output(caregiver);
                break;
            } else {
                System.out.println("Okänt kommando, var göd försök igen.");
            }
        }

    }


    public void educatorView(int input) throws InterruptedException {

        String name;
        String firstName;
        String lastName;
        String personalNumber;
        //Om användaren valde att logga in som pedagog (2)

        s = States.USERNAME;
        s.output(null);

        name = scan.next();
        Educator educator = personDAO.getEducator(name);
        while (educator == null) {
            System.out.println("Var god försök igen: ");
            name = scan.next();
            educator = personDAO.getEducator(name);
        }

        while (true) {
            Thread.sleep(1000);
            s = States.EDUCATOR;
            s.output(educator);
            input = scan.nextInt();

            //Om användaren valde att registrera frånvaro för ett barn
            if (input == 1) {
                Thread.sleep(1000);
                s = States.EDUCATOR_ABSENCE;
                List<Child> childList = databaseDAO.getChildList();
                s.output(databaseDAO.getChildList());
                input = scan.nextInt();

                //Registrerar frånvaro på barn
                if (input <= childList.size()) {
                    Thread.sleep(1000);
                    s = States.CHILD_ABSENCE;
                    Child child = childList.get(input - 1);
                    s.output(child);
                    attendanceDAO.addAbsence(child);
                }


                //Om användaren vill lägga till ett barn
            } else if (input == 2) {
                Thread.sleep(1000);
                s = States.REGISTER_CHILD;
                s.output(null);
                firstName = scan.next();
                boolean foundCaregiver = false;

                for (Caregiver c : d.getCaregiverList()) {
                    if (c.getFirstName().equalsIgnoreCase(firstName)) {
                        System.out.println("Det nya barnet kommer att registreras på den redan " +
                                "\nexisterande vårdnadshavaren " + c.getFirstName());
                        Child child = s.registerNewChild(scan);
                        c.addChildren(child);
                        d.addChild(child);
                        //s.registerNewChild(scan);
                        foundCaregiver = true;
                    }
                }
                if (!foundCaregiver) {
                    s.addCaregiverToNewChild(scan).addChildren(s.registerNewChild(scan));
                    //s.addCaregiverToNewChild(scan);
                }

                //TODO test om barn lagts till:
                System.out.println(d.getChildList().size());
                System.out.println(d.getCaregiverList().size());


            }
            //Om användaren vill skriva ut närvarolistor
            else if (input == 3) {
                Thread.sleep(1000);
                s = States.PRINT_ATTENDANCE;
                s.output(null);
                input = scan.nextInt();
                if (input == 1) {
                    Thread.sleep(1000);
                    s = States.PRINT_ALL;
                    s.output(attendanceDAO.getAttendanceToday());
                } else if (input == 2) {
                    Thread.sleep(1000);
                    s = States.PRINT_PRESENT;
                    s.output(attendanceDAO.getAttendanceToday());
                } else if (input == 3) {
                    Thread.sleep(1000);
                    s = States.PRINT_ABSENT;
                    s.output(attendanceDAO.getAttendanceToday());
                }
            } else if (input == 4) {
                Thread.sleep(1000);
                s = States.CAREGIVER_INFO;
                s.output(null);
                name = scan.next();
                List<Child> childList = databaseDAO.getChildList();
                for (Child child : childList) {
                    if (name.equalsIgnoreCase(child.getFirstName()) || name.equalsIgnoreCase(child.getLastName())) {
                        s = States.CAREGIVER_INFO_PRINT;
                        s.output(child);
                    }
                }
            }
            //om användaren väljer att avsluta
            else if (input == 5) {
                d.addAttendanceTodayInList(d.getAttendanceToday());
                d.serialize(d.getAttendanceTest(), SerFiles.LIST_OF_ATTENDANCE.serFiles);
                d.serialize(d.getAttendanceToday(), SerFiles.ATTENDANCE.serFiles);
                d.serialize(d.getChildList(), SerFiles.CHILDS.serFiles);
                d.serialize(d.getEducatorList(), SerFiles.EDUCATOR.serFiles);
                d.serialize(d.getCaregiverList(), SerFiles.CAREGIVERS.serFiles);
                Thread.sleep(1000);
                s = States.SHUT_DOWN;
                s.output(educator);
                break;

            } else {
                System.out.println("Okänt kommando, var god försök igen.");
            }

        }

    }

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
    }
}



