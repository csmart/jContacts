/**
 * 
 * Database
 * 
 * This is responsible for reading and writing out the program's dataset to disk
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
 *
 */

package com.distroguy.jContacts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Database
 * 
 * Reading and writing out the dataset.
 * 
 * @param Takes nothing
 * @return Returns nothing
 *
 */
public class Database {

	// Create a file object so we can test for it
	File organiserFile;
	String dataFormat;

	/**
	 * Database
	 * 
	 * Constructor for the Database object
	 * 
	 * @param Takes a String for filename
	 * @return Returns nothing
	 *
	 */
	Database(String filename) {
		organiserFile = new File(filename);
	}
	
	/**
	 * Database
	 * 
	 * Default constructor for the Database object
	 * 
	 * @param Takes nothing
	 * @return Returns nothing
	 *
	 */
	Database() {
	}
	
	/**
	 * checkExists
	 * 
	 * Checks to see if the persistent file that holds the dataset already
	 * exists
	 * 
	 * @param Takes nothing
	 * @return Returns Boolean
	 *
	 */
	public Boolean checkExists() {
		if (organiserFile.exists() && organiserFile.isFile()
				&& organiserFile.canWrite() && organiserFile.canWrite()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * readDataset
	 * 
	 * Reads in any data from disk and formats as an ArrayList for Organiser
	 * class
	 * 
	 * @param Takes nothing
	 * @return Returns ArrayList of the dataset
	 *
	 */
	public ArrayList<Contact> readDataset() {
		ArrayList<Contact> newContacts = new ArrayList<Contact>();
		try {
			ObjectInputStream inputStream = new ObjectInputStream(
					new FileInputStream(organiserFile.getAbsolutePath()));
			newContacts = (ArrayList<Contact>) inputStream.readObject();
			inputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newContacts;
	}

	/**
	 * saveDataset
	 * 
	 * Saves the program data to persistent storage
	 * 
	 * @param Takes String for the file name
	 * @param Takes ArrayList of Contact objects to write
	 * @return Returns nothing
	 *
	 */
	public Boolean saveDataset(ArrayList<Contact> contacts) {
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(
					new FileOutputStream(organiserFile.getAbsolutePath()));
			outputStream.writeObject(contacts);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * setFormat
	 * 
	 * Sets the format of the persistent data
	 * 
	 * @param Takes String for data format
	 * @return Returns nothing
	 *
	 */
	public void setFormat(String dataFormat) {
		// TO-DO
	}

	/**
	 * setFilename
	 * 
	 * Sets the name of the file to write data to
	 * 
	 * @param Takes String for file name
	 * @return Returns nothing
	 *
	 */
	public void setFilename(String filename) {
		organiserFile = new File(filename);
	}
}
