package library.exception;

public class UnborrowableEntityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8966891184827935068L;
	
	public UnborrowableEntityException(String msg) {
		super(msg);
	}

}
