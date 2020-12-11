import java.util.List;

/**
 * Created by Ivona Zoricic
 * Date: 2020-12-07
 * Time: 15:10
 * Project: Preeschool
 * Copywrite: MIT
 */
public interface DatabaseDAO {
    void addChild(Child child);
    void deleteChild(Child child);
    void addCaregiver(Caregiver caregiver);
    void deleteCaregiver(Caregiver caregiver);
    void addEducator(Educator educator);
    void deleteEducator(Educator educator);
    List<Child> getChildList();
    List<Educator> getEducatorList();
    List<Caregiver> getCaregiverList();
    String getContactInformation(IContactInformation person);
    <T> List<T> deSerialize(String fileName);
    <T> void serialize(List <T> list, String fileName);

}
