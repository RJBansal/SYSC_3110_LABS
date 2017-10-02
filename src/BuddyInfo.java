
public class BuddyInfo {
	private String address, name, phonenumber;
	
	public BuddyInfo(String address, String name, String phonenumber) {
		this.setAddress(address);
		this.setName(name);
		this.setPhonenumber(phonenumber);
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

}
