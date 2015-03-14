package com.distroguy.jContacts;

import java.util.ArrayList;

// read prefs, set variables, i.e. data format (e.g. JSON/XML)
// create arraylist of contacts
// load in data from saved file/load()
// populate arraylist with contact objects based on file
// provide methods for:
	// getting all contact objects from arraylist
	// getting contact objects from arraylist based on regex
	// adding a new object
	// deleting an object
	// editing an object

public class Organiser {

	// Array list to store contact objects
	private ArrayList<Contact> contacts;

	// Preferences
	private String dataFormat = "json";

	public Organiser(){
		contacts = new ArrayList<Contact>();
		loadPrefs();
		load();
	}

	// Load database
	public int load(){
		// Read in database and build arraylist of contacts
		// TODO
		
		// Dummy data for now
		Contact newContact = new Contact();
		newContact.setSalutation("Mr");
		newContact.setFirstname("Chris");
//		newContact.setMiddlename("");
		newContact.setLastname("Smart");
		newContact.setPhone("026123456");
		newContact.setMobile("04123456");
		contacts.add(newContact);
		
		Contact newContact2 = new Contact();
		newContact2.setSalutation("Dr");
		newContact2.setFirstname("Ringo");
		newContact2.setMiddlename("the");
		newContact2.setLastname("Star");
		newContact2.setPhone("+12123456");
		newContact2.setMobile("+14123456");
		contacts.add(newContact2);

		// Check for success?
		return 0;
	}

	// Load database
	public int save(){
		// Write arraylist out to contact in format as per prefs
		return 0;
	}
	
	// Return an arraylist of all contacts
	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	// Return a filtered arraylist for searching
	public ArrayList<Contact> getContacts(String contact){
		//new array for filtered contact list
		ArrayList<Contact> contactsFiltered = new ArrayList<Contact>();
		//search through arraylist for String "contact", return new list
		return contactsFiltered;
	}

	// load in preferences and set variables
	public void loadPrefs(){
		System.out.println("Loading preferences");
	}
	
	// Return a 2-dimensional array of contact data (useful for table)
	public String[][] getStrings(){
		String[][] contactStrings = new String[contacts.size()][Contact.getAttributes().length];
		int i = 0;
		for (Contact thisContact : contacts){
			contactStrings[i] = thisContact.getStrings();
			i++;
		}
		return contactStrings;
	}
	
	// Create a new contact
	public void addContact(){
		Contact newContact = new Contact();
		contacts.add(newContact);
	}

	// Copy an existing contact to a new one
	public void copyContact(int user){
		Contact newContact = new Contact();
		String[] copyData = contacts.get(user).getStrings();
		newContact.setStrings(copyData);
		newContact.setUuid();
		contacts.add(newContact);
	}

	// Delete an existing contact
	public void delContact(Contact contact) {
		contacts.remove(contact);
	}

	// Return a contact object from arraylist
	public Contact getContact(int i) {
		return contacts.get(i);
	}

	// Return a contact object that matches given UUID
	public Contact getContact(String uuid) {
		for (Contact thisContact : contacts){
			if (thisContact.getUuid().equals(uuid));{
				System.out.println("removed " + thisContact.getFirstname());
				return(thisContact);
			}
		}
		return null;
	}

	// Update specific data for a contact
	public void updateData(int row, String columnName, String data) {
		contacts.get(row).setString(columnName, data);
	}

}
