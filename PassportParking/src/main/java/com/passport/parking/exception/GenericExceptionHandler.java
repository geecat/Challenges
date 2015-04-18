package com.passport.parking.exception;

/**
 * This class handles exception of all type.
 * @author Ravi
 *
 */
public class GenericExceptionHandler extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String statusCode;
	public GenericExceptionHandler(String message) {
		super(message);
	}
	public GenericExceptionHandler() {
		// TODO Auto-generated constructor stub
	}
	public GenericExceptionHandler(String message, String code){
		super(message);
		this.setStatusCode(code);
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}
	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	
	
}
