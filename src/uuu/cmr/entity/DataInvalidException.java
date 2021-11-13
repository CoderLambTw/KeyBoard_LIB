package uuu.cmr.entity;

public class DataInvalidException extends RuntimeException{

	public DataInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataInvalidException(String message) {
		super(message);
	}

	public DataInvalidException() {
		super();
	}
	
}
