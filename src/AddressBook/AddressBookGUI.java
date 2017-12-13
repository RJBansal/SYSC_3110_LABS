/*
 * Address Book GUI
 * @author Jobin Mathew
 * @version 1.0
 */
package AddressBook;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.InputEvent;
import javax.swing.JFileChooser;

/**
 * The Class AddressBookGUI.
 */
@SuppressWarnings("serial")
public class AddressBookGUI extends JFrame {

	/** The content pane. */
	private JPanel contentPane;

	/** The name text field. */
	private JTextField nameTextField;

	/** The address text field. */
	private JTextField addressTextField;

	/** The phone text field. */
	private JTextField phoneTextField;

	/** The menu bar. */
	private JMenuBar menuBar;

	/** The address book menu. */
	private JMenu addressBookMenu;

	/** The create menu item. */
	private JMenuItem createMenuItem;

	/** The save menu item. */
	private JMenuItem saveMenuItem;

	/** The display menu item. */
	private JMenuItem displayMenuItem;

	/** The buddy info menu. */
	private JMenu buddyInfoMenu;

	/** The add buddy menu item. */
	private JMenuItem addBuddyMenuItem;

	/** The remove buddy menu item. */
	private JMenuItem removeBuddyMenuItem;

	/** The book. */
	private AddressBook book;

	/** The buddy info list. */
	private DefaultListModel<BuddyInfo> buddyInfoList = new DefaultListModel<>();

	/** The list. */
	private JList<BuddyInfo> list;

	/** The lbl address book. */
	private JLabel lblAddressBook;

	/** The edit buddy menu item. */
	private JMenuItem editBuddyMenuItem;

	/** The lbl age. */
	private JLabel lblAge;

	/** The age text field. */
	private JTextField ageTextField;

	/** The import menu item. */
	private JMenuItem importMenuItem;
	private JMenu mnImport;
	private JMenuItem mntmFromXml;
	private JFileChooser fileChooser;

