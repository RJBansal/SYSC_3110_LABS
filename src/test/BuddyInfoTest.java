package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import AddressBook.BuddyInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class BuddyInfoTest.
 */
public class BuddyInfoTest {
	
	/** The bd 1. */
	private BuddyInfo bd1;
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		bd1= new BuddyInfo("Joe", "Carleton", "613-9998888",19);
		new BuddyInfo("Matt", "Guelph", "613-8889999",20);
		new BuddyInfo("John", "Toronto", "613-7774444",13);
	}

	/**
	 * Test copy constructor.
	 */
	@Test
	public void testCopyConstructor() {
		BuddyInfo cb = new BuddyInfo(bd1);
		assertTrue(bd1.equals(cb));
	}
	
	/**
	 * Test greeting.
	 */
	@Test
	public void testGreeting() {
		BuddyInfo cb = new BuddyInfo(bd1);
		assertEquals("Welcome "+cb.getName(),cb.Welcome());
	}
	
	/**
	 * Test age.
	 */
	@Test
	public void testAge() {
		bd1.setAge(16);
		assertEquals(16,bd1.getAge());
	}
	
	/**
	 * Test age over 18.
	 */
	@Test
	public void testAgeOver18() {
		bd1.setAge(25);
		assertTrue(bd1.isOver18());
	}

}
