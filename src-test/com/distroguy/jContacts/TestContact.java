/**
 * 
 * TestContact
 * 
 * This JUnit class tests the Contact class.
 * 
 * @author Chris Smart <distroguy@gmail.com>
 * @since 2015-03-22
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

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * TestContact
 * 
 * The TestContact class which will hold and perform our unit tests for the
 * Contact class
 * 
 */
public class TestContact {

	// Expected constants
	private static final String UNIQUEID = "UUID";
	private static final String SALUTATION = "Salutation";
	private static final String FIRSTNAME = "Firstname";
	private static final String MIDDLENAME = "Middlename";
	private static final String LASTNAME = "Lastname";
	private static final String PHONE = "Phone";
	private static final String MOBILE = "Mobile";

	// Expected attribute names
	String[] attributeNames = { UNIQUEID, SALUTATION, FIRSTNAME, MIDDLENAME,
			LASTNAME, PHONE, MOBILE };

	Contact testContact;

	@Before
	public void setup() {
		testContact = new Contact();
	}

	/**
	 * testSettersGetters
	 * 
	 * Test the getters and setters all work
	 */
	@Test
	public void testSettersGetters() {
		String[] testValues = { "0123", "Mr", "Huey",
				"~`!@#$%^&*()_+-=[]\\{}|;':\",./<>?1234567890ABCabc", "Duck",
				"+61234567890", "(02) 34567890" };

		// Test the getter and setter methods by calling the method that takes
		// an array of strings
		testContact.setStrings(testValues);
		String[] testReturnedValues = testContact.getStrings();
		for (int i = 0; i < testValues.length; i++) {
			assertTrue(testReturnedValues[i].equals(testValues[i]));
		}

		// Test the method that takes an attribute with a value
		for (String attribute : attributeNames) {
			for (String value : testValues) {
				testContact.setString(value, attribute);
			}
		}
		testReturnedValues = testContact.getStrings();
		for (int i = 0; i < testValues.length; i++) {
			assertTrue(testReturnedValues[i].equals(testValues[i]));
		}
	}

	/**
	 * testUuidGeneration
	 * 
	 * Test that method for generating a UUID works
	 */
	@Test
	public void testUuidGeneration() {
		String Uuid = testContact.getUuid();
		testContact.setUuid();
		assertTrue(!testContact.getUuid().equals(Uuid));
	}

}
