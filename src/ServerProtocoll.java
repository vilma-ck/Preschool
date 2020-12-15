import javax.xml.crypto.Data;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-12-14
 * Project: Preschool_own_project
 * Copyright: MIT
 */
public class ServerProtocoll {

    //private States state;
    private DataTransferObject dto;

    public DataTransferObject startConnection(){
        DataTransferObject startObject = new DataTransferObject();
        startObject.setState(States.CHOOSE_ROLE);
        return startObject;
    }

    public Object processInput(DataTransferObject input){
        // input borde vara obj,
        // och sätta state = inputObj.getState
        // inputObj.getMessage
        // inputObj.getList

        Object object = null;


        // användningsfallen från main
        if(input.getState() == States.CHOOSE_ROLE){ // gamla login
            object = input.getState().getMessage(null);
        }
        else if (input.getState()==States.GIVE_USERNAME){
            if (input.getNavigationNumber()==1){
                // loggar in som vårdnadshavare
            } else if(input.getNavigationNumber() == 2){
                // loggar in som pedagog
            }
            object = input.getState().getMessage(null);
        }

        return object;

    }




}
