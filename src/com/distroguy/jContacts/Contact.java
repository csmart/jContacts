package com.distroguy.jContacts;

import java.util.UUID;

public class Contact {
	private static final String CONTACT = "Contact";
	private static final String SALUTATION = "Salutation";
	private static final String FIRSTNAME = "Firstname";
	private static final String MIDDLENAME = "Middlename";
	private static final String LASTNAME = "Lastname";
	private static final String PHONE = "Phone";
	private static final String MOBILE = "Mobile";
	

	// possible attributes for a contact
	private String uuid, salutation, firstName, middleName, lastName;
	private Integer phoneNumber, mobileNumber;
	
	public Contact(){
		String uuid = UUID.randomUUID().toString();
		this.setUuid(uuid);
	}
	
	// Return a list of possible attributes
	public static String[] getAttributes(){
		String[] attributeNames = {SALUTATION, FIRSTNAME, MIDDLENAME, LASTNAME, PHONE, MOBILE};
		return attributeNames;
	}

	public String[] getStrings(){
		String[] contactString = new String[getAttributes().length];
		contactString[0] = this.getSaltutation();
		contactString[1] = this.getFirstname();
		contactString[2] = this.getMiddlename();
		contactString[3] = this.getLastname();
		contactString[4] = this.getPhone().toString();
		contactString[5] = this.getMobile().toString();
		return contactString;
	}

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
	public String getSaltutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public Integer getPhone() {
		return phoneNumber;
	}

	public void setPhone(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getMobile() {
		return mobileNumber;
	}

	public void setMobile(Integer mobileNumber) {
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
		
}