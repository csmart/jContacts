package com.distroguy.jContacts;

import java.util.UUID;

public class Contact {
	private static final String CONTACT = "Contact";
	private static final String UNIQUEID = "UUID";
	private static final String SALUTATION = "Salutation";
	private static final String FIRSTNAME = "Firstname";
	private static final String MIDDLENAME = "Middlename";
	private static final String LASTNAME = "Lastname";
	private static final String PHONE = "Phone";
	private static final String MOBILE = "Mobile";

	// Possible attributes for a contact
	private String uuid, salutation, firstName, middleName, lastName, phoneNumber, mobileNumber;
	//	private Integer phoneNumber, mobileNumber;
	//	private Address address;

	// Constructor, set defaults
	public Contact(){
		this.setUuid();
		this.setSalutation("");
		this.setFirstname("");
		this.setMiddlename("");
		this.setLastname("");
		this.setPhone("");
		this.setMobile("");
	}

	// Copy one object to another
	
	// Return a list of possible attributes
	public static String[] getAttributes(){
		String[] attributeNames = {UNIQUEID, SALUTATION, FIRSTNAME, MIDDLENAME, LASTNAME, PHONE, MOBILE};
		return attributeNames;
	}

	// Return details of a contact in a list of strings 
	public String[] getStrings(){
		String[] contactString = new String[getAttributes().length];
		contactString[0] = this.getUuid();
		contactString[1] = this.getSalutation();
		contactString[2] = this.getFirstname();
		contactString[3] = this.getMiddlename();
		contactString[4] = this.getLastname();
		contactString[5] = this.getPhone().toString();
		contactString[6] = this.getMobile().toString();
		return contactString;
	}
	
	// Set details of a contact from list of strings
	public void setStrings(String[] strings){
		this.setUuid(strings[0]);
		this.setSalutation(strings[1]);
		this.setFirstname(strings[2]);
		this.setMiddlename(strings[3]);
		this.setLastname(strings[4]);
		this.setPhone(strings[5]);
		this.setMobile(strings[6]);
	}

	// Set single attribute value, given attribute name 
	public void setString(String attribute, String data){
		switch (attribute) {
		case UNIQUEID: setUuid();
		break;
		case SALUTATION: setSalutation(data);
		break;
		case FIRSTNAME: setFirstname(data);
		break;
		case MIDDLENAME: setMiddlename(data);
		break;
		case LASTNAME: setLastname(data);
		break;
		case PHONE: setPhone(data);
		break;
		case MOBILE: setMobile(data);
		break;
		}
	}

	// Getters and setters
	public String getMiddlename() {
		return middleName;
	}

	public void setMiddlename(String middleName) {
		this.middleName = middleName;
	}

	public String getLastname() {
		return lastName;
	}

	public void setLastname(String lastName) {
		this.lastName = lastName;
	}
	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getPhone() {
		return phoneNumber;
	}

	public void setPhone(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMobile() {
		return mobileNumber;
	}

	public void setMobile(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getFirstname() {
		return firstName;
	}

	public void setFirstname(String firstName) {
		this.firstName = firstName;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setUuid() {
		String uuid = UUID.randomUUID().toString();
		this.uuid = uuid;
	}

}
