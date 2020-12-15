import java.time.LocalDate;
import java.time.LocalTime;
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

    CHOOSE_ROLE {
        @Override
        public String getMessage(Object o) {
            return ("Välkommen till förskolan!" + "\nLOGGA IN SOM"
                    + "\n 1. Vårdnadshavare" + "\n 2. Pedagog" + "\n 3. Admin \n 4. Avsluta programmet");
        }
    },

    GIVE_USERNAME {
        @Override
        public String getMessage(Object o) {
            return("Skriv ditt namn: ");

        }
    },

    CHOOSE_CHILD {
        @Override
        public String getMessage(Object o) {
            Caregiver caregiver = (Caregiver) o;
            StringBuilder sb = new StringBuilder();

            sb.append("Välkommen " + caregiver.getFirstName() +
                    "\nVälj barn:");
            int counter = 1;
            for (Child child : caregiver.getChildren()) {
                sb.append(counter + " " + child.getFirstName());
                counter++;
            }
            sb.append(counter + " Logga ut.");
            return sb.toString();
        }
    },

    CAREGIVER_MENY {
        @Override
        public String getMessage(Object o) {
            Child child = (Child)o;
            return ("Välkommen till sidan för " + child.getFirstName() +
                    "\n 1. Ange omsorgstider" +
                    "\n 2. Registrera frånvaro" +
                    "\n 3. Se pedagogers kontaktuppgifter" +
                    "\n 4. Logga ut");
        }
    },

    CHILD_ABSENCE {
        @Override
        public String getMessage(Object o) {
            Child child = (Child)o;
            return("Registrerat frånvaro för " + child.getFirstName() + " " + LocalDate.now());
        }
    },

    CHILD_CARINGTIME {
        @Override
        public String getMessage(Object o) {
            Child child = (Child)o;
            StringBuilder sb = new StringBuilder('\n');
            sb.append("Var god ange omsorgstider för " + child.getFirstName());
            sb.append(showCaringTimes(child));
            sb.append("Vilken dag vill du ändra?");
            return sb.toString();
        }

        @Override
        public void addCaringTime(Child child, Scanner scan) {
            String day = scan.next().toLowerCase();

            if (day.equals("måndag")) {
                System.out.println("Var god ange lämningstid och hämtningstid på måndag: ");
                createCaringTime(0, child, day, scan);
            } else if (day.equals("tisdag")) {
                System.out.println("Var god ange lämningstid och hämtningstid på tisdag: ");
                createCaringTime(1, child, day, scan);
            } else if (day.equals("onsdag")) {
                System.out.println("Var god ange lämningstid och hämtningstid på onsdag: ");
                createCaringTime(2, child, day, scan);
            } else if (day.equals("torsdag")) {
                System.out.println("Var god ange lämningstid och hämtningstid på torsdag: ");
                createCaringTime(3, child, day, scan);
            } else if (day.equals("fredag")) {
                System.out.println("Var god ange lämningstid och hämtningstid på fredag: ");
                createCaringTime(4, child, day, scan);
            } else{
                System.out.println("var god skriv dagen igen: ");
                day = scan.next().toLowerCase();
            }
        }

        @Override
        public void createCaringTime(int dayNumber, Child child, String day, Scanner scan) {
            String time = scan.next();
            String start = time.substring(0, time.indexOf(","));
            String stop = time.substring(time.indexOf(",") + 1);
            child.getCaringTimes().set(dayNumber, new CaringTime(day, LocalTime.parse(start), LocalTime.parse(stop)));
        }
    },

    EDUCATOR_INFO {
        @Override
        public String getMessage(Object o) {
            List<Educator> educatorList = (List<Educator>) o;
            StringBuilder sb = new StringBuilder();
            sb.append("Kontaktuppgifter till pedagogerna:");
            for (Educator educator : educatorList) {
                sb.append(educator.getFirstName() + " " + educator.getLastName() +
                        "\n" + educator.getEmailAddress() +
                        "\n" + educator.getPhoneNumber());
            }
            return sb.toString();
        }
    },

    EDUCATOR_MENY {
        @Override
        public String getMessage(Object o) {
            Educator educator = (Educator)o;
            return("\nVälkommen " + educator.getFirstName() + "!" +
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
        public String getMessage(Object o) {
            List<Child> childList = (List<Child>) o;
            StringBuilder sb = new StringBuilder();
            sb.append("Ange frånvaro för: ");
            int counter = 1;
            for (Child child : childList) {
                sb.append(counter + " " + child.getFirstName());
                counter++;
            }
            return sb.toString();
        }
    },

    REGISTER_CHILD {
        @Override
        public String getMessage(Object o) {
           return ("Registrera nytt barn" +
                    "\nVem är vårdnadshavare?: ");
        }

        public Child registerNewChild(Scanner scan) {
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

            return child;
        }

        public Caregiver addCaregiverToNewChild(Scanner scan, String firstName) {

            String lastName;
            String personalNumber;
            String email;
            String phoneNumber;
            String address;

            System.out.println("Denna vårdnadshavare finns inte registrerad");

            System.out.print("Ange vårdnadhavarens efternamn: ");
            lastName = scan.next();
            System.out.print("Ange vårdnadshavarens personnummer: ");
            personalNumber = scan.next();

            Caregiver caregiver = new Caregiver(firstName, lastName, personalNumber);

            System.out.print("Ange vårdnadshavarens e-mail: ");
            email = scan.next();
            caregiver.setEmailAddress(email);

            System.out.print("Ange vårdnadshavarens telefonnummer: ");
            phoneNumber = scan.next();
            caregiver.setPhoneNumber(phoneNumber);

            System.out.print("Ange vårdnadshavarens adress: ");
            address = scan.next();
            caregiver.setPostAddress(address);

            System.out.println();

            return caregiver;
        }


    },

    ATTENDANCE {
        @Override
        public String getMessage(Object o) {
            return ("Vilken lista vill du skriva ut?" +
                    "\n 1. Alla barn" +
                    "\n 2. Närvarande barn" +
                    "\n 3. Frånvarande barn");
        }
    },

    PRINT_ALL {
        @Override
        public String getMessage(Object o) {
            List<Attendance> attendanceList = (List<Attendance>) o;
            String present;
            StringBuilder sb = new StringBuilder();
            sb.append("Närvaro " + LocalDate.now());
            for (Attendance a : attendanceList) {
                if (!a.getPresent())
                    present = "Frånvarande";
                else
                    present = "Närvarande";
                sb.append(a.getChild().getFirstName() + " " + a.getChild().getLastName() +
                        " " + present);
            }
            return sb.toString();
        }
    },

    PRINT_ABSENT {
        @Override
        public String getMessage(Object o) {
            List<Attendance> attendanceList = (List<Attendance>) o;
            StringBuilder sb = new StringBuilder();
            sb.append("Frånvarande " + LocalDate.now());
            for (Attendance a : attendanceList) {
                if (!a.getPresent())
                    sb.append(a.getChild().getFirstName() + " " + a.getChild().getLastName());
            }
            return sb.toString();
        }
    },

    PRINT_PRESENT {
        @Override
        public String getMessage(Object o) {
            List<Attendance> attendanceList = (List<Attendance>) o;
            StringBuilder sb = new StringBuilder();
            sb.append("Närvarande " + LocalDate.now());
            for (Attendance a : attendanceList) {
                if (a.getPresent())
                   sb.append(a.getChild().getFirstName() + " " + a.getChild().getLastName());
            }
            return sb.toString();
        }
    },

    CAREGIVER_INFO {
        @Override
        public String getMessage(Object o) {
            return("Vilket barn?");
        }
    },

    CAREGIVER_INFO_PRINT {
        @Override
        public String getMessage(Object o) {
            Child child = (Child) o;
            List<Caregiver> caregiverList = child.getCaregivers();
            StringBuilder sb = new StringBuilder();
            for(Caregiver caregiver : caregiverList){
                sb.append(caregiver.getFirstName() + " " + caregiver.getLastName()+
                        "\n" + caregiver.getPhoneNumber() +
                        "\n" + caregiver.getEmailAddress());
            }
            return sb.toString();
        }
    },


    SEE_CARINGTIMES {
        @Override
        public String getMessage(Object o) {
            StringBuilder sb = new StringBuilder();
            List<Child> childList = (List<Child>) o;
            sb.append("Vilket barns omsorgstider vill du se? ");
            int counter = 1;
            for (Child c : childList) {
                sb.append(counter + " " + c.getFirstName());
                counter++;
            }
            return sb.toString();
        }

        @Override
        public String showCaringTimes(Child child) {
            return super.showCaringTimes(child);
        }
    },

    ADMIN_MENY {
        @Override
        public String getMessage(Object o) {
            return ("Välkommen! \n1. Visa närvarohistorik över tid \n2. Visa närvarohostorik för barn \n 3. Logga ut");
        }
    },

    LOG_OUT {
        @Override
        public String getMessage(Object o) {
            return ("Du har loggats ut");
        }
    },

    SHUT_DOWN {
        @Override
        public String getMessage(Object o) {
            return ("Programmet avslutas");
        }
    };

    public abstract String getMessage(Object o);


    public Child registerNewChild(Scanner scan) {
        Child child = new Child(null, null, null);
        return child;
    }

    public Caregiver addCaregiverToNewChild(Scanner scan, String firstName){
        Caregiver caregiver = new Caregiver(null, null, null);
        return caregiver;
    }

    public void addCaringTime(Child child, Scanner scan) { }

    public void createCaringTime(int dayNumber, Child child, String day, Scanner scan) { }

    public String showCaringTimes(Child child) {
        StringBuilder sb = new StringBuilder();
        sb.append("Här är " + child.getFirstName() + "s omsorgstider: ");
        for (CaringTime ct : child.getCaringTimes()) {
            sb.append(ct.getDay() + ": " + ct.getStart() + " - " + ct.getStop());
        }
        return sb.toString();
    }


}
