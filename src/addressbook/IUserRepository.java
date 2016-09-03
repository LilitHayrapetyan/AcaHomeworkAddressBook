package addressbook;

import java.io.IOException;
import java.util.List;

public interface IUserRepository {

	
	void deleteUser(User user);
	boolean addUser(User user);
	User addPhoneAddressInFile(User user, PhoneAddress phoneAddress) throws IOException;
	List<PhoneAddress> getPhoneAddresses(String userName);
	User getUser(String userName) throws IOException, ClassNotFoundException;
	boolean userListContainUserOrNot(List<User> userList, User usr);
	void addFriendInFile(int userId, int fuserId) throws IOException;
	void editUser(User user, int userId) throws IOException;

}
