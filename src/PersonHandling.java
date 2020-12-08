
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-12-07
 * Project: Preeschool
 * Copyright: MIT
 */
public class PersonHandling {

    private Database database;
    public PersonHandling(Database database) throws IOException {
        this.database = database;
    }

/*
    public <? extends Person> getPerson(String searchWord, ArrayList<Person> list){
        for(Person p : list){
            if(p.getFirstName().equalsIgnoreCase(searchWord) || p.getLastName().equalsIgnoreCase(searchWord)){
                if(p instanceof Child){
                    return p;
                }
            }

        }

    }

 */

    public static void main(String[] args) {

    }
}
