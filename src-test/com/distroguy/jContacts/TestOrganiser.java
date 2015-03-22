/**
 * 
 * TestOrganiser
 * 
 * This JUnit class tests the Organiser class.
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * TestOrganiser
 * 
 * The TestOrganiser class which will hold and perform our unit tests for the
 * Organiser class
 * 
 */
public class TestOrganiser {

	Organiser testOrganiser;

	@Before
	public void setup() {
		testOrganiser = new Organiser();
	}

	/**
	 * testInitialisation
	 * 
	 * Test that initial creation of an Organiser object has a blank contact
	 * ArrayList
	 */
	@Test
	public void testInitialisation() {
		ArrayList<Contact> blankContactList = new ArrayList<Contact>();
		assertEquals(testOrganiser.getContacts().toString(),
				blankContactList.toString());
	}

	/**
	 * testCreateContactList
	 * 
	 * Start with a blank Organiser, add a contact and test that the object is
	 * added and that it's a Contact object
	 */
	@Test
	public void testCreateContactList() {
		testOrganiser.addContact();
		Contact newContact = new Contact();
		assertEquals(testOrganiser.getContact(0).getClass(),
				newContact.getClass());
	}

	/**
	 * testDelContact
	 * 
	 * Test we can (add and) delete contacts
	 */
	@Test
	public void testDelContact() {
		testOrganiser.addContact();
		assertTrue(!testOrganiser.getContacts().isEmpty());
		testOrganiser.delContact(testOrganiser.getContact(0));
		assertTrue(testOrganiser.getContacts().isEmpty());
	}

	/**
	 * testGetStrings
	 * 
	 * Test we can get back Strings from an Object
	 */
	@Test
	public void testGetStrings() {
		String[] testValues = { "0123", "Mr", "Huey",
				"~`!@#$%^&*()_+-=[]\\{}|;':\",./<>?1234567890ABCabc", "Duck",
				"+61234567890", "(02) 34567890" };
		testOrganiser.addContact();
		testOrganiser.getContact(0).setStrings(testValues);

		String[] testReturnedValues = testOrganiser.getContact(0).getStrings();
		for (int i = 0; i < testValues.length; i++) {
			assertEquals(testReturnedValues[i], testValues[i]);
		}
	}

	/**
	 * testGetFilteredStrings
	 * 
	 * Test we can get back a filtered list of Strings from an Object
	 */
	@Test
	public void testGetFilteredStrings() {
		String firstName = "Huey";
		testOrganiser.addContact();
		testOrganiser.getContact(0).setFirstname(firstName);

		assertEquals(testOrganiser.getStrings(firstName).length, 1);
		assertEquals(testOrganiser.getStrings("no match").length, 0);
	}
}
