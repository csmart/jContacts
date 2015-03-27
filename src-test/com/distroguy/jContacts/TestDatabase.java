/**
 * 
 * TestDatabase
 * 
 * This JUnit class tests the Database class.
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

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * TestDatabase
 * 
 * The TestDatbase class which will hold and perform our unit tests for the
 * Database class
 * 
 */
public class TestDatabase {

	Database testDatabase;
	ArrayList<Contact> contactList;
	Organiser testOrganiser;

	private static final String TESTFILE = "testFile";

	@Before
	public void setup() {
		testDatabase = new Database(TESTFILE);
		testOrganiser = new Organiser();
	}

	/**
	 * testWriting
	 * 
	 * Test writing to a file
	 */
	@Test
	public void testWriting() {
		String[] testValues = { "0123", "Mr", "Huey",
				"~`!@#$%^&*()_+-=[]\\{}|;':\",./<>?1234567890ABCabc", "Duck",
				"+61234567890", "(02) 34567890" };
		testOrganiser.addContact();
		testOrganiser.getContact(0).setStrings(testValues);
		assertTrue((testDatabase.saveDataset(testOrganiser.getContacts())));
		ArrayList<Contact> contactListRead = new ArrayList<Contact>();

		// Read back out (verifies write worked)
		contactListRead = testDatabase.readDataset();

		String[] testReturnedValues = contactListRead.get(0).getStrings();
		for (int i = 0; i < testValues.length; i++) {
			assertTrue(testReturnedValues[i].equals(testValues[i]));
		}
	}
}
