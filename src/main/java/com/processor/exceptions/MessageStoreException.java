package com.processor.exceptions;

/**
 * Custome exception to throw Message Store Exception.
 * @author BNaraya
 *
 */
public class MessageStoreException extends Exception {
	
	private static final long serialVersionUID = -8702375588438647717L;

	public MessageStoreException(String message){
		super(message);
	}
}