	/**
	 * Launch the application.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddressBookGUI frame = new AddressBookGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddressBookGUI() {
		setResizable(false);
		setTitle("Address Book");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 500);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		addressBookMenu = new JMenu("Address Book");
		menuBar.add(addressBookMenu);

		createMenuItem = new JMenuItem("Create");
		createMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createMenuItemActionPerformed(e);
			}
		});
		addressBookMenu.add(createMenuItem);

		saveMenuItem = new JMenuItem("Save");
		saveMenuItem.setEnabled(false);
		saveMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveMenuItemActionPerformed(e);
			}
		});

		mnImport = new JMenu("Import");
		addressBookMenu.add(mnImport);

		importMenuItem = new JMenuItem("From Text File");
		mnImport.add(importMenuItem);

		mntmFromXml = new JMenuItem("From XML");
		mntmFromXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importXMLMenuItemActionPerformed(e);
			}
		});
		mnImport.add(mntmFromXml);
		importMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importTextMenuItemActionPerformed(e);
			}
		});
		addressBookMenu.add(saveMenuItem);

		displayMenuItem = new JMenuItem("Display");
		displayMenuItem.setEnabled(false);
		displayMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayMenuItemActionPerformed(e);
			}
		});
		addressBookMenu.add(displayMenuItem);

		buddyInfoMenu = new JMenu("Buddy Info");
		menuBar.add(buddyInfoMenu);

		addBuddyMenuItem = new JMenuItem("Add");
		addBuddyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		addBuddyMenuItem.setEnabled(false);
		addBuddyMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBuddyMenuItemActionPerformed(e);
			}
		});
		buddyInfoMenu.add(addBuddyMenuItem);

		removeBuddyMenuItem = new JMenuItem("Remove");
		removeBuddyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		removeBuddyMenuItem.setEnabled(false);
		removeBuddyMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeBuddyMenuItemActionPerformed(e);
			}
		});

		editBuddyMenuItem = new JMenuItem("Edit");
		editBuddyMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editBuddyMenuItemActionPerformed(e);
			}
		});
		editBuddyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		editBuddyMenuItem.setEnabled(false);
		buddyInfoMenu.add(editBuddyMenuItem);
		buddyInfoMenu.add(removeBuddyMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblName = new JLabel("Name:");

		nameTextField = new JTextField();
		nameTextField.setColumns(10);

		JLabel lblAddress = new JLabel("Address:");

		addressTextField = new JTextField();
		addressTextField.setColumns(10);

		JLabel lblPhoneNo = new JLabel("Phone #");

		phoneTextField = new JTextField();
		phoneTextField.setColumns(10);

		list = new JList<>(buddyInfoList);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		listScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		listScroller.setViewportView(list);
		list.setLayoutOrientation(JList.VERTICAL);
		contentPane.add(listScroller);

		lblAddressBook = new JLabel("Address Book");
		lblAddressBook.setHorizontalAlignment(SwingConstants.CENTER);

		lblAge = new JLabel("Age:");

		ageTextField = new JTextField();
		ageTextField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				gl_contentPane.createSequentialGroup().addContainerGap().addGroup(gl_contentPane
						.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblAddressBook, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
						.addComponent(listScroller, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblAddress, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(lblName, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblAge, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblPhoneNo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(nameTextField, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
										.addComponent(addressTextField, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
										.addComponent(phoneTextField, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
										.addComponent(ageTextField, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE))))
						.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblName).addComponent(
						nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblAddress).addComponent(
						addressTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblPhoneNo).addComponent(
						phoneTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblAge).addComponent(
						ageTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(lblAddressBook, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(listScroller, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(null);
	}

	/**
	 * Creates the menu item action performed.
	 *
	 * @param arg0
	 *            the arg 0
	 */
	private void createMenuItemActionPerformed(ActionEvent arg0) {
		book = new AddressBook();
		Popup.infoBox("Created an Address Book!", "CREATED");
		createMenuItem.setEnabled(false);
		addBuddyMenuItem.setEnabled(true);
		saveMenuItem.setEnabled(true);
		displayMenuItem.setEnabled(true);
	}

