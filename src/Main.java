import com.sun.security.jgss.GSSUtil;

import java.sql.SQLOutput;
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
    1. välj vårdnads  eller pedagog ex (1,2)
2. vårdnads.. 1. Välj barn
                               1. Registerara frånvaro
                                2. anmäla omsorgstider
                     2. visa pedagogers uppgifter

3. Pedagog  1. lägga till frånvaro
                    2. Registrera nytt barn

 */

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        //TODO ska bytas ut
        String b1 = "Barn1";
        String p1 = "Pedagog";
        String v1 = "Vårdnadshavare";

        System.out.println("Välkommen till förskolan!");
        System.out.println();

        System.out.println("LOGGA IN SOM");
        System.out.println("1. Vårdnadshavare");
        System.out.println("2. Pedagog");

            //Om användaren valde att logga in som vårdnadshavare (1)
            if(scan.nextInt() == 1){

                System.out.println("Välkommen " + v1 + "!");
                System.out.println("Välj barn:");
                System.out.println(b1);

                    //Om användaren valde att 'hantera' "Barn1"
                    if (scan.next().equalsIgnoreCase(b1)) {

                        System.out.println("Välkommen till sidan för " + b1);
                        System.out.println("1. Ange omsorgstider");
                        System.out.println("2. Registrera frånvaro");

                        //Om användaren valde omsorgstider (1)
                        if(scan.nextInt() == 1){
                            System.out.println("Var god ange omsorgstider för " + b1);
                        }

                        //Om användaren valde frånvaro (2)
                        else if(scan.nextInt() == 2){
                            System.out.println("Registrera frånvaro för " + b1);
                        }

                    } else {
                        System.out.println("Det här barnet finns inte registrerat på förskolan");
                    }

}
            //Om användaren valde att logga in som pedagog (2)
            else if(scan.nextInt() == 2){

                System.out.println("Välkommen " + p1 + "!");
                System.out.println("1. Ange frånvaro");
                System.out.println("2. Registrera ett nytt barn till förskolan");

                if(scan.nextInt() == 1){
                    System.out.println("Ange frånvaro för ");
                    //TODO alla barn på förskolan blir ett alternativ att välja
                    System.out.println(b1);
                }
                else if(scan.nextInt() == 2){
                    System.out.println("Registrera nytt barn");
                }

            }



    }
}
