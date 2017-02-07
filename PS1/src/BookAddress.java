import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

/*****************************************************************************
 * entry class                                                               *
 * each entry contains five properties: name, address, phone, email and note *
 ****************************************************************************/
class Entry {
	private String name;
	private String address;
	private String phone;
	private String email;
	private String note;
	public Entry( String name, String address, String phone, String email, String note ) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.note = note;
	}
	public Entry() {
		this.name = null;
		this.address = null;
		this.phone = null;
		this.email = null;
		this.note = null;
	}
	public String getName() {
		return this.name;
	}
	public String getAddress() {
		return this.address;
	}
	public String getPhone() {
		return this.phone;
	}
	public String getEmail() {
		return this.email;
	}
	public String getNote() {
		return this.note;
	}
	public void setName( String name ) {
		this.name = name;
	}
	public void setAddress( String address ) {
		this.address = address;
	}
	public void setPhone( String phone ) {
		this.phone = phone;
	}
	public void setEmail( String email ) {
		this.email = email;
	}
	public void setNote( String note ) {
		this.note = note;
	}
}

public class BookAddress {
	
	private Vector<Entry> book = new Vector <Entry> ();;
	
	/*************************************
	 * constructor creates an empty book *
	 ************************************/
	public BookAddress() {
		Vector<Entry> book = new Vector <Entry> ();
	}
	
	public int getLength() {
		return this.book.size();
	}
	
	/*********************************************
	 * add new entry to the book address library *
	 ********************************************/
	public void add( String name, String address, String phone, String email, String note ) {
		Entry newEntry = new Entry(name,address,phone,email,note);
		book.add(newEntry);
	}
	
	/****************************************************
	 * remove function removes the entry at target rank *
	 ***************************************************/
	public void remove( int rank ) {
		//Guarantees the target rank is valid
		if( rank < getLength() ) {
			book.remove(rank);
		}
	}
	
	/*********************************************************
	 * remove function removes the entry by contact property *
	 * remove all entries with contact property              *
	 ********************************************************/
	public void remove( String info ) {
		for( int i = 0;  i < getLength(); ++i ) {
			if( book.get(i).getName() == info ) {
				book.remove(i);
			}
			else if ( book.get(i).getAddress() == info ) {
				book.remove(i);
			}
			else if ( book.get(i).getPhone() == info ) {
				book.remove(i);
			}
			else if ( book.get(i).getEmail() == info ) {
				book.remove(i);
			}
			else if ( book.get(i).getNote() == info ) {
				book.remove(i);
			}
		}
	}
	
	/****************************************
	 * search the entry by contact property *
	 * return the first entry found         *
	 ***************************************/
	public int search( String info ) {
		for( int i = 0;  i < getLength(); ++i ) {
			if( book.get(i).getName() == info ) {
				return i;
			}
			else if ( book.get(i).getAddress() == info ) {
				return i;
			}
			else if ( book.get(i).getPhone() == info ) {
				return i;
			}
			else if ( book.get(i).getEmail() == info ) {
				return i;
			}
			else if ( book.get(i).getNote() == info ) {
				return i;
			}
		}
		//If there is no entry containing such property, return -1;
		return -1;
	}
	
	/***************************
	 * return the target entry *
	 **************************/
	public Entry getEntry( int rank ) {
		//Guarantees the target rank is valid
		if( rank < book.size() ) {
			return book.get(rank);
		}
		return null;
	}
	
	/************************
	 * write book to a file *
	 ***********************/
	public void writeToFile() {
		try{
			PrintWriter writer = new PrintWriter( "BookAddressLibrary.txt", "UTF-8" );
			for( int i = 0; i < getLength(); ++i ) {
				writer.println( book.get(i).getName() + " " + book.get(i).getAddress() + " " + book.get(i).getPhone() + " " + 
						book.get(i).getEmail() + " " + book.get(i).getNote() );
			}
			writer.close();
		}
		catch( IOException e ) {
			e.printStackTrace();
		}
	}
	
	/*************************
	 * Read book from a file *
	 ************************/
	public void readFile( String file ) {
		Scanner sc = null;
		try {
			sc = new Scanner( new File(file) );
		}
		catch( FileNotFoundException e ) {
			e.printStackTrace();
		}
		while( sc.hasNextLine() ) {
			Scanner scTemp = new Scanner (sc.nextLine());
			String name = null;
			String address = null;
			String phone = null;
			String email = null;
			String note = null;
			if( scTemp.hasNext() ) {
				name = scTemp.next();
			}
			if( scTemp.hasNext() ) {
				address = scTemp.next();
			}
			if( scTemp.hasNext() ) {
				phone = scTemp.next();
			}
			if( scTemp.hasNext() ) {
				email = scTemp.next();
			}
			if( scTemp.hasNext() ) {
				note = scTemp.next();
			}
			add( name, address, phone, email, note );
			scTemp.close();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
}