package addressbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class UserController {

	public String str;
	public static User user;

	String FILE_DIR = "/Users/lilitha/projects/AddressBookSkeleton/resources/config.properties";
	String COMMAND_DIR = "/Users/lilitha/projects/AddressBookSkeleton/resources/commands.properties";

	String command;
	Scanner sc = new Scanner(System.in);
	UserRepository userRepo = new UserRepository();
	Properties prop = new Properties();
	// String lockAccount = prop.getProperty("lockAccount");
	public static int counter = 0;
	List<User> userList = null;
	private String userName;
	private String passwd;
	List<User> friends;
	PhoneAddress phoneAddress;
	List<PhoneAddress> phoneAddressList;

	// InputStream input = null;
	// private ArrayList<String> lines;

	public void startMethod() throws IOException, ClassNotFoundException {

		FileInputStream input = new FileInputStream(FILE_DIR);
		prop.load(input);
		System.out.println(prop.getProperty("writeSignInOrSignUp"));
		command = sc.nextLine();
		try {
			validation(command);
		} catch (CommandException e) {
			System.out.println(e.getMessage());
			startMethod();
		}
        while (command != "Exit" || counter != 3) {
			switch (command) {

			case ("Sign In"):
				signIn();
			case ("Sign Up"):
				signUp();
			case ("Sign Out"):
				System.out.println(prop.getProperty("writeSignInOrSignUp"));
				signInOrSignUp();
			case ("Help"):
				System.out.println(prop.getProperty("Help"));
				startMethod();
			default:
				// invalidCommandCounter();
				counter++;
				System.out.println(prop.getProperty("invalidCommand"));
				System.out.println("or " + prop.getProperty("invalidUserNameOrPasswd"));
				// check if 3 times are not written invalid commands
			}
			command = sc.nextLine();
		}
		}
	


	public void continueMethod(User user) throws IOException, ClassNotFoundException {

		FileInputStream input = new FileInputStream(FILE_DIR);
		prop.load(input);
		System.out.println(prop.getProperty("writeSignInOrSignUp"));
		command = sc.nextLine();
		while (command != "Exit" || counter != 3) {
			switch (command) {
			
			case "Add Tel. Numbers":
				addTelNum(user);
				continueMethod(user);
			case ("Show Tel. Numbers"):
				showTelNumbers(user);
			    continueMethod(user);
			case ("Add Friend"):
				addFriend(user);
		        continueMethod(user);
			case ("Delete Friend"):
				deleteFriend();
		        continueMethod(user);
			case ("Show my friend list"):
				showMyFriendList();
		        continueMethod(user);
			default:
				// invalidCommandCounter();
				counter++;
				System.out.println(prop.getProperty("invalidCommand"));
			}
			command = sc.nextLine();
		}
	}

	public int invalidCommandCounter() {
		if (!prop.contains(command)) {
			return counter++;
		}
		return counter = counter;
	}

	public boolean userListContainUserOrNot(List<User> userList, User user) {
		this.userList = userList;
		this.user = user;
		for (User tmpUser : userList) {
			if (tmpUser.equals(user)) {
				return true;
			}
		}
		return false;
	}

	public void signIn() throws IOException, ClassNotFoundException {

		boolean var = false;
		System.out.println(prop.getProperty("provideYourUserName"));
		userName = sc.nextLine();
		user.setUserName(userName);
		System.out.println(prop.getProperty("provideYourPassword"));
		passwd = sc.nextLine();
		user.setPassword(passwd);
		User newUser = userRepo.getUser(userName);
		if (newUser != null) {
			var = true;
		}
		if (var) {
			System.out.println(prop.getProperty("succesfullyLoggedIn"));
			System.out.println(prop.getProperty("addTelNumberOrShowTelNumbers"));
			continueMethod(newUser);

		} else {
			System.out.println("notUserSignUp");
			counter++;
			signIn();
		}
		if (counter < 3) {
			System.out.println(prop.getProperty("invalidUserNameOrPasswd"));
		} else {
			System.out.println(prop.getProperty("lockAccount"));
			System.exit(0);
		}
	}

	public void signUp() throws IOException, ClassNotFoundException {

		System.out.println(prop.getProperty("provideYourUserName"));
		userName = sc.nextLine();
		user.setUserName(userName);
		System.out.println(prop.getProperty("provideYourPassword"));
		passwd = sc.nextLine();
		user.setPassword(passwd);

		if (!userRepo.addUser(user)) {
			System.out.println(prop.getProperty("plsTryAgain"));
			signIn();
		} else {
			System.out.println("successCreatedNewUserWriteSignInOrSignUp");
			startMethod();
		}
	}

	private void signInOrSignUp() throws IOException, ClassNotFoundException {
		startMethod();
	}

	public void addTelNum(User user) throws IOException, ClassNotFoundException {
		System.out.println("provideYourTelNumber");
		String tempNum = sc.nextLine();
		user = this.userRepo.addPhoneAddressInFile(user, new PhoneAddress(tempNum));
		if (user != null) {
			System.out.println("successCreatedNewUserWriteSignInOrSignUp");
			continueMethod(user);
		} else
			System.out.println("invalidCommandHelp");
		startMethod();
	}

	public void showTelNumbers(User user) throws IOException {

		this.phoneAddressList = this.userRepo.getPhoneAddresses(user.getUserName());
		for (PhoneAddress temp : phoneAddressList) {
			System.out.println(temp);
		}
	//	continueMethod(user);
	}

	public void telNumberChecker(String addTelN) {
		if (addTelN.length() < 12 || addTelN.length() > 14 || addTelN.contains(("[a-zA-Z+]"))) {
			System.out.println(prop.getProperty("correctPhoneNumber"));
		}
	}

	public void addFriend(User friendUser) throws IOException, ClassNotFoundException {
		
         System.out.println(prop.getProperty("yourFriendUserName"));
         String friendUserName = sc.nextLine();
         int fUserId = this.userRepo.getUser(friendUserName).userId;
         this.userRepo.addFriendInFile(this.user.getUserId(), fUserId);
         System.out.println("yourFriendHasAccessPhonebook");
     //    continueMethod(friendUser);
		//this.user.addFriend(this.user.userId, friendUser.userId);
		//userRepo.addFriendInFile(this.user.userId, friendUser.userId);

	}

	private void deleteFriend() throws ClassNotFoundException, IOException {
		System.out.println("yourFriendUserName");
        String friendUserName = sc.nextLine();
        User fUser = this.userRepo.getUser(friendUserName);
        this.userRepo.delFriend(this.user.userId, fUser.userId);
		System.out.println(this.user.getUserName());
	//	continueMethod(fUser);
	}

	private void showMyFriendList() {
		// TODO Auto-generated method stub

	}

/*	public void readFile(String FILE_DIR) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(FILE_DIR));
		lines = new ArrayList<String>();
		String newLine;
		while ((newLine = br.readLine()) != null) {
			newLine = br.readLine();
			System.out.println(newLine);
			lines.add(newLine);
		}
		br.close();
	}
	*/
	public void validation(String command)throws CommandException{
		
		
		
		
		throw new CommandException(command);
	}
}
