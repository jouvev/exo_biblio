package library.entity;

public interface IBorrowableEntity extends IBasicEntity {
	public boolean borrow();
	public boolean restore();
	public boolean isAvailable();
}
