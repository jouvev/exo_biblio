package library;

import library.exception.UnavailableEntityException;

public class LibraryMain {

	public static void main(String[] args) {
		MyLibrary lib = new MyLibrary();
		
		try {
			lib.registerEntity("b1", "book test", "anonymous", "book");
			lib.registerMember("1", "1", "vincent", "vincent@vincent.fr");
			lib.registerMember("2", "2", "vincent", "vincent@vincent.fr");
			lib.borrowEntity("b1", "1");
			System.out.println(lib.borrowed("b1", "1") == true);
			
			try {
				lib.borrowEntity("b1", "2");
			}
			catch(UnavailableEntityException e) {
				System.out.println("impossible d'emprunter b1");
			}
			
			lib.restoreEntity("b1", "1");
			System.out.println(lib.borrowed("b1", "1") == false);
			
			lib.borrowEntity("b1", "2");
			System.out.println(lib.borrowed("b1", "2") == true);
			System.out.println(lib.isAvailable("b1") == false);
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