	/**
	 * Import menu item action performed.
	 *
	 * @param arg0
	 *            the arg 0
	 */
	private void importTextMenuItemActionPerformed(ActionEvent arg0) {
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
		fileChooser.setFileFilter(filter);
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			String s = getFileExtension(selectedFile.getName());
			if (s.equals("txt")) {
				try {
					@SuppressWarnings("resource")
					BufferedReader br = new BufferedReader(new FileReader(selectedFile));
					if (br.readLine() == null) {
						Popup.infoBox("The selected file is empty!", "NO DATA");
					} else
						book = new AddressBook();
					book.importFromFile(selectedFile);
					for (BuddyInfo buddy : book.buddyInfoList) {
						buddyInfoList.addElement(buddy);
					}
					Popup.infoBox("Created an Address Book from Text File!", "CREATED");
					createMenuItem.setEnabled(false);
					addBuddyMenuItem.setEnabled(true);
					editBuddyMenuItem.setEnabled(true);
					removeBuddyMenuItem.setEnabled(true);
					saveMenuItem.setEnabled(true);
					displayMenuItem.setEnabled(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else
				Popup.infoBox("Please select a .txt file!", "INVALID FILE TYPE");
		}
	}

	private void importXMLMenuItemActionPerformed(ActionEvent arg0) {
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".xml", "xml");
		fileChooser.setFileFilter(filter);
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			String s = getFileExtension(selectedFile.getName());
			if (s.equals("xml")) {
				try {
					@SuppressWarnings("resource")
					BufferedReader br = new BufferedReader(new FileReader(selectedFile));
					if (br.readLine() == null) {
						Popup.infoBox("The selected file is empty!", "NO DATA");
					} else
						book = new AddressBook();
					book.importFromXML(selectedFile);
					for (BuddyInfo buddy : book.buddyInfoList) {
						buddyInfoList.addElement(buddy);
					}
					Popup.infoBox("Created an Address Book from XML File!", "CREATED");
					createMenuItem.setEnabled(false);
					addBuddyMenuItem.setEnabled(true);
					editBuddyMenuItem.setEnabled(true);
					removeBuddyMenuItem.setEnabled(true);
					saveMenuItem.setEnabled(true);
					displayMenuItem.setEnabled(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else
				Popup.infoBox("Please select an XML file!", "INVALID FILE TYPE");
		}

	}

	/**
	 * Save menu item action performed.
	 *
	 * @param arg0
	 *            the arg 0
	 */
	private void saveMenuItemActionPerformed(ActionEvent arg0) {
		try {
			book.export();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Popup.infoBox("Saved the Address Book!", "SAVED");
	}

	/**
	 * Display menu item action performed.
	 *
	 * @param arg0
	 *            the arg 0
	 */
	private void displayMenuItemActionPerformed(ActionEvent arg0) {
		Popup.infoBox("HAHA Congradulations! You found the useless Menu Item", "EASTER EGG!!!");
	}

	/**
	 * Adds the buddy menu item action performed.
	 *
	 * @param arg0
	 *            the arg 0
	 */
	private void addBuddyMenuItemActionPerformed(ActionEvent arg0) {
		if (nameTextField.getText().equals("")) {
			Popup.infoBox("Please Enter a Name!", "MISSING INPUT FIELD");
		} else {
			try {
				int age = Integer.parseInt(ageTextField.getText());
				BuddyInfo buddy = new BuddyInfo(addressTextField.getText(), nameTextField.getText(),
						phoneTextField.getText(), age);
				if (book.addBuddy(buddy) == true) {
					buddyInfoList.addElement(buddy);
					editBuddyMenuItem.setEnabled(true);
					removeBuddyMenuItem.setEnabled(true);
				} else
					Popup.infoBox("The exact same buddy already exists in this Address Book!", "DUPLICATE");
			} catch (NumberFormatException e) {
				Popup.infoBox("Age only accepts numbers!", "INVALID AGE");
			}
		}
	}

	/**
	 * Edits the buddy menu item action performed.
	 *
	 * @param arg0
	 *            the arg 0
	 */
	private void editBuddyMenuItemActionPerformed(ActionEvent arg0) {
		if (list.isSelectionEmpty()) {

		} else {
			int selectedIndex = list.getSelectedIndex();
			try {
				int age = Integer.parseInt(ageTextField.getText());
				BuddyInfo buddy = new BuddyInfo(addressTextField.getText(), nameTextField.getText(),
						phoneTextField.getText(), age);
				if (selectedIndex != -1) {
					buddyInfoList.setElementAt(buddy, selectedIndex);
				}
				book.editBuddy(selectedIndex, buddy);
			} catch (NumberFormatException e) {
				Popup.infoBox("Age only accepts numbers!", "INVALID AGE");
			}
		}
	}

	/**
	 * Removes the buddy menu item action performed.
	 *
	 * @param arg0
	 *            the arg 0
	 */
	private void removeBuddyMenuItemActionPerformed(ActionEvent arg0) {
		if (list.isSelectionEmpty()) {

		} else {
			int selectedIndex = list.getSelectedIndex();
			if (selectedIndex != -1) {
				buddyInfoList.remove(selectedIndex);
			}
			book.removeBuddy(selectedIndex);
			if (buddyInfoList.getSize() == 0) {
				editBuddyMenuItem.setEnabled(false);
				removeBuddyMenuItem.setEnabled(false);
			}
		}
	}

	/**
	 * Gets the file extension.
	 *
	 * @param fullName
	 *            the full name
	 * @return the file extension
	 */
	public static String getFileExtension(String fullName) {
		if (fullName.equals(""))
			return "";
		else {
			String fileName = new File(fullName).getName();
			int dotIndex = fileName.lastIndexOf('.');
			return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
		}
	}
}