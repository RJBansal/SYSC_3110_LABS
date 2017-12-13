package AddressBook;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParser extends DefaultHandler {
	public static AddressBook book = new AddressBook();
	boolean name = false, address = false, phonenumber = false, age = false;
	String newName;
	String newAddress;
	String newPhoneNumber;
	int newAge;
	BuddyInfo buddy;

	public void startElement(String u, String ln, String qName, Attributes a) throws SAXException {
		if (qName.equals("BuddyInfo")) {
		}
		if (qName.equalsIgnoreCase("name")) {
			name = true;
		}
		if (qName.equalsIgnoreCase("address")) {
			address = true;
		}
		if (qName.equalsIgnoreCase("phonenumber")) {
			phonenumber = true;
		}
		if (qName.equalsIgnoreCase("age")) {
			age = true;
		}
	}

	public void endElement(String url, String localName, String qName) throws SAXException{
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		if (name) {
			newName = new String(ch, start, length);
		}
		if (address) {
			newAddress = new String(ch, start, length);
		}
		if (phonenumber) {
			newPhoneNumber = new String(ch, start, length);
		}
		if (age) {
			newAge = Integer.parseInt(new String(ch, start, length));
		}
		if (name && address && phonenumber && age) {
			System.out.println(newName + " " + newAddress + " " + newPhoneNumber + " " + newAge);
			buddy = new BuddyInfo(newName, newAddress, newPhoneNumber, newAge);
			book.addBuddy(buddy);
			name = false;
			address = false;
			phonenumber = false;
			age = false;
		}
	}
}