package library.entity;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BookTest {
	private Book availableBook;
	private Book unavailableBook;

	@Before
	public void setUp() throws Exception {
		availableBook = new Book("1","available book","anonymous");
		unavailableBook = new Book("2","unavailable book","anonymous");
		unavailableBook.borrow();
	}
	
	@Test
	public void testInitialSetup() {
		assertTrue(availableBook.isAvailable());
		assertFalse(unavailableBook.isAvailable());
	}

	@Test
	public void testBorrowForAvailableBook() {
		assertTrue(availableBook.borrow());
		assertFalse(availableBook.isAvailable());
	}
	
	@Test
	public void testBorrowForUnavailableBook() {
		assertFalse(unavailableBook.borrow());
		assertFalse(unavailableBook.isAvailable());
	}
	
	@Test
	public void testRestoreForAvailableBook() {
		assertFalse(availableBook.restore());
		assertTrue(availableBook.isAvailable());
	}
	
	@Test
	public void testRestoreForUnavailableBook() {
		assertTrue(unavailableBook.restore());
		assertTrue(unavailableBook.isAvailable());
	}
	

}
