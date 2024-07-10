package library.entity;

public class EntityFactory {
	public IBasicEntity createEntity(String id, String title, String autor, String entityType) {
		if(entityType.equalsIgnoreCase("BOOK")) {
			return new Book(title,autor,id);
		}
		return null;
		
	}
}
