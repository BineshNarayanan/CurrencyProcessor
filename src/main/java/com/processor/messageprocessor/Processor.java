package com.processor.messageprocessor;

import com.processor.exceptions.MessageProcessException;
import com.processor.message.store.Message;

public interface Processor {
	public void process(Message message) throws MessageProcessException;
}
