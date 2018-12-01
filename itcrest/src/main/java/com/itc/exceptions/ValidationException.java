package com.itc.exceptions;

/**
 * The Class ValidationException.
 * @author Debadatta Mishra
 */
public class ValidationException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7864288888316538966L;
	
	/** The message. */
	private String message;
	
	/**
	 * Instantiates a new validation exception.
	 *
	 * @param message the message
	 */
	public ValidationException(String message) {
		super(message);
		this.message = message;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return message;
	}

}
