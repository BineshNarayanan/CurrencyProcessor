package com.processor.exceptions;

/**
 * Custom Exception to throw Message Processing Exception.
 * @author BNaraya
 *
 */
public class MessageProcessException extends Exception {
	
	private static final long serialVersionUID = -8702375588438647717L;

	public MessageProcessException(String message){
		super(message);
	}
}
