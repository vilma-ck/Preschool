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
        public void output() {
            System.out.println("Välkommen till förskolan!" + "\nLOGGA IN SOM"
                    + "\n 1. Vårdnadshavare" + "\n 2. Pedagog");
        }
    },

    CAREGIVER {
        @Override
        public void output() {
            System.out.println("Välkommen " + "VÅRDNADSHAVARE" + "!" +
                    "\n 1. Välj barn:" + '\n' + "BARN1" +
                    "\n 2. Kontaktuppgifter till pedagoger");
        }
    },

    CHOSE_CHILD {
        @Override
        public void output() {
            System.out.println("Välkommen till sidan för " + "BARN1" +
                    "\n 1. Ange omsorgstider" +
                    "\n 2. Registrera frånvaro");
        }

    },

    CHILD_ABSENCE {
        @Override
        public void output() {
            System.out.println("Registrera frånvaro för " + "BARN1");
        }
    },

    CHILD_ATTENDANCE {
        @Override
        public void output() {
            System.out.println("Var god ange omsorgstider för " + "BARN1");
        }
    },

    EDUCATOR_INFO {
        @Override
        public void output() {
            System.out.println("Kontaktuppgifter till Pedagoger");
        }
    },

    EDUCATOR {
        @Override
        public void output() {
            System.out.println("Välkommen " + "PEDAGOG" + "!" +
                    "\n 1. Ange frånvaro" +
                    "\n 2. Registrera ett nytt barn till förskolan");
        }
    },

    EDUCATOR_ABSENCE {
        @Override
        public void output() {
            System.out.println("Ange frånvaro för: " +
                    "\n 1. " + "BARN1");
        }
    },

    REGISTER_CHILD {
        @Override
        public void output() {
            System.out.println("Registrera nytt barn");
        }
    };

    public abstract void output();

}
