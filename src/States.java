import java.time.LocalDate;
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
                    + "\n 1. Vårdnadshavare" + "\n 2. Pedagog");
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
                    "\n 2. Registrera frånvaro");
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
            System.out.println("Välkommen " + educator.getFirstName() + "!" +
                    "\n 1. Ange frånvaro" +
                    "\n 2. Registrera ett nytt barn till förskolan");
        }
    },

    EDUCATOR_ABSENCE {
        @Override
        public void output(Object o) {
            System.out.println("Ange frånvaro för: " +
                    "\n 1. " + "BARN1");
        }
    },

    REGISTER_CHILD {
        @Override
        public void output(Object o) {
            System.out.println("Registrera nytt barn");
        }
    };

    public abstract void output(Object o);

    public void addCaringTime(Child child, Scanner scan){};



}
