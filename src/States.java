import java.io.Serializable;
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
            System.out.println("Var god ange omsorgstider för " + child.getFirstName() );
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
            System.out.println("Registrera nytt barn" +
                    "\nVem är vårdnadshavare?: ");
        }
        public Child registerNewChild(Scanner scan){
            String firstName;
            String lastName;
            String personalNumber;

            System.out.print("Ange barnets förnamn: ");
            firstName = scan.next();

            System.out.print("Ange barnets efternamn: ");
            lastName = scan.next();

            System.out.print("Ange barnets personnummer: ");
            personalNumber = scan.next();

            Child child = new Child(firstName, lastName, personalNumber);
            System.out.println(child.getFirstName() + child.getLastName() + child.getPersonalNumber());

            return child;
        }

        public Caregiver addCaregiverToNewChild(Scanner scan) {
            String firstName;
            String lastName;
            String personalNumber;

            System.out.print("Denna vårdnadshavare finns inte registrerad " +
                    "\nAnge den nya vårdnadshavarens förnamn: ");
            firstName = scan.next();
            System.out.print("Ange vårdnadhavarens efternamn: ");
            lastName = scan.next();
            System.out.print("Ange vårdnadshavarens personnummer: ");
            personalNumber = scan.next();

            Caregiver caregiver = new Caregiver(firstName, lastName, personalNumber);
            System.out.println(caregiver.getFirstName() + caregiver.getLastName() + caregiver.getPersonalNumber());

            return caregiver;
        }

    };

    public abstract void output(Object o);

    public Child registerNewChild(Scanner scan){
        Child child = new Child(null, null, null);
        return child;
    }

    public Caregiver addCaregiverToNewChild(Scanner scan){
        Caregiver caregiver = new Caregiver(null, null, null);
        return caregiver;
    }

}
