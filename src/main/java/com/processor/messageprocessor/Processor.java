package com.processor.messageprocessor;

import com.processor.exceptions.MessageProcessException;
import com.processor.message.store.Message;

public interface Processor {
	
	/**
	 * Process the message to analyse the tendency. E.g volume of similar currency From, currency to, etc.
	 * @param message
	 * @throws MessageProcessException
	 */
	public void process(Message message) throws MessageProcessException;
}
