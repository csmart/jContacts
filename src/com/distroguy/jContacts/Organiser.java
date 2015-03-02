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

	// array list to store contact objects
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
		//read in database
		//build arraylist of contacts
		Contact newContact = new Contact();
		newContact.setSalutation("Mr");
		newContact.setFirstname("Chris");
		newContact.setMiddlename("David");
		newContact.setLastname("Smart");
		newContact.setPhone(123);
		newContact.setMobile(456);
		contacts.add(newContact);
		Contact newContact2 = new Contact();
		newContact2.setSalutation("Dr");
		newContact2.setFirstname("Paul");
		newContact2.setMiddlename("Ringo");
		newContact2.setLastname("Beatle");
		newContact2.setPhone(789);
		newContact2.setMobile(000);
		contacts.add(newContact2);

		return 0;
	}

	// Load database
	public int save(){
		//write arraylist out to contact in format as per prefs
		return 0;
	}
	
	// Return an arraylist of all contacts
	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	// return a filtered arraylist
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
	
	public String[][] getStrings(){
		String[][] contactStrings = new String[contacts.size()][Contact.getAttributes().length];
		int i = 0;
		for (Contact thisContact : contacts){
			contactStrings[i] = thisContact.getStrings();
			i++;
		}
		return contactStrings;
	}

}