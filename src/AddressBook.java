import java.util.*;

public class AddressBook {
	ArrayList<BuddyInfo> buddylist;
	public AddressBook() {
		buddylist = new ArrayList<BuddyInfo>();
	}
	
	public boolean addBuddy(BuddyInfo buddy) {
		buddylist.add(buddy);
		System.out.println("Added a Buddy!");
		return true;
	}
	
	public boolean removeBuddy(BuddyInfo buddy) {
		buddylist.remove(buddy);
		System.out.println("Removed a Buddy!");
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AddressBook list1 = new AddressBook();
		BuddyInfo bd1= new BuddyInfo();
		BuddyInfo bd2 = new BuddyInfo();
		BuddyInfo bd3 = new BuddyInfo();
		
		bd1.setName("Joe");
		bd1.setAddress("Carleton");
		bd1.setPhonenumber("613-9998888");
		
		bd2.setName("Matt");
		bd2.setAddress("Guelph");
		bd2.setPhonenumber("613-8889999");
		
		bd3.setName("John");
		bd3.setAddress("Toronto");
		bd3.setPhonenumber("613-7774444");
		
		list1.addBuddy(bd1);
		list1.addBuddy(bd2);
		list1.addBuddy(bd3);
		list1.removeBuddy(bd1);
		
		System.out.println("Updated!");
	}
}
