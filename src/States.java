import javax.xml.crypto.Data;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
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
                    + "\n 1. Vårdnadshavare" + "\n 2. Pedagog" + "\n 3. Avsluta programmet");
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
                    "\n 3. Se pedagogers kontaktuppgifter" +
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
            showCaringTimes(child);
            System.out.println("Vilken dag vill du ändra?");
        }

        @Override
        public void addCaringTime(Child child, Scanner scan) {
            String time;
            String day  = scan.next().toLowerCase();

            if(day.equals("måndag")){
                System.out.println("Var god ange lämningstid och hämtningstid på måndag: ");
                createCaringTime(child, day, scan);
            } else if(day.equals("tisdag")){
                System.out.println("Var god ange lämningstid och hämtningstid på tisdag: ");
                createCaringTime(child, day, scan);
            }else if(day.equals("onsdag")){
                System.out.println("Var god ange lämningstid och hämtningstid på onsdag: ");
                createCaringTime(child, day, scan);
            }else if(day.equals("torsdag")){
                System.out.println("Var god ange lämningstid och hämtningstid på torsdag: ");
                createCaringTime(child, day, scan);
            }else if(day.equals("fredag")){
                System.out.println("Var god ange lämningstid och hämtningstid på fredag: ");
                createCaringTime(child, day, scan);

            }
        }

        @Override
        public void createCaringTime(Child child, String day, Scanner scan) {
            String time = scan.next();
            String start = time.substring(0, time.indexOf(","));
            String stop = time.substring(time.indexOf(",") + 1);
            child.caringTimes.set(0, new CaringTime("fredag", LocalTime.parse(start), LocalTime.parse(stop)));
        }
    },

    EDUCATOR_INFO {
        @Override
        public void output(Object o) {
            List<Educator> educatorList = (List<Educator>) o;
            System.out.println("Kontaktuppgifter till pedagogerna:");
            for (Educator educator : educatorList){
                System.out.println(educator.getFirstName() + " " + educator.getLastName()+
                        "\n" + educator.getEmailAddress()+
                        "\n" + educator.getPhoneNumber());
            }
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
                    "\n 4. Se ett barns omsorgstider " +
                    "\n 5. Se vårdnadshavares kontaktuppgifter" +
                    "\n 6. Logga ut");
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

            Database d = new Database();
            String[] week = {"måndag", "tisdag", "onsdag", "torsdag", "fredag"};

            for(String day: week) {


            }

            System.out.print("Denna vårdnadshavare finns inte registrerad " +
                    "\nAnge den nya vårdnadshavarens förnamn: ");
            firstName = scan.next();
            System.out.print("Ange vårdnadhavarens efternamn: ");
            lastName = scan.next();
            System.out.print("Ange vårdnadshavarens personnummer: ");
            personalNumber = scan.next();

            Caregiver caregiver = new Caregiver(firstName, lastName, personalNumber);
            System.out.println(caregiver.getFirstName() + caregiver.getLastName() + caregiver.getPersonalNumber());

            d.addCaregiver(caregiver);

            return caregiver;
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

    CAREGIVER_INFO{
        @Override
        public void output(Object o) {
            System.out.println("Vilket barn?");
        }
    },

    CAREGIVER_INFO_PRINT{
        @Override
        public void output(Object o) {
            Child child = (Child) o;
            List<Caregiver> caregiverList = child.getCaregivers();
            for(Caregiver caregiver : caregiverList){
                System.out.println(caregiver.getFirstName() + " " + caregiver.getLastName()+
                        "\n" + caregiver.getPhoneNumber() +
                        "\n" + caregiver.getEmailAddress());
            }
        }
    },



    SEE_CARINGTIMES{
        @Override
        public void output(Object o) {
            List<Child> childList = (List<Child>) o;
            System.out.println("Vilket barns omsorgstider vill du se? ");
            int counter = 1;
            for (Child c : childList){
                System.out.println(counter + " " + c.getFirstName());
                counter ++;
            }
        }

        @Override
        public void showCaringTimes(Child child) {
            super.showCaringTimes(child);
        }

    },


    SHUT_DOWN{
        @Override
        public void output(Object o) {
            System.out.println("Programmet är avslutat");
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

    public void addCaringTime(Child child, Scanner scan){};

    public void createCaringTime(Child child, String day, Scanner scan){};

    public void showCaringTimes(Child child){
        System.out.println("Här är " + child.getFirstName() + "s omsorgstider: ");
        for(CaringTime ct : child.caringTimes){
            System.out.println(ct.getDay() + ": " + ct.getStart() + " - " + ct.getStop());
        }
    };

}
