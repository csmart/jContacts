/**
 * 
 * Organiser
 * 
 * This is the controller component of the model-view-controller design pattern.
 * It holds the program data and provides methods for controlling the data.
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

import java.util.ArrayList;

/**
 * Organiser
 * 
 * The Organiser class which holds the data of the program and provides methods
 * for getting and setting that data.
 * 
 * @param Takes nothing
 * @return Returns nothing
 *
 */
public class Organiser {

	// Array list to store contact objects
	private ArrayList<Contact> contacts;
	private Database data;

	// Default preferences
	// Preferences prefs = Preferences. // TO-DO
	private String dataFormat = "serial";
	private String filename = "sample.ser";

	/**
	 * Organiser
	 * 
	 * Default constructor for the Organiser object
	 * 
	 * @param Takes nothing
	 * @return Returns nothing
	 *
	 */
	public Organiser() {
		contacts = new ArrayList<Contact>();
		data = new Database();
	}

	/**
	 * load
	 * 
	 * Calls the Database class to read in any persistent dataset from disk and
	 * populates the contact list with it
	 * 
	 * @param Takes nothing
	 * @return Returns nothing
	 *
	 */
	public Boolean load() {
		// Create a new file for loading and saving, passing new filename from prefs if exists
		data = new Database(filename);
		// Stub for setting the file format
		data.setFormat(dataFormat); // TO-DO
		// If the file exists, then let's load the contacts into the ArrayList
		if (data.checkExists()) {
			ArrayList<Contact> contactsTemp = data.readDataset();
			if (! contactsTemp.isEmpty()){
				contacts = contactsTemp;
				return true;
			}
		}
		return false;
	}

	/**
	 * save
	 * 
	 * Calls the Database class to write out the contact list to disk
	 * 
	 * @param Takes nothing
	 * @return Returns nothing
	 *
	 */
	public Boolean save() {
		return data.saveDataset(contacts);
	}

	/**
	 * setFileName
	 * 
	 * Passes the name of a file we want to use for loading and saving to the
	 * Database class.
	 * 
	 * @param Takes nothing
	 * @return Returns nothing
	 *
	 */
	public void setFilename(String filename) {
		this.filename = filename;
		data.setFilename(filename);
	}

	/**
	 * getFilename
	 * 
	 * Returns the current filename
	 * 
	 * @param Takes nothing
	 * @return Returns String of file name
	 *
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * getContacts
	 * 
	 * Gets the current dataset in the contact list
	 * 
	 * @param Takes nothing
	 * @return Returns an arraylist of Contact objects
	 *
	 */
	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	/**
	 * getContacts
	 * 
	 * Gets a filtered set of data from the contact list dataset, for searching
	 * 
	 * @param Takes a String to match a Contact with
	 * @return Returns an ArrayList of matching Contact objects
	 *
	 */
	public ArrayList<Contact> getContacts(String contact) {
		ArrayList<Contact> contactsFiltered = new ArrayList<Contact>();
		// TO-DO search through arraylist for String "contact", return new list
		return contactsFiltered;
	}

	/**
	 * loadPrefs
	 * 
	 * Loads in the preferences and sets program variables based on the data
	 * 
	 * @param Takes nothing
	 * @return Returns nothing
	 *
	 */
	public void loadPrefs() {
		// TO-DO
		System.out.println("Loading preferences");
	}

	/**
	 * getStrings
	 * 
	 * Formats the dataset in two-dimensional array
	 * 
	 * @param Takes nothing
	 * @return Returns two-dimensional String array with complete contact list dataset
	 *
	 */
	public String[][] getStrings() {
		String[][] contactStrings = new String[contacts.size()][Contact
				.getAttributes().length];
		int i = 0;
		for (Contact thisContact : contacts) {
			contactStrings[i] = thisContact.getStrings();
			i++;
		}
		return contactStrings;
	}

	/**
	 * getStrings
	 * 
	 * Loads in the preferences and sets program variables based on the data
	 * 
	 * @param Takes nothing
	 * @return Returns two-dimensional String array with complete contact list dataset
	 *
	 */
	public String[][] getStrings(String filter) {
		filter = filter.toLowerCase();
		String[][] contactStrings = new String[contacts.size()][Contact
				.getAttributes().length];
		int i = 0;
		for (Contact thisContact : contacts) {
			String[] strings = thisContact.getStrings();
			if (thisContact.getFirstname().toLowerCase().contains(filter)
					|| thisContact.getMiddlename().toLowerCase()
							.contains(filter)
					|| thisContact.getLastname().toLowerCase().contains(filter)) {
				contactStrings[i] = strings;
				i++;
			}
		}
		// We only want to return an array with filtered objects
		String[][] contactStringsFiltered = new String[i][Contact
				.getAttributes().length];
		for (int j = 0; j < i; j++) {
			contactStringsFiltered[j] = contactStrings[j];
		}
		return contactStringsFiltered;
	}

	/**
	 * addContact
	 * 
	 * Adds a new Contact object to the contact list
	 * 
	 * @param Takes nothing
	 * @return Returns nothing
	 *
	 */
	public void addContact() {
		Contact newContact = new Contact();
		contacts.add(newContact);
	}

	/**
	 * copyContact
	 * 
	 * Copies an existing Contact to a new Contact object in the contact list
	 * 
	 * @param Takes an int index of the Contact list array which is the one to copy from
	 * @return Returns nothing
	 *
	 */
	public void copyContact(int user) {
		Contact newContact = new Contact();
		String[] copyData = contacts.get(user).getStrings();
		newContact.setStrings(copyData);
		newContact.setUuid();
		contacts.add(newContact);
	}

	/**
	 * delContact
	 * 
	 * Deletes an existing Contact from the contact list
	 * 
	 * @param Takes a Contact object to delete
	 * @return Returns nothing
	 *
	 */
	public void delContact(Contact contact) {
		contacts.remove(contact);
	}

	/**
	 * getContact
	 * 
	 * Gets a Contact from the contact list
	 * 
	 * @param Takes an int index of the list array pointing to the Contact to get
	 * @return Returns Contact object
	 *
	 */
	public Contact getContact(int i) {
		return contacts.get(i);
	}

	/**
	 * getContact
	 * 
	 * Gets a Contact from the contact list
	 * 
	 * @param Takes a string to match a UUID with a Contact object in contact list
	 * @return Returns Contact object
	 *
	 */
	public Contact getContact(String uuid) {
		for (Contact thisContact : contacts) {
			if (thisContact.getUuid().equals(uuid)){
				return (thisContact);
			}
		}
		return null;
	}

	/**
	 * updateData
	 * 
	 * Updates a particular attribute for a Contact object
	 * 
	 * @param Takes an int index of the list array pointing to the contact to set
	 * @param Takes a String with the name of the attribute to set
	 * @param Takes a String with the value of the attribute to set
	 * @return Returns nothing
	 *
	 */
	public void updateData(int row, String columnName, String data) {
		contacts.get(row).setString(columnName, data);
	}

}
