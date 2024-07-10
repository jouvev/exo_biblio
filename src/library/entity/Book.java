package library.entity;

public class Book implements IBorrowableEntity{
	private String title;
	private String autor;
	private String id;
	private boolean available;
	
	public Book(String id, String title, String autor) {
		super();
		this.title = title;
		this.autor = autor;
		this.id = id;
		available = true;
	}

	@Override
	public String toString() {
		return "Livre [title=" + title + ", autor=" + autor + ", id=" + id + "]";
	}

	public String getTitle() {
		return title;
	}

	public String getAutor() {
		return autor;
	}

	public String getId() {
		return id;
	}

	public boolean isAvailable() {
		return available;
	}
	
	public boolean borrow() {
		if(this.isAvailable()) {
			available = false;
			return true;
		}
		return false;
	}

	public boolean restore() {
		if(!this.isAvailable()) {
			available = true;
			return true;
		}
		return false;
	}
	
}
