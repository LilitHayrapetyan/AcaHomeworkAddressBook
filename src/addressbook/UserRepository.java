package addressbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

//public class UserRepository implements UserRepositoryInterface{
public class UserRepository implements IUserRepository{

	static int counter = 0;
	String userName;
	private String password;
	List<PhoneAddress> phoneAddressList;
	List<User> friends;
	
	User user; // static or no
	User friendUser;
	List<User> userList = new ArrayList();

	private static final String FILE_HEADER = "userId, userName, password, phoneAddressList,  friendsList";
	String commandfilename = "/Users/lilitha/projects/aca_homeworks/UserList.csv";
	String phoneAddressFilename = "/Users/lilitha/projects/aca_homeworks/phoneAddressFilename.csv";

	private User result;

	FileWriter fileWriter = null;

	@Override
	public boolean addUser(User user) {
		this.user=user;
		if (userList.contains(user)) {
			System.out.println("user already exist");
			return false;
		} else
			userList.add(user);

		try {

			user.userId = counter++;
			user.phoneAddress.setPhoneId(user.userId);

			FileOutputStream fos = new FileOutputStream(commandfilename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(userList);
			oos.close();
			System.out.println(" User successfully added in file");

		} catch (Exception e) {

			// System.out.println();
			e.printStackTrace();
		}

		return true;
	}
	@Override
	public User addPhoneAddressInFile(User user, PhoneAddress phoneAddress) throws IOException {
		if (phoneAddressList.contains(phoneAddress)) {
			System.out.println("The phone number already exists.");
			return null;
		} else {
			phoneAddressList.add(phoneAddress);
			try {

				FileOutputStream fos1 = new FileOutputStream(phoneAddressFilename);
				ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
				oos1.writeObject(user.phoneAddressList);
				oos1.close();
				System.out.println("Successfully added phoneAddress in file");
			} catch (IOException e) {
				e.getMessage();
			}
			return user;

		}

	}
	@Override
	public List<PhoneAddress> getPhoneAddresses(String userName){
		
		
		
		
		//phoneAddressFilename
		return phoneAddressList;
		
	}
	
	@Override
	public User getUser(String userName) throws IOException, ClassNotFoundException {
		
		try{
			FileInputStream fis = new FileInputStream("commandfilename");
			ObjectInputStream ois = new ObjectInputStream(fis);
			userList = (ArrayList<User>) ois.readObject();
		
		ois.close();
		
		
	}catch(IOException e){
		e.getMessage();
	}
		if(userListContainUserOrNot(userList, user) ){
			return user;
		}
		return null;
	
		
	}	
	@Override
	public boolean userListContainUserOrNot(List<User> userList, User usr) {
		this.userList = userList;
		this.user = usr;
		for (User tmpUser : userList) {
			if (tmpUser.equals(usr)) {
				return true;
			}
		}
		return false;
	}

	 @Override
	public void deleteUser(User user){
		
			
			try{
				FileInputStream fis = new FileInputStream("commandfilename");
				ObjectInputStream ois = new ObjectInputStream(fis);
				userList = (ArrayList<User>) ois.readObject();
			
			ois.close();				
			}catch(IOException e){
				e.getMessage();
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}
			if(userListContainUserOrNot(userList, user)){
				userList.remove(user);
			}else 
				System.out.println("No such user in list");
		}	
	@Override 
	public void addFriendInFile(int userId, int fuserId) throws IOException{
		this.user.userId = userId;
		this.friendUser.userId = fuserId;
		try{
			FileInputStream fis = new FileInputStream("phoneAddressFilename");
			ObjectInputStream ois = new ObjectInputStream(fis);
			userList = (ArrayList<User>) ois.readObject();
		ois.close();
		
	}catch(IOException e){
		e.getMessage();
	}catch(ClassNotFoundException e){
		e.printStackTrace();
	}
		/*if(!userListContainUserOrNot(userList, user) || !userListContainUserOrNot(userList, fuser) ){
			throw new ListNotContainUser();
		}catch(ListNotContainUser ex){
			ex
		}
	*/}
	public boolean delFriend(int userId, int fUserId){
		return false;
	}

	 @Override
	public void editUser(User user, int userId) throws IOException {
		// TODO Auto-generated method stub
//commandfilename
	}

	// controllerna kanchum Repoyin,Repon DB, hakarak@ chisht chi....
	// kmaxq@ grel,
}
