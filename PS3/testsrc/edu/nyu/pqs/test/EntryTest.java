package edu.nyu.pqs.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pqs171.Entry;

public class EntryTest {
  @Test
  public void testEquals() {
    Entry entry1 = new Entry.EntryBuilder("Alice", "12345").postalAddress("257 Flatbush St, NY").
        emailAddress("Alice@nyu.edu").note("Midterm next week!").build();
    Entry entry2 = new Entry.EntryBuilder("Alice", "12345").postalAddress("257 Flatbush St, NY").
        emailAddress("Alice@nyu.edu").note("Midterm next week!").build();
    assertEquals(entry1, entry2);
    assertEquals(entry1.hashCode(), entry2.hashCode());
  }
  
  @Test
  public void testSameObject() {
    Entry entry1 = new Entry.EntryBuilder("Alice", "12345").build();
    assertEquals(entry1, entry1);
  }
  
  @Test
  public void testNullObject() {
    Entry entry1 = new Entry.EntryBuilder("Alice", "12345").build();
    assertFalse(entry1.equals(null));
    Entry entry2 = new Entry.EntryBuilder(null, "12345").build();
    assertFalse( entry2.equals(null));    
  }
  
  @Test
  public void testDifferentClassObject() {
    Entry entry1 = new Entry.EntryBuilder( "Alice", "12345" ).build();
    assertFalse( entry1.equals( Integer.MAX_VALUE ) );
  }
  
  /**
   * For each variable, test whether it equals to null and test their hasCode().
   */
  @Test
  public void testEqualOnVariables() {
    //Testing emailAddress
    Entry testEmail1 = new Entry.EntryBuilder( "Alice", "12345" ).postalAddress("257 Flatbush St, NY").
        emailAddress("Alice@nyu.edu").note("Midterm next week!").build();
    Entry testEmail2 = new Entry.EntryBuilder( "Alice", "12345" ).postalAddress("257 Flatbush St, NY").
        emailAddress(null).note("Midterm next week!").build();
    Entry testEmail3 = new Entry.EntryBuilder( "Alice", "12345" ).postalAddress("257 Flatbush St, NY").
        emailAddress(null).note("Midterm next week!").build();
    assertFalse( testEmail1.equals(testEmail2) );
    assertFalse( testEmail2.equals(testEmail1) );
    assertTrue( testEmail2.equals(testEmail3) );
    assertNotEquals( testEmail2.hashCode(), testEmail1.hashCode() );
    
    //Testing name. However, it is not reasonable to allow name to be null String.
    Entry testName1 = new Entry.EntryBuilder( "Alice", "12345" ).postalAddress("257 Flatbush St, NY").
        emailAddress("Alice@nyu.edu").note("Midterm next week!").build();
    Entry testName2 = new Entry.EntryBuilder( null, "12345" ).postalAddress("257 Flatbush St, NY").
        emailAddress("Alice@nyu.edu").note("Midterm next week!").build();
    Entry testName3 = new Entry.EntryBuilder( null, "12345" ).postalAddress("257 Flatbush St, NY").
        emailAddress("Alice@nyu.edu").note("Midterm next week!").build();
    assertFalse( testName1.equals(testName2) );
    assertFalse( testName2.equals(testName1) );
    assertTrue( testName2.equals(testName3) );
    assertNotEquals( testName1.hashCode(), testName2.hashCode() );
    
    //Testing emailAddress
    Entry testNote1 = new Entry.EntryBuilder( "Alice", "12345" ).postalAddress("257 Flatbush St, NY").
        emailAddress("Alice@nyu.edu").note("Midterm next week!").build();
    Entry testNote2 = new Entry.EntryBuilder( "Alice", "12345" ).postalAddress("257 Flatbush St, NY").
        emailAddress("Alice@nyu.edu").note(null).build();
    Entry testNote3 = new Entry.EntryBuilder( "Alice", "12345" ).postalAddress("257 Flatbush St, NY").
        emailAddress("Alice@nyu.edu").note(null).build();
    assertFalse( testNote1.equals(testNote2) );
    assertFalse( testNote2.equals(testNote1) );
    assertTrue( testNote2.equals(testNote3) );
    assertNotEquals( testNote1.hashCode(), testNote2.hashCode() );
    
    //Testing phoneNumber
    Entry testPhone1 = new Entry.EntryBuilder( "Alice", "12345" ).postalAddress("257 Flatbush St, NY").
        emailAddress("Alice@nyu.edu").note("Midterm next week!").build();
    Entry testPhone2 = new Entry.EntryBuilder( "Alice", null ).postalAddress("257 Flatbush St, NY").
        emailAddress("Alice@nyu.edu").note("Midterm next week!").build();
    Entry testPhone3 = new Entry.EntryBuilder( "Alice", null ).postalAddress("257 Flatbush St, NY").
        emailAddress("Alice@nyu.edu").note("Midterm next week!").build();
    assertFalse(testPhone1.equals(testPhone2));
    assertFalse(testPhone2.equals(testPhone1));
    assertTrue(testPhone2.equals(testPhone3));
    assertNotEquals(testPhone1.hashCode(), testPhone2.hashCode());
    assertEquals(testPhone2.hashCode(), testPhone3.hashCode());
    
    Entry testPostal1 = new Entry.EntryBuilder( "Alice", "12345" ).postalAddress("257 Flatbush St, NY").
        emailAddress("Alice@nyu.edu").note("Midterm next week!").build();
    Entry testPostal2 = new Entry.EntryBuilder( "Alice", "12345" ).postalAddress(null).
        emailAddress("Alice@nyu.edu").note("Midterm next week!").build();
    Entry testPostal3 = new Entry.EntryBuilder( "Alice", "12345" ).postalAddress(null).
        emailAddress("Alice@nyu.edu").note("Midterm next week!").build();
    assertFalse(testPostal1.equals(testPostal2));
    assertFalse(testPostal2.equals(testPostal1));
    assertTrue(testPostal2.equals(testPostal3));
    assertNotEquals(testPostal1.hashCode(), testPostal2.hashCode());
  }
  
  @Test
  public void testGetter() {
    Entry entry = new Entry.EntryBuilder( "Alice", "12345" ).postalAddress("257 Flatbush St, NY").
        emailAddress("Alice@nyu.edu").note("Midterm next week!").build();
    assertEquals(entry.getName(), "Alice");
    assertEquals(entry.getPhoneNumber(), "12345");
    assertEquals(entry.getEmailAddress(), "Alice@nyu.edu");
    assertEquals(entry.getNote(), "Midterm next week!");
    assertEquals(entry.getPostalAddress(), "257 Flatbush St, NY");
  }
  
  @Test
  public void testPrintOut() {
    Entry entry = new Entry.EntryBuilder("Alice", "12345").postalAddress("257 Flatbush St, NY").
        emailAddress("Alice@nyu.edu").note("Midterm next week!").build();
    assertEquals(entry.toString(), "Alice;12345;257 Flatbush St, NY;Alice@nyu.edu;Midterm next week!");
  }
  
}
