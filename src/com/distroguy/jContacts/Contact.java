/**
 * 
 * Contact
 * 
 * This is the model component of the model-view-controller design pattern.
 * It holds the definition of the Contact object as well as methods for getting and setting data.
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

import java.io.Serializable;
import java.util.UUID;

/**
 * Contact
 * 
 * The class for a Contact object, which is serialiseable for persistent storage of data.
 * It provides methods for getting and setting data of a Contact object.
 * 
 * @param Takes nothing
 * @return Returns nothing
 *
 */
public class Contact implements Serializable {
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

	/**
	 * Contact
	 * 
	 * Default constructor for the Contact object
	 * 
	 * @param Takes nothing
	 * @return Returns nothing
	 *
	 */
	public Contact(){
		this.setUuid();
		this.setSalutation("");
		this.setFirstname("");
		this.setMiddlename("");
		this.setLastname("");
		this.setPhone("");
		this.setMobile("");
	}
	
	/**
	 * getAttributes
	 * 
	 * Gets a list of the attribute names a Contact object has
	 * 
	 * @param Takes nothing
	 * @return Returns a list with all attributes as defined above
	 *
	 */
	public static String[] getAttributes(){
		String[] attributeNames = {UNIQUEID, SALUTATION, FIRSTNAME, MIDDLENAME, LASTNAME, PHONE, MOBILE};
		return attributeNames;
	}

	/**
	 * getStrings
	 * 
	 * Gets a list of the attribute values a Contact object has
	 * 
	 * @param Takes nothing
	 * @return Returns details of a Contact in a list of strings
	 *
	 */
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

	/**
	 * setStrings
	 * 
	 * Sets the values of attributes for a Contact from a String list
	 * 
	 * @param Takes a list of attributes to set for a Contact
	 * @return Returns nothing
	 * 
	 */
	public void setStrings(String[] strings){
		this.setUuid(strings[0]);
		this.setSalutation(strings[1]);
		this.setFirstname(strings[2]);
		this.setMiddlename(strings[3]);
		this.setLastname(strings[4]);
		this.setPhone(strings[5]);
		this.setMobile(strings[6]);
	}

	/**
	 * setString
	 * 
	 * Sets the value of a single attribute, given the attribute name
	 * 
	 * @param Takes the name of an attribute
	 * @param Takes the value of an attribute
	 * @return Returns nothing
	 * 
	 */
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

	/**
	 * getMiddlename
	 * 
	 * Gets the value of a Contact's middlename
	 * 
	 * @param Takes nothing
	 * @return Returns nothing
	 * 
	 */
	public String getMiddlename() {
		return middleName;
	}

	/**
	 * setMiddlename
	 * 
	 * Sets the value of a Contact's middlename
	 * 
	 * @param Takes a String
	 * @return Returns nothing
	 * 
	 */
	public void setMiddlename(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * getLastname
	 * 
	 * Gets the value of a Contact's lastname
	 * 
	 * @param Takes nothing
	 * @return Returns nothing
	 * 
	 */
	public String getLastname() {
		return lastName;
	}

	/**
	 * setLastname
	 * 
	 * Sets the value of a Contact's lastname
	 * 
	 * @param Takes a String
	 * @return Returns nothing
	 * 
	 */
	public void setLastname(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * getTitle
	 * 
	 * Gets the value of a Contact's title
	 * 
	 * @param Takes nothing
	 * @return Returns nothing
	 * 
	 */
	public String getSalutation() {
		return salutation;
	}

	/**
	 * setTitle
	 * 
	 * Sets the value of a Contact's title
	 * 
	 * @param Takes a String
	 * @return Returns nothing
	 * 
	 */
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	/**
	 * getPhone
	 * 
	 * Gets the value of a Contact object's phone number
	 * 
	 * @param Takes nothing
	 * @return Returns nothing
	 * 
	 */
	public String getPhone() {
		return phoneNumber;
	}

	/**
	 * setPhone
	 * 
	 * Sets the value of a Contact object's phone number
	 * 
	 * @param Takes a String
	 * @return Returns nothing
	 * 
	 */
	public void setPhone(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * getMobile
	 * 
	 * Gets the value of a Contact object's mobile number
	 * 
	 * @param Takes nothing
	 * @return Returns nothing
	 * 
	 */
	public String getMobile() {
		return mobileNumber;
	}

	/**
	 * setMobile
	 * 
	 * Sets the value of a Contact object's mobile number
	 * 
	 * @param Takes nothing
	 * @return Returns nothing
	 * 
	 */
	public void setMobile(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * getFirstname
	 * 
	 * Gets the value of a Contact object's firstname
	 * 
	 * @param Takes nothing
	 * @return Returns nothing
	 * 
	 */
	public String getFirstname() {
		return firstName;
	}

	/**
	 * setFirstname
	 * 
	 * Sets the value of a Contact object's firstname
	 * 
	 * @param Takes a String
	 * @return Returns nothing
	 * 
	 */
	public void setFirstname(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * getUuid
	 * 
	 * Gets the value of a Contact object's unique ID
	 * 
	 * @param Takes nothing
	 * @return Returns nothing
	 * 
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * setUuid
	 * 
	 * Sets the value of a Contact object's unique ID
	 * 
	 * @param Takes String
	 * @return Returns nothing
	 * 
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * setUuid
	 * 
	 * Sets random value of a Contact object's unique ID
	 * 
	 * @param Takes nothing
	 * @return Returns nothing
	 * 
	 */
	public void setUuid() {
		String uuid = UUID.randomUUID().toString();
		this.uuid = uuid;
	}

}
