package com.amardeep.simplenote.exception;

public class NoteNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7017842696520702764L;
	private String message;
	public NoteNotFoundException(String message)
	{
		super(message);
		this.message=message;
	}
	public String toString()
	{
		return message;
	}

}
