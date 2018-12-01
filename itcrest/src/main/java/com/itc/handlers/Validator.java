package com.itc.handlers;

import com.itc.exceptions.ValidationException;
import com.itc.models.Registration;

/**
 * The Interface Validator.
 * @author Debadatta Mishra
 */
public interface Validator {
	
	/**
	 * Validate.
	 *
	 * @param registration the registration
	 * @return true, if successful
	 * @throws ValidationException the validation exception
	 */
	public boolean validate(Registration registration) throws ValidationException ;

}
