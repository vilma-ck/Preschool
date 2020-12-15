import java.io.Serializable;
import java.util.List;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-12-15
 * Project: Preschool_own_project
 * Copyright: MIT
 */
public class DataTransferObject implements Serializable {

    private int navigationNumber;
    private States state;
    private String message;
    private List<String> infoList;

    public DataTransferObject(){

    }

    public int getNavigationNumber() {
        return navigationNumber;
    }

    public void setNavigationNumber(int navigationNumber) {
        this.navigationNumber = navigationNumber;
    }

    public States getState() {
        return state;
    }

    public void setState(States state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<String> infoList) {
        this.infoList = infoList;
    }
}
