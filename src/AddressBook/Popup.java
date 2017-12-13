/**
 * Just a simple popup class to create quick popup messages!
 * @author Jobin Mathew
 */
package AddressBook;

import javax.swing.JOptionPane;

/**
 * The Class Popup.
 */
public class Popup
{
	/*
	 * Make shift Popup box for prompts
	 * @param infoMessage: gets string to be displayed
	 * @param titleBar: name of the popup box 
	 */
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
