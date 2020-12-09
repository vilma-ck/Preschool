import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Lisa Ramel
 * Date: 2020-12-04
 * Time: 16:27
 * Project: Preeschool
 * Copywrite: MIT
 */
public enum States {

    LOGIN {
        @Override
        public void output(Object o) {
            System.out.println("Välkommen till förskolan!" + "\nLOGGA IN SOM"
                    + "\n 1. Vårdnadshavare" + "\n 2. Pedagog" + "\n 3. Stäng av programmet.");
        }
    },

    USERNAME {
        @Override
        public void output(Object o) {
            System.out.println("Skriv ditt namn: ");
        }
    },

    CAREGIVER {
        @Override
        public void output(Object o) {
            Caregiver caregiver = (Caregiver) o;
            System.out.println("Välkommen " + caregiver.getFirstName() +
                    "\nVälj barn:");
            int counter = 1;
            for(Child child : caregiver.getChildren()){
                System.out.println(counter + " " + child.getFirstName());
                counter++;
            }
        }
    },

    CHOSE_CHILD {
        @Override
        public void output(Object o) {
            Child child = (Child)o;
            System.out.println("Välkommen till sidan för " + child.getFirstName() +
                    "\n 1. Ange omsorgstider" +
                    "\n 2. Registrera frånvaro" +
                    "\n 3. Visa pedagogers kontaktuppgifter" +
                    "\n 4. Logga ut");
        }
    },

    CHILD_ABSENCE {
        @Override
        public void output(Object o) {
            Child child = (Child)o;
            System.out.println("Registrerat frånvaro för " + child.getFirstName() + " " + LocalDate.now());

        }
    },

    CHILD_ATTENDANCE {
        @Override
        public void output(Object o) {
            Child child = (Child)o;
            System.out.println("Var god ange omsorgstider för " + child.getFirstName());
        }

        @Override
        public void addCaringTime(Child child, Scanner scan) {
            String time;

            String[] week = {"måndag", "tisdag", "onsdag", "torsdag", "fredag"};

            for(String day: week) {
                System.out.println("Var god ange lämningstid och hämtningstid på " + day);
                time = scan.next();
                String start = time.substring(0, time.indexOf(","));
                String stop = time.substring(time.indexOf(",") + 1);
                child.addCaringTime(day, start, stop);
            }
        }
    },

    EDUCATOR_INFO {
        @Override
        public void output(Object o) {
            System.out.println("Kontaktuppgifter till Pedagoger");
        }
    },

    EDUCATOR {
        @Override
        public void output(Object o) {
            Educator educator = (Educator)o;
            System.out.println("\nVälkommen " + educator.getFirstName() + "!" +
                    "\n 1. Ange frånvaro" +
                    "\n 2. Registrera ett nytt barn till förskolan" +
                    "\n 3. Se närvaro idag" +
                    "\n 4. Logga ut");
        }
    },

    EDUCATOR_ABSENCE {
        @Override
        public void output(Object o) {
            List<Child> childList = (List<Child>) o;
            System.out.println("Ange frånvaro för: ");
            int counter = 1;
            for(Child child : childList) {
                System.out.println(counter + " " + child.getFirstName());
                counter ++;
            }
        }
    },

    REGISTER_CHILD {
        @Override
        public void output(Object o) {
            System.out.println("Registrera nytt barn");
        }
    },

    PRINT_ATTENDANCE{
        @Override
        public void output(Object o) {
            System.out.println("Vilken lista vill du skriva ut?");
            System.out.println(" 1. Alla barn" +
                    "\n 2. Närvarande barn" +
                    "\n 3. Frånvarande barn");
        }
    },

    PRINT_ALL{
        @Override
        public void output(Object o) {
            List<Attendance> attendanceList = (List<Attendance>) o;
            String present;
            System.out.println("Närvaro " + LocalDate.now());
            for(Attendance a: attendanceList){
                if(!a.getPresent())
                    present = "Frånvarande";
                else
                    present = "Närvarande";
                System.out.println(a.getChild().getFirstName() + " " + a.getChild().getLastName() +
                        " " + present );
            }
            System.out.println();
        }
    },

    PRINT_ABSENT{
        @Override
        public void output(Object o) {
            List<Attendance> attendanceList = (List<Attendance>) o;
            System.out.println("Frånvarande " + LocalDate.now());
            for(Attendance a: attendanceList) {
                if (!a.getPresent())
                    System.out.println(a.getChild().getFirstName() + " " + a.getChild().getLastName());
            }
        }
    },

    PRINT_PRESENT {
        @Override
        public void output(Object o) {
            List<Attendance> attendanceList = (List<Attendance>) o;
            System.out.println("Närvarande " + LocalDate.now());
            for(Attendance a: attendanceList) {
                if (a.getPresent())
                    System.out.println(a.getChild().getFirstName() + " " + a.getChild().getLastName());
            }
        }
    },

    SHUT_DOWN{
        @Override
        public void output(Object o) {
            System.out.println("Programmet är avslutat");
        }
    };

    public abstract void output(Object o);

    public void addCaringTime(Child child, Scanner scan){};




}
