/*
 * Model Class for the address book.
 * @author Jobin Mathew
 */
package AddressBook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import org.xml.sax.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

// TODO: Auto-generated Javadoc
/**
 * The Class AddressBook.
 */
public class AddressBook implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The buddy info list. */
	public ArrayList<BuddyInfo> buddyInfoList;

	/**
	 * Instantiates a new address book.
	 */
	public AddressBook() {
		this.buddyInfoList = new ArrayList<BuddyInfo>();
	}

	/**
	 * Size.
	 *
	 * @return the int
	 */
	////////////////////////////////////////////////////
	public int size() {
		return buddyInfoList.size();
	}

	/**
	 * Clear.
	 */
	public void clear() {
		buddyInfoList.clear();
		System.out.println("Cleared all buddies!");
	}
	////////////////////////////////////////////////////

	/**
	 * Adds the buddy.
	 *
	 * @param buddy
	 *            the buddy
	 * @return true, if successful
	 */
	public boolean addBuddy(BuddyInfo buddy) {
		if (buddy != null) {
			for (BuddyInfo testbuddy : this.buddyInfoList) {
				if (buddy.equals(testbuddy)) {
					System.out.println("Same Buddies!");
					return false;
				}
			}
			this.buddyInfoList.add(buddy);
			System.out.println("Added a Buddy!");
			System.out.println(buddy.Welcome());
		}
		return true;
	}

	/**
	 * Edits the buddy.
	 *
	 * @param index
	 *            the index
	 * @param buddy
	 *            the buddy
	 */
	public void editBuddy(int index, BuddyInfo buddy) {
		if (index >= 0) {
			this.buddyInfoList.set(index, buddy);
			System.out.println("Edited a Buddy!");
		}
	}

	/**
	 * Removes the buddy.
	 *
	 * @param index
	 *            the index
	 * @return the buddy info
	 */
	public BuddyInfo removeBuddy(int index) {
		if (index >= 0 && index < this.buddyInfoList.size()) {
			System.out.println("Removed a Buddy!");
			return this.buddyInfoList.remove(index);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String output = "";
		for (BuddyInfo infobuddy : buddyInfoList) {
			output += infobuddy.toString() + "\n";
		}
		return output;
	}

	/**
	 * Export.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void export() throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter("AddressBook.txt"));
		for (BuddyInfo buddy : buddyInfoList) {
			String s = buddy.toString();
			System.out.println(s);
			out.write(s);
			out.newLine();
		}
		out.close();
	}

	/**
	 * Import from file.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void importFromFile(File selectedFile) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(selectedFile));
		try {
			String line = br.readLine();
			while (line != null) {
				System.out.println(line);
				this.addBuddy(BuddyInfo.importFromString(line));
				line = br.readLine();
			}
		} finally {
			br.close();
		}
	}

	/**
	 * Serialized export.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void serializedExport() throws IOException {
		FileOutputStream out = new FileOutputStream("SerializedAddressBook.txt");
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(this);
		oos.close();
		out.close();
	}

	/**
	 * Serialized import.
	 *
	 * @return the address book
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public AddressBook serializedImport() throws IOException {
		FileInputStream input = new FileInputStream("SerializedAddressBook.txt");
		ObjectInputStream ois = new ObjectInputStream(input);
		AddressBook book = new AddressBook();
		try {
			book = (AddressBook) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ois.close();
		input.close();
		return book;
	}

	/**
	 * Equals.
	 *
	 * @param book
	 *            the book
	 * @return true, if successful
	 */
	public boolean equals(AddressBook book) {
		if (this.toString().equals(book.toString()))
			return true;
		else
			return false;
	}

	public String toXML() {
		String s = "";
		String b = "";
		for (BuddyInfo buddy : this.buddyInfoList) {
			b += buddy.toXML();
		}
		s = "<AddressBook>\n" + b + "</AddressBook>\n";
		return s;
	}

	public void exportToXML() throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter("AddressBook.xml"));
		String s = this.toXML();
		System.out.println(s);
		out.write(s);
		out.newLine();

		out.close();
	}
	
	public static String indentXMLString(String input, int indent) {
	    try {
	        Source xmlInput = new StreamSource(new StringReader(input));
	        StringWriter stringWriter = new StringWriter();
	        StreamResult xmlOutput = new StreamResult(stringWriter);
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        transformerFactory.setAttribute("indent-number", indent);
	        Transformer transformer = transformerFactory.newTransformer(); 
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	        transformer.transform(xmlInput, xmlOutput);
	        return xmlOutput.getWriter().toString();
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

	public static String indentXMLString(String input) {
	    return indentXMLString(input, 4);
	}

	public void importFromXML(File selectedFile) throws IOException, ParserConfigurationException, SAXException {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			XMLParser xml = new XMLParser();
			saxParser.parse(selectedFile, xml);
			this.buddyInfoList = XMLParser.book.buddyInfoList;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}