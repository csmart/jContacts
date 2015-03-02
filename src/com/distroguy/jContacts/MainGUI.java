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
import javax.swing.table.DefaultTableModel;

/**
 * @author Chris Smart <distroguy@gmail.com>
 * GPLv3+
 *
 */

public class MainGUI implements Runnable, ActionListener, MouseListener {
	// create our organiser
	Organiser organiser;

	// Create our canvas
	JFrame organiserFrame;
	Container organiserPane;
	JFileChooser fileChooser;
	JTable organiserTable;
	DefaultTableModel organiserTableModel;

	public MainGUI(){
		// Invoke the main UI later to avoid race conditions
		SwingUtilities.invokeLater(this);
	}

	// method for menu items
	public JMenuItem addMenuItems(String title, JMenu menu){
		JMenuItem newMenuItem = new JMenuItem(title);
		newMenuItem.addActionListener(this);
		newMenuItem.setActionCommand(title.toUpperCase() + "_PRESSED");
		menu.add(newMenuItem);
		return newMenuItem;
	}

	// method for creating buttons
	public JButton addButtons(String title, JPanel buttonsPanel){
		JButton newButton = new JButton(title);
		newButton.addActionListener(this);
		newButton.setActionCommand(title.toUpperCase() + "_PRESSED");
		buttonsPanel.add(newButton);
		return newButton;
	}

	public void run(){
		// Set up data
		organiser = new Organiser();

		// Print out objects in arraylist
		for (Contact thisContact : organiser.getContacts()){
			System.out.println(thisContact.getFirstname());
		}

		// Create frame for application and set exit
		organiserFrame = new JFrame("Organiser");
		organiserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Explicitly set layout to be BorderLayout in case defaults change in future
		organiserPane  = organiserFrame.getContentPane();
		organiserPane.setLayout(new BorderLayout());

		// Create table to hold our data
		organiserTableModel = new DefaultTableModel();
		organiserTable = new JTable(organiserTableModel);

		// Create data and populate table
		String[] tableColumns = Contact.getAttributes();
		Object[][] tableData = organiser.getStrings();
		organiserTableModel.setDataVector(tableData, tableColumns);

		JScrollPane tablePane = new JScrollPane(organiserTable);
		organiserTable.setFillsViewportHeight(true);
		organiserTable.addMouseListener(this);

		// Create file chooser
		fileChooser = new JFileChooser();

		// Add the menu to the top of the window, where we can exit and perform tasks
		JMenuBar organiserMenuBar = new JMenuBar();
		JMenu organiserFileMenu = new JMenu("File");
		JMenu organiserEditMenu = new JMenu("Edit");
		JMenu organiserHelpMenu = new JMenu("Help");
		organiserMenuBar.add(organiserFileMenu);
		organiserMenuBar.add(organiserEditMenu);
		organiserMenuBar.add(organiserHelpMenu);

		// Add items in the File menu
		JMenuItem quitMenuItem = addMenuItems("Quit", organiserFileMenu);

		// Add items in the Edit menu
		JMenuItem addMenuItem = addMenuItems("Add", organiserEditMenu);
		JMenuItem editMenuItem = addMenuItems("Edit", organiserEditMenu);
		JMenuItem deleteMenuItem = addMenuItems("Delete", organiserEditMenu);

		//Add about item to help menu
		JMenuItem aboutMenuItem = addMenuItems("About", organiserHelpMenu);

		// Add the menu to the frame
		organiserFrame.setJMenuBar(organiserMenuBar);

		// Add filter panel to the top of the window
		JPanel filterPanel = new JPanel();
		filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.X_AXIS));

		// Add search results to the center of the window
		// Create a filter to search for names and add it to the panel
		JTextField filter = new JTextField();
		filter.setText("Type here to filter names");
		filterPanel.add(filter);
		JButton clearButton = addButtons("Clear", filterPanel);

		// Add buttons to the bottom of the window
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

		// Create the buttons
		JButton addButton = addButtons("Add", buttonsPanel);
		JButton editButton = addButtons("Edit", buttonsPanel);
		JButton deleteButton = addButtons("Delete", buttonsPanel);
		buttonsPanel.add(Box.createHorizontalGlue()); // Create space so others are on the right
		JButton saveButton = addButtons("Save", buttonsPanel);
		JButton quitButton = addButtons("Quit", buttonsPanel);

		// Create main layout
		organiserPane.add(filterPanel, BorderLayout.PAGE_START);
		organiserPane.add(tablePane, BorderLayout.CENTER);
		organiserPane.add(buttonsPanel, BorderLayout.PAGE_END);

		// Pack everything and display the window		
		organiserFrame.setMinimumSize(new Dimension(800, 600));
		organiserFrame.pack();
		organiserFrame.setVisible(true);
	}

	private Object[][] getTableData(ArrayList<Contact> contacts) {
		System.out.println("number of contacts " + contacts.size() + " number of attributes " + Contact.getAttributes().length);
		Object[][] tableData = new Object[contacts.size()][Contact.getAttributes().length];
		int i = 0;
		for (Contact thisContact : contacts){
			tableData[i][0] = thisContact.getFirstname();
			tableData[i][1] = thisContact.getMiddlename();
			tableData[i][2] = thisContact.getLastname();
			tableData[i][3] = thisContact.getPhone();
			tableData[i][4] = thisContact.getMobile();
			i++;
		}
		return tableData;
	}

	public static void main(String[] args) {
		new MainGUI();		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("QUIT_PRESSED")){
			//probably should call some clean up code
			if ((JOptionPane.showConfirmDialog(organiserFrame, "Are you really a quitter!?")) == 0){
				System.out.println("Bye, bye!");
				System.exit(0);
			};
		}else if (e.getActionCommand().equals("SAVE_PRESSED")) {
			int res = fileChooser.showSaveDialog(organiserFrame);
			if ( res == fileChooser.APPROVE_OPTION ){
				File file = fileChooser.getSelectedFile();
				System.out.println(file.getName());
				//call save function and pass file?
			}
		}else {
			//just print what we pressed, for now
			JOptionPane.showMessageDialog(organiserFrame, "You pressed " + e.getActionCommand().trim().split("_PRESSED")[0].toLowerCase());
			System.out.println(e.getActionCommand());			
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//		if (e.getClickCount() == 2){
		System.out.println("Clicked " + organiserTable.getSelectedRow());
		//			organiserTableModel.setRowCount(0);
		//		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Pressed " + organiserTable.getSelectedRow());
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("Released " + organiserTable.getSelectedRow());

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("Entered " + organiserTable.getSelectedRow());		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("Exited " + organiserTable.getSelectedRow());
	}

}
