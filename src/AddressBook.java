import java.util.ArrayList;
import java.util.List;

public class AddressBook {
	private List<BuddyInfo> buddyInfo;
	
	public AddressBook() {
		this.buddyInfo = new ArrayList<BuddyInfo>();
	}
	
	public void addBuddy(BuddyInfo buddy) {
		if (buddy!= null) {
		this.buddyInfo.add(buddy);
		System.out.println("Added a Buddy!");
		}
	}
	
	public BuddyInfo removeBuddy(int index) {
		if(index >= 0 && index <this.buddyInfo.size()) {
			System.out.println("Removed a Buddy!");
			return this.buddyInfo.remove(index);
		}
		return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AddressBook list1 = new AddressBook();
		BuddyInfo bd1= new BuddyInfo("Joe", "Carleton", "613-9998888");
		BuddyInfo bd2 = new BuddyInfo("Matt", "Guelph", "613-8889999");
		BuddyInfo bd3 = new BuddyInfo("John", "Toronto", "613-7774444");
		
		list1.addBuddy(bd1);
		list1.addBuddy(bd2);
		list1.addBuddy(bd3);
		list1.removeBuddy(0);
		
		System.out.println("Updated Again and Again!");
	}
}