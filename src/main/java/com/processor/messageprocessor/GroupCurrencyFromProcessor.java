package com.processor.messageprocessor;

import org.apache.log4j.Logger;

import com.processor.exceptions.MessageProcessException;
import com.processor.exceptions.MessageStoreException;
import com.processor.message.store.Message;
import com.processor.persistence.JSONMessagePersistenceImpl;

public class GroupCurrencyFromProcessor implements Processor {

	private static Logger logger = Logger.getLogger(GroupCurrencyFromProcessor.class);
	
	JSONMessagePersistenceImpl persistenceObj = new JSONMessagePersistenceImpl();
	
	@Override
	public void process(Message message) throws MessageProcessException {
		try {
			if(null != message){
				persistenceObj.saveGroupCurrencyFrom(message.getId());	
			}
		} catch (MessageStoreException e) {
			logger.error("MessageStoreException in process of GroupCurrencyFromProcessor", e);
			throw new MessageProcessException(e.getMessage());
		}
	}

}
