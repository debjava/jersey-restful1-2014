package com.itc.handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.itc.exceptions.ValidationException;
import com.itc.models.Registration;

/**
 * The Class ValidatorImpl.
 * @author Debadatta Mishra
 */
public class ValidatorImpl implements Validator {

	/** The Constant EMAIL_PATTERN. */
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	/** The pattern. */
	private Pattern pattern;
	
	/** The matcher. */
	private Matcher matcher;
	
	/* (non-Javadoc)
	 * @see com.itc.handlers.Validator#validate(com.itc.models.Registration)
	 */
	public boolean validate(Registration registration) throws ValidationException {
		String firstName = registration.getFirstName();
		if( firstName == null || firstName.length() == 0 ) throw new ValidationException("First name can not be blank");
		String password = registration.getPassword();
		if( password == null || password.length() == 0 ) throw new ValidationException("Password name can not be blank");
		String email = registration.getEmail();
		if( !isEmailValid(email)) throw new ValidationException("Invalid email id");
		return true;
	}
	
	/**
	 * Checks if is email valid.
	 *
	 * @param email the email
	 * @return true, if is email valid
	 */
	private boolean isEmailValid(String email) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

}
