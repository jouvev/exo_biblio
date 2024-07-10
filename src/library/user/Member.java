package library.user;

public class Member {
	private String firstname;
	private String name;
	private String mail;
	private String id;
	
	public Member(String id, String name, String firstname, String mail) {
		super();
		this.firstname = firstname;
		this.name = name;
		this.mail = mail;
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Membre [nom=" + name + ", prenom=" + firstname + "]";
	}

	public String getFirstname() {
		return firstname;
	}

	public String getName() {
		return name;
	}

	public String getMail() {
		return mail;
	}

	public String getId() {
		return id;
	}
	
	

}
