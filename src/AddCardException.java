public class AddCardException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5724927377367937675L;
	public AddCardException(){
		super();
	}
	public AddCardException(String message){
		super(message);
	}
	public AddCardException(String message, Throwable cause){
		super(message,cause);
	}
	public AddCardException(Throwable cause){
		super(cause);
	}
}