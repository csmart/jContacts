/**
 *
 * MainGui
 * 
 * This is the view component of the model-view-controller design pattern.
 * It is a graphical Swing implementation of the Organiser.
 * 
 * @author Chris Smart <distroguy@gmail.com>
 * @since 2015-02-01
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.distroguy.jContacts;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 * @author Chris Smart <distroguy@gmail.com>
 * GPLv3+
 *
 */

public class MainGUI implements Runnable, ActionListener, MouseListener,
		TableModelListener, DocumentListener {

	// create our organiser
	Organiser organiser;

	// Create our canvas
	JFrame organiserFrame;
	Container organiserPane;
	JFileChooser fileChooser;
	JTable organiserTable;
	DefaultTableModel organiserTableModel;
	String[] tableColumns;
	JTextField filter;
	JButton clearButton;

	// Constants for GUI
	private static final String ABOUT_MESSAGE = "This is an Organiser program which can maintain a contact list.\n\nCopyright Chris Smart <distroguy@gmail.com>\nLicensed under the GPLv3 or later.";
	private static final String FAILED_SAVE = "Failed to save file";
	private static final String FILE_SAVED = "File saved.";
	private static final String SAVE = "Save";

	public MainGUI() {
		// Invoke the main UI later to avoid race conditions
		SwingUtilities.invokeLater(this);
	}

	/**
	 * addMenuItems
	 * 
	 * Adds menu items to a Swing GUI menu, adds an action listener and command
	 * 
	 * @param Takes
	 *            a string for the title of the menu item
	 * @param Takes
	 *            a menu object to add the menu item to
	 * @return Returns the new menu item object
	 *
	 */
	public JMenuItem addMenuItems(String title, JMenu menu) {
		JMenuItem newMenuItem = new JMenuItem(title);
		newMenuItem.addActionListener(this);
		newMenuItem.setActionCommand(title.toUpperCase() + "_PRESSED");
		menu.add(newMenuItem);
		return newMenuItem;
	}

	/**
	 * addButtons
	 * 
	 * Adds buttons to a given Swing GUI JPanel object, adds an action listener
	 * and command
	 * 
	 * @param Takes
	 *            a string for the title of the button
	 * @param Takes
	 *            a JPanel object to add the button to
	 * @return Returns the new button object
	 *
	 */
	public JButton addButtons(String title, JPanel buttonsPanel) {
		JButton newButton = new JButton(title);
		newButton.addActionListener(this);
		newButton.setActionCommand(title.toUpperCase() + "_PRESSED");
		buttonsPanel.add(newButton);
		return newButton;
	}

	/**
	 * run
	 * 
	 * Initialises and sets the layout of the GUI, as well as interfacing with
	 * our controller to get the contact list dataset
	 * 
	 * @param Takes
	 *            nothing
	 * @return Returns nothing
	 *
	 */
	public void run() {
		// Set up data
		organiser = new Organiser();
		organiser.loadPrefs();
		organiser.load();
//		// debug - print out objects in arraylist
//		for (Contact thisContact : organiser.getContacts()) {
//			System.out.println(thisContact.getFirstname());
//		}

		// Create frame for application and set exit
		organiserFrame = new JFrame("Organiser");
		organiserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Explicitly set layout to be BorderLayout in case defaults change in
		// future
		organiserPane = organiserFrame.getContentPane();
		organiserPane.setLayout(new BorderLayout());

		// Create table to hold our contact list data, set model, add listener
		// so we can get data changes
		organiserTableModel = new DefaultTableModel();
		organiserTable = new JTable(organiserTableModel);
		organiserTable.getModel().addTableModelListener(this);

		// Create data and populate table
		tableColumns = Contact.getAttributes();
		setTableData();
		JScrollPane tablePane = new JScrollPane(organiserTable);
		organiserTable.setFillsViewportHeight(true);
		organiserTable.addMouseListener(this);

		// Create file chooser
		fileChooser = new JFileChooser();

		// Add the menu to the top of the window, where we can exit and perform
		// tasks
		JMenuBar organiserMenuBar = new JMenuBar();
		JMenu organiserFileMenu = new JMenu("File");
		JMenu organiserEditMenu = new JMenu("Edit");
		JMenu organiserHelpMenu = new JMenu("Help");
		organiserMenuBar.add(organiserFileMenu);
		organiserMenuBar.add(organiserEditMenu);
		organiserMenuBar.add(organiserHelpMenu);

		// Add items in the File menu
		JMenuItem loadMenuItem = addMenuItems("Load", organiserFileMenu);
		JMenuItem saveMenuItem = addMenuItems(SAVE, organiserFileMenu);
		JMenuItem saveasMenuItem = addMenuItems("Save As", organiserFileMenu);
		JMenuItem quitMenuItem = addMenuItems("Quit", organiserFileMenu);

		// Add items in the Edit menu
		JMenuItem addMenuItem = addMenuItems("Add", organiserEditMenu);
		JMenuItem copyMenuItem = addMenuItems("Copy", organiserEditMenu);
		JMenuItem editMenuItem = addMenuItems("Edit", organiserEditMenu);
		JMenuItem deleteMenuItem = addMenuItems("Delete", organiserEditMenu);

		// Add about item to help menu
		JMenuItem aboutMenuItem = addMenuItems("About", organiserHelpMenu);

		// Add the menu to the frame
		organiserFrame.setJMenuBar(organiserMenuBar);

		// Add filter panel to the top of the window
		JPanel filterPanel = new JPanel();
		filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.X_AXIS));

		// Add search results to the center of the window
		// Create a filter to search for names and add it to the panel
		filter = new JTextField();
		filter.setText("");
		filter.getDocument().addDocumentListener(this);
		filterPanel.add(filter);

		// Add buttons to the bottom of the window
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

		// Create the buttons
		JButton addButton = addButtons("Add", buttonsPanel);
		JButton copyButton = addButtons("Copy", buttonsPanel);
		JButton editButton = addButtons("Edit", buttonsPanel);
		JButton deleteButton = addButtons("Delete", buttonsPanel);
		buttonsPanel.add(Box.createHorizontalGlue()); // Create space so others
		// are on the right
		JButton loadButton = addButtons("Load", buttonsPanel);
		JButton saveButton = addButtons(SAVE, buttonsPanel);
		JButton quitButton = addButtons("Quit", buttonsPanel);

		// The clear button is special, only enable it when there is something
		// in the filter that we need to clear!
		clearButton = new JButton("Clear");
		clearButton.addActionListener(this);
		clearButton.setActionCommand("Clear".toUpperCase() + "_PRESSED");
		filterPanel.add(clearButton);
		clearButton.setEnabled(false);

		// Create main layout
		organiserPane.add(filterPanel, BorderLayout.PAGE_START);
		organiserPane.add(tablePane, BorderLayout.CENTER);
		organiserPane.add(buttonsPanel, BorderLayout.PAGE_END);

		// Pack everything and display the window
		organiserFrame.setMinimumSize(new Dimension(800, 600));
		organiserFrame.pack();
		organiserFrame.setVisible(true);
	}

	/**
	 * main
	 * 
	 * The entry point for starting the Organiser Swing GUI
	 * 
	 * @param Takes
	 *            array of strings for program arguments
	 * @return Returns nothing
	 *
	 */
	public static void main(String[] args) {
		new MainGUI();
	}

	/**
	 * addContact
	 * 
	 * Adds a new contact to the contact list via the controller, calls
	 * functions to update the table which redraws the GUI
	 * 
	 * @param Takes
	 *            nothing
	 * @return Returns nothing
	 *
	 */
	public void addContact() {
		organiser.addContact();
		clearTableData();
		setTableData();
	}

	/**
	 * copyContact
	 * 
	 * Copies an existing contact in the list and adds it to the list, calls
	 * functions to update the table which redraws the GUI
	 * 
	 * @param Takes
	 *            nothing
	 * @return Returns nothing
	 *
	 */
	private void copyContact() {
		int row = organiserTable.getSelectedRow();
		if (row != -1) {
			organiser.copyContact(organiserTable.getSelectedRow());
			clearTableData();
			setTableData();
		} else {
			JOptionPane.showMessageDialog(organiserFrame,
					"You didn't select a contact to copy.");
		}
	}

	/**
	 * delContact
	 * 
	 * Deletes a selected contact out of the contact list, calls functions to
	 * update the table which redraws the GUI
	 * 
	 * @param Takes
	 *            nothing
	 * @return Returns nothing
	 *
	 */
	public void delContact() {
		int row = organiserTable.getSelectedRow();
		if (row != -1) {
			// Temporary array to hold a list of contact objects we need to
			// delete
			ArrayList<Contact> deleteContacts = new ArrayList<Contact>();
			for (int i : organiserTable.getSelectedRows()) {
				System.out.print(i + " ("
						+ organiser.getContact(i).getFirstname() + ") ");
				// Build list of contacts to delete
				deleteContacts.add(organiser.getContact(i));
			}
			// Delete contacts here, no array indexing when deleting
			for (Contact contact : deleteContacts) {
				organiser.delContact(contact);
			}
			clearTableData();
			setTableData();
		} else {
			JOptionPane.showMessageDialog(organiserFrame,
					"You didn't select a contact to delete.");
		}

	}

	/**
	 * setTableData
	 * 
	 * Provides/updates the contact list dataset in the table
	 * 
	 * @param Takes
	 *            nothing
	 * @return Returns nothing
	 *
	 */
	public void setTableData() {
		organiserTableModel.setDataVector(getTableData(), tableColumns);
	}

	/**
	 * setTableData
	 * 
	 * Provides/updates the table with filter
	 * 
	 * @param Takes
	 *            nothing
	 * @return Returns nothing
	 *
	 */
	public void setTableData(String filter) {
		organiserTableModel.setDataVector(getTableData(filter), tableColumns);
	}

	/**
	 * clearTableData
	 * 
	 * Clears the contact list dataset in the table by setting it to nothing
	 * 
	 * @param Takes
	 *            nothing
	 * @return Returns nothing
	 *
	 */
	public void clearTableData() {
		organiserTableModel.setRowCount(0);
	}

	/**
	 * getTableData
	 * 
	 * Retrieves the contact list dataset via the controller for input into the
	 * table
	 * 
	 * @param Takes
	 *            nothing
	 * @return Returns two-dimensional array of Strings, which is the format the
	 *         table model wants
	 *
	 */
	public Object[][] getTableData() {
		Object[][] tableData = organiser.getStrings();
		return tableData;
	}

	/**
	 * getTableData
	 * 
	 * Filter the contact list
	 * 
	 * @param Takes
	 *            nothing
	 * @return Returns two-dimensional array of Strings, which is the format the
	 *         table model wants
	 *
	 */
	public Object[][] getTableData(String filter) {
		Object[][] tableData = organiser.getStrings(filter);
		return tableData;
	}

	/**
	 * showButton
	 * 
	 * Enables or disables a button
	 * 
	 * @param String
	 * @return Returns nothing
	 *
	 */
	private void showButton(String state) {
		if (state.equals("")) {
			clearButton.setEnabled(false);
		} else {
			clearButton.setEnabled(true);
		}
	}

	@Override
	/**
	 * actionPerformed
	 * 
	 * Override for GUI driven ActionEvents, such as clicking a button, etc
	 * 
	 * @param Takes an ActionEvent
	 * @return Returns nothing
	 *
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("QUIT_PRESSED")) {
			// probably should call some clean up code
			if ((JOptionPane.showConfirmDialog(organiserFrame,
					"Are you really a quitter!?")) == 0) {
				System.out.println("Bye, bye!");
				System.exit(0);
			}
		} else if (e.getActionCommand().equals("ADD_PRESSED")) {
			addContact();
		} else if (e.getActionCommand().equals("COPY_PRESSED")) {
			copyContact();
		} else if (e.getActionCommand().equals("DELETE_PRESSED")) {
			delContact();
		} else if (e.getActionCommand().equals("SAVE AS_PRESSED")) {
			int res = fileChooser.showSaveDialog(organiserFrame);
			if (res == fileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				organiser.setFilename(file.getAbsolutePath());
				if (organiser.save()){
					JOptionPane.showMessageDialog(organiserFrame, FILE_SAVED, SAVE, 1);
				}else{
					JOptionPane.showMessageDialog(organiserFrame, FAILED_SAVE, SAVE, 0);
				}
			}
		} else if (e.getActionCommand().equals("LOAD_PRESSED")) {
			int res = fileChooser.showOpenDialog(organiserFrame);
			if (res == fileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				organiser.setFilename(file.getAbsolutePath());
				organiser.load();
				clearTableData();
				setTableData();
			}
		} else if (e.getActionCommand().equals("CLEAR_PRESSED")) {
			filter.setText("");
		} else if (e.getActionCommand().equals("SAVE_PRESSED")) {
			if (organiser.save()){
				JOptionPane.showMessageDialog(organiserFrame, FILE_SAVED, SAVE, 1);
			}else{
				JOptionPane.showMessageDialog(organiserFrame, FAILED_SAVE, SAVE, 0);
			}
		} else if (e.getActionCommand().equals("ABOUT_PRESSED")) {
			JOptionPane.showMessageDialog(organiserFrame, ABOUT_MESSAGE,
					"About", 1);
		} else {
			// just print what we pressed, for now
			JOptionPane.showMessageDialog(
					organiserFrame,
					"You pressed "
							+ e.getActionCommand().trim().split("_PRESSED")[0]
									.toLowerCase());
			System.out.println(e.getActionCommand());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// System.out.println("Pressed " + organiserTable.getSelectedRow());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// System.out.println("Pressed " + organiserTable.getSelectedRow());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// System.out.println("Released " + organiserTable.getSelectedRow());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// System.out.println("Entered " + organiserTable.getSelectedRow());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// System.out.println("Exited " + organiserTable.getSelectedRow());
	}

	@Override
	/**
	 * tableChanged
	 * 
	 * Update the contact list dataset when a user updates data in the table
	 * 
	 * @param Takes an TableModelEvent
	 * @return Returns nothing
	 *
	 */
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
		int column = e.getColumn();
		// Only update the data if we have an update event and it's not an
		// initial
		// draw of the table (which gives -1) else this throws an exception on
		// startup
		if ((e.getType() == TableModelEvent.UPDATE) && (row != -1)
				&& (column != -1)) {
			String columnName = organiserTableModel.getColumnName(column);
			String data = organiserTableModel.getValueAt(row, column)
					.toString();
			// Because users can shuffle the columns, we can't use column number
			organiser.updateData(row, columnName, data);
			System.out.println("row = " + row + " column = " + column
					+ " name = " + columnName + " data = " + data);
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		clearTableData();
		setTableData(filter.getText());
		System.out.println(filter.getText());
		showButton(filter.getText());
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		clearTableData();
		setTableData(filter.getText());
		System.out.println(filter.getText());
		showButton(filter.getText());
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		clearTableData();
		setTableData(filter.getText());
		showButton(filter.getText());
	}
}
