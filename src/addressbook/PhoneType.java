package addressbook;

enum PhoneType {

	MOBILEPHONENUMBER(1), 
	HOMEPHONENUMBER(2),
	FAX(1),
	HOMEADDRESS(1);
	int phoneId;
	
	PhoneType(int phoneId){
		this.phoneId = phoneId;
	}
	public int getphoneId() {
		return phoneId;
	}
	
//PhoneType constructor
}
