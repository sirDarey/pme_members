package com.pme.utils;

public class Validate {

	public final static String minLength = "MINIMUM Length is ";
	public final static String validEmail = "Enter a valid Email";
	public final static String validAlphabets = "Only ALPHABETS allowed";
	public final static String validNotBlank = "Field Cannot Be BLANK";
	public final static String validPhoneNo = "Enter a valid Phone No";
	public final static String validPassword = 
			"Password must contain lowercase [a-z], uppercase [A-Z], digits [0-9] and at least one special character such as (\"!@#&())";
	
	public final static String EMAIL_REGEX = 
			"^[a-zA-Z0-9_!#$%&'*+/=?``{|}~^.-]+@[a-zA-Z0-9.-]+$";
	
	public final static String PASSWORD_REGEX = 
			"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
}
