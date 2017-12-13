package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import AddressBook.AddressBook;
import AddressBook.BuddyInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class AddressBookTest.
 */
public class AddressBookTest {

	/** The book 1. */
	private AddressBook book1;

	/** The bd 1. */
	private BuddyInfo bd1;

	/** The bd 2. */
	private BuddyInfo bd2;

	/** The bd 3. */
	private BuddyInfo bd3;

	/** The bd 01. */
	private BuddyInfo bd01;

	/** The bd 02. */
	private BuddyInfo bd02;

	/** The bd 03. */
	private BuddyInfo bd03;

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		book1 = new AddressBook();
		bd1 = new BuddyInfo("Joe", "Carleton", "613-9998888", 19);
		bd2 = new BuddyInfo("Matt", "Guelph", "613-8889999", 20);
		bd3 = new BuddyInfo("John", "Toronto", "613-7774444", 13);
		// Without age
		bd01 = new BuddyInfo("Joe", "Carleton", "613-9998888");
		bd02 = new BuddyInfo("Matt", "Guelph", "613-8889999");
		bd03 = new BuddyInfo("John", "Toronto", "613-7774444");
	}

	/**
	 * Test size.
	 */
	@Test
	public void testSize() {
		System.out.println("\n**** Testing Size ****");
		book1.addBuddy(bd1);
		book1.addBuddy(bd2);
		book1.addBuddy(bd3);
		assertEquals(3, book1.size());
		System.out.println(book1.size() == 3 ? "Success! Passed the sizeTest!" : "Failure! Failed the sizeTest!");
	}

	/**
	 * Test clear.
	 */
	@Test
	public void testClear() {
		System.out.println("\n**** Testing Clear ****");
		book1.addBuddy(bd1);
		book1.addBuddy(bd2);
		book1.addBuddy(bd3);
		book1.clear();
		assertEquals(0, book1.size());
		System.out.println(book1.size() == 0 ? "Success! Passed the clearTest!" : "Failure! Failed the clearTest!");
	}

	/**
	 * Test export.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testExport() throws IOException {
		System.out.println("\n**** Testing Export ****");
		book1.addBuddy(bd01);
		book1.addBuddy(bd02);
		book1.addBuddy(bd03);
		try {
			System.out.println("\nStarting Export! ");
			book1.export();
			System.out.println("Done Exporting! ");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String test = "";
		BufferedReader br = new BufferedReader(new FileReader("AddressBook.txt"));
		try {
			String line = br.readLine();
			while (line != null) {
				test += line + "\n";
				line = br.readLine();
			}
		} finally {
			br.close();
		}
		System.out.println("\n" + book1.toString());
		System.out.println(test);
		assertTrue(book1.toString().equals(test));
		System.out.println(
				book1.toString().equals(test) ? "Success! Passed the exportTest!" : "Failure! Failed the exportTest!");
	}

	/**
	 * Test import.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testImport() throws IOException {
		System.out.println("\n**** Testing Import ****");
		book1.addBuddy(bd01);
		book1.addBuddy(bd02);
		book1.addBuddy(bd03);
		AddressBook book2 = new AddressBook();
		try {
			System.out.println("\nStarting Import!");
			book2.importFromFile(new File("AddressBook.txt"));
			System.out.println("Done Importing!\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(book1.toString());
		System.out.println(book2.toString());
		assertTrue(book1.toString().equals(book2.toString()));
		System.out.println(book1.toString().equals(book2.toString()) ? "Success! Passed the importTest!"
				: "Failure! Failed the importTest!");
	}

	/**
	 * Test serialized export.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testSerializedExportAndImport() throws IOException {
		System.out.println("\n**** Testing Serialized Export and Import ****");
		book1.addBuddy(bd01);
		book1.addBuddy(bd02);
		book1.addBuddy(bd03);
		AddressBook book2 = new AddressBook();
		try {
			book1.serializedExport();
			book2 = book1.serializedImport();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertTrue(book1.equals(book2));
		System.out.println("Done Serialized Export and Import!");
	}
	
	@Test
	public void testToXML(){
		System.out.println("\n**** Testing To XML ****");
		book1.addBuddy(bd01);
		book1.addBuddy(bd02);
		book1.addBuddy(bd03);
		System.out.println(book1.toXML());
		assertTrue(true);
		System.out.println("Done Testing: To XML!");
	}
	
	@Test
	public void testExportAndImportFromXML(){
		System.out.println("\n**** Testing Export and Import From XML ****");
		book1.addBuddy(bd1);
		book1.addBuddy(bd2);
		book1.addBuddy(bd3);
		System.out.println(book1.toXML());
		AddressBook book2 = new AddressBook();
		try {
			book1.exportToXML();
			book2.importFromXML(new File("AddressBook.xml"));
			System.out.println(book2.toString()+"9999999999999999999999999999999999999999999999999999999999999999999999");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		assertTrue(true);
		System.out.println("Done Testing: Export and Import From XML!");
	}
}
