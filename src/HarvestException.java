
public class HarvestException extends Exception {
	/**
	 * wtf
	 */
	private static final long serialVersionUID = 8255683991959888421L;
	
	
	public HarvestException(){
		super();
	}
	public HarvestException(String message){
		super(message);
	}
	public HarvestException(String message, Throwable cause){
		super(message,cause);
	}
	public HarvestException(Throwable cause){
		super(cause);
	}
}
