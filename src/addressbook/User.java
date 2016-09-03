package addressbook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{

	public int userId;
	
	private String userName;
	private String password;
	PhoneAddress phoneAddress ;
	
	List<PhoneAddress> phoneAddressList =new  ArrayList<PhoneAddress>();
	List<User> friends =new ArrayList<User>();
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public PhoneAddress getPhoneAddress() {
		return phoneAddress;
	}

	public void setPhoneAddress(PhoneAddress phoneAddress) {
		this.phoneAddress = phoneAddress;
	}

	public List<PhoneAddress> getPhoneAddressList() {
		return phoneAddressList;
	}

	public void setPhoneAddressList(PhoneAddress phoneAddress) {
		this.phoneAddressList.add(phoneAddress);
	}
	
	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public void setPhoneAddressList(List<PhoneAddress> phoneAddressList) {
		this.phoneAddressList = phoneAddressList;
	}
	public void addPhoneAddressList(PhoneAddress phoneAddress){
		this.phoneAddress =phoneAddress;
		this.phoneAddressList.add(phoneAddress);
	}
	
	
	
	public User(int userId, String userName, String password, List<PhoneAddress> phoneAddressList, List<User> friends) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.phoneAddressList = phoneAddressList;
		this.friends = friends;
	}
	
	public void addFriend(int userId1, int userId2){
		this.userId = userId1;
		
	//	this.userName = userName;
		User fUser = null; 
		fUser.setUserId(userId2);
		this.friends.add(fUser);
	}
	
	
	@Override
	public boolean equals(Object object) {
		boolean result = false;
		if (object == null || object.getClass() != getClass()) {
			result = false;
		} else {
			User user = (User) object;
			if (this.userName != user.userName) {
				result = false;
			}else 
				result=true;
		}
		return result;
	}

	@Override
	public int hashCode() {
		int hash = 1; 
		return hash + this.userName.hashCode();		
	}
	public Boolean CheckUsersId(User user){
		return this.equals(user);
	
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", phoneId=" + this.phoneAddress.getPhoneId() + ", phoneAddressList=" + phoneAddressList + ", friends=" + friends
				+ ", userId=" + userId + ", phoneAddress=" + phoneAddress + "]";
	}
}

