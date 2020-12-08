/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-11-30
 * Project: Preeschool
 * Copyright: MIT
 */
public interface IContactInformation {

    // vi kanske kan anta att alla har fullständig info, då kanske vi kan slå ihop tre setters till en gemensam?

    public void setEmailAddress(String emailAddress);

    public void setPhoneNumber(String phoneNumber);

    public void setPostAddress(String postAddress);

    public String getEmailAddress();

    public String getPhoneNumber();

    public String getPostAddress();

}
