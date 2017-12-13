/*
 * Address Book Class
 * @author Jobin Mathew
 * @version 1.0
 */
package AddressBook;

import java.io.Serializable;

/**
 * The Class BuddyInfo.
 */
public class BuddyInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The phonenumber. */
	private String address, name, phonenumber;
	
	/** The age. */
	private int age;
	
	
	/**
	 * Instantiates a new buddy info.
	 *
	 * @param address the address
	 * @param name the name
	 * @param phonenumber the phonenumber
	 */
	public BuddyInfo(String name, String address, String phonenumber) {
		this.setAddress(address);
		this.setName(name);
		this.setPhonenumber(phonenumber);
		this.age = -1;
	}
	
	/**
	 * Instantiates a new buddy info.
	 *
	 * @param address the address
	 * @param name the name
	 * @param phonenumber the phonenumber
	 * @param age the age
	 */
	public BuddyInfo(String name, String address, String phonenumber, int age) {
		this.setAddress(address);
		this.setName(name);
		this.setPhonenumber(phonenumber);
		this.setAge(age);
	}
	
	/**
	 * Instantiates a new buddy info.
	 *
	 * @param buddysInfo the buddys info
	 */
	//////////////////////////////////////////////////////
	public BuddyInfo(BuddyInfo buddysInfo) {
		this.setAddress(buddysInfo.getAddress());
		this.setName(buddysInfo.getName());
		this.setPhonenumber(buddysInfo.getPhonenumber());
		this.setAge(buddysInfo.getAge());
		
	}
	
	/**
	 * Welcome.
	 *
	 * @return the string
	 */
	public String Welcome() {
		return"Welcome "+ getName();
	}
	
	/**
	 * Sets the age.
	 *
	 * @param age the new age
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Checks if is over 18.
	 *
	 * @return true, if is over 18
	 */
	public boolean isOver18() {
		if(age>18) return true;
		else return false;
	}
	
	/**
	 * Equals.
	 *
	 * @param b the b
	 * @return true, if successful
	 */
	public boolean equals(BuddyInfo b) {
		if(b.name.equals(this.name) && b.address.equals(this.address) && b.phonenumber.equals(this.phonenumber)
				&& b.age == this.age) {
			return true;
		}
		else return false;
	}
	
	/**
	 * Import from string.
	 *
	 * @param s the s
	 * @return the buddy info
	 */
	public static BuddyInfo importFromString(String s){
		String[] word = s.split("\\$");
		BuddyInfo buddy = new BuddyInfo(word[0], word[1], word[2]);
		return buddy;
	}
	////////////////////////////////////////////////////////
	
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the phonenumber.
	 *
	 * @return the phonenumber
	 */
	public String getPhonenumber() {
		return phonenumber;
	}
	
	/**
	 * Sets the phonenumber.
	 *
	 * @param phonenumber the new phonenumber
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name+"$"+address+"$"+phonenumber;
	}
	
	public String toXML(){
		String s = "";
		s = "\t<BuddyInfo>" + 
		"\n\t\t<name>" + name + "</name>" +
		"\n\t\t<address>" + address + "</address>" +
		"\n\t\t<phonenumber>" + phonenumber + "</phonenumber>" +
		"\n\t\t<age>" + Integer.toString(age) + "</age>" +
		"\n\t</BuddyInfo>\n";
		return s;
	}
}