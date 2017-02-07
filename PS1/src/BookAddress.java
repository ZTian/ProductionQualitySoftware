/**
 * This is an address book library.
 * <p>
 * The address book allows creating a book, adding an entry to the book each time, removing entries with target 
 * properties, searching the entry with target information, writing the book to a file and reading from a file.
 * 
 * @author Tian Zhao
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

/**
 * Each Entry contains five properties: name, address, phone, email and note.
 */
class Entry {
	private String name;
	private String address;
	private String phone;
	private String email;
	private String note;

	/**
	 * Constructor with parameters.
	 * 
	 * @param name the contact name
	 * @param address the contact postal address
	 * @param phone the contact phone number
	 * @param email the contact email address
	 * @param note the note to the contact
	 */
	public Entry(String name, String address, String phone, String email, String note) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.note = note;
	}

	/**
	 * Constructor with no parameters. All properties are set to null by default.
	 */
	public Entry() {
		this.name = null;
		this.address = null;
		this.phone = null;
		this.email = null;
		this.note = null;
	}

	/**
	 * Return the name of the entry.
	 * 
	 * @return the contact name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Return the postal address of the entry.
	 * 
	 * @return the contact postal address
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * Return the phone number of the entry.
	 * 
	 * @return the contact phone number
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * Return the email of the entry.
	 * 
	 * @return the contact email address
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Return the note of the entry.
	 * 
	 * @return the note to the contact
	 */
	public String getNote() {
		return this.note;
	}
	
	/**
	 * Set the name of the entry.
	 * 
	 * @param name the contact name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Set the address of the entry.
	 * 
	 * @param address the contact postal address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Set the phone of the entry.
	 * 
	 * @param phone the contact phone number
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Set the email of the entry.
	 * 
	 * @param email the contact email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Set the note of the entry.
	 * 
	 * @param note the note to the contact
	 */
	public void setNote(String note) {
		this.note = note;
	}
}

public class BookAddress {

	private Vector<Entry> book = new Vector<Entry>();;

	/**
	 * Constructor creates an empty book. No parameter passed in. 
	 */
	public BookAddress() {
		Vector<Entry> book = new Vector<Entry>();
	}

	/**
	 * Return the size of the address book.
	 * 
	 * @return the size of the address book
	 */
	public int getLength() {
		return this.book.size();
	}

	/**
	 * @param name the contact name
	 * @param address the contact postal address
	 * @param phone the contact phone number
	 * @param email the contact email address
	 * @param note the note to the contact
	 */
	public void add(String name, String address, String phone, String email, String note) {
		Entry newEntry = new Entry(name, address, phone, email, note);
		book.add(newEntry);
	}

	/**
	 * Remove function removes the entry at target rank.
	 * 
	 * @param rank the position of the target entry
	 */
	public void remove(int rank) {
		// Guarantees the target rank is valid
		if (rank < getLength()) {
			book.remove(rank);
		}
	}

	/**
	 * Remove function removes the entry by contact property. Remove all entries with contact property
	 * 
	 * @param info the target contact property
	 */
	public void remove(String info) {
		for (int i = 0; i < getLength(); ++i) {
			if (book.get(i).getName() == info) {
				book.remove(i);
			} else if (book.get(i).getAddress() == info) {
				book.remove(i);
			} else if (book.get(i).getPhone() == info) {
				book.remove(i);
			} else if (book.get(i).getEmail() == info) {
				book.remove(i);
			} else if (book.get(i).getNote() == info) {
				book.remove(i);
			}
		}
	}

	/**
	 * Search the entry by contact property. Return the first entry found.
	 * 
	 * @param info the target contact property
	 * @return 		 the position of the target contact
	 */
	public int search(String info) {
		for (int i = 0; i < getLength(); ++i) {
			if (book.get(i).getName() == info) {
				return i;
			} else if (book.get(i).getAddress() == info) {
				return i;
			} else if (book.get(i).getPhone() == info) {
				return i;
			} else if (book.get(i).getEmail() == info) {
				return i;
			} else if (book.get(i).getNote() == info) {
				return i;
			}
		}
		// If there is no entry containing such property, return -1;
		return -1;
	}

	/**
	 * Return the target entry.
	 * 
	 * @param rank the position of the target contact
	 * @return		 the target contact
	 **/
	public Entry getEntry(int rank) {
		// Guarantees the target rank is valid
		if (rank < book.size()) {
			return book.get(rank);
		}
		return null;
	}

	/**
	 * Write book to a file
	 */
	public void writeToFile() {
		try {
			PrintWriter writer = new PrintWriter("BookAddressLibrary.txt", "UTF-8");
			for (int i = 0; i < getLength(); ++i) {
				writer.println(book.get(i).getName() + " " + book.get(i).getAddress() + " " + book.get(i).getPhone()
						+ " " + book.get(i).getEmail() + " " + book.get(i).getNote());
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Read book from a file
	 * 
	 * @param file the name of the input file
	 */
	public void readFile(String file) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (sc.hasNextLine()) {
			Scanner scTemp = new Scanner(sc.nextLine());
			String name = null;
			String address = null;
			String phone = null;
			String email = null;
			String note = null;
			if (scTemp.hasNext()) {
				name = scTemp.next();
			}
			if (scTemp.hasNext()) {
				address = scTemp.next();
			}
			if (scTemp.hasNext()) {
				phone = scTemp.next();
			}
			if (scTemp.hasNext()) {
				email = scTemp.next();
			}
			if (scTemp.hasNext()) {
				note = scTemp.next();
			}
			add(name, address, phone, email, note);
			scTemp.close();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}