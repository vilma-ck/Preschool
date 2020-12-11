import java.util.List;

/**
 * Created by Ivona Zoricic
 * Date: 2020-12-07
 * Time: 13:43
 * Project: Preeschool
 * Copywrite: MIT
 */
public interface PersonDAO {


   public String getContactInformation(IContactInformation person);
   public Child getChild(String name);
   public Caregiver getCaregiver(String name);
   public Educator getEducator(String name);
   List<Child> getChildList();
   List<Educator> getEducatorList();
   List<Caregiver> getCaregiverList();


}
