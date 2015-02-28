package com.processor.messageprocessor.test;

import static org.junit.Assert.*;
import org.junit.Test;

import com.processor.exceptions.MessageProcessException;
import com.processor.exceptions.MessageStoreException;
import com.processor.message.store.Message;
import com.processor.messageprocessor.GroupCurrencyFromProcessor;
import com.processor.messageprocessor.GroupCurrencyToProcessor;
import com.processor.messageprocessor.GroupOriginatingCountriesProcessor;
import com.processor.messageprocessor.Processor;
import com.processor.persistence.JSONMessagePersistenceImpl;
import com.processor.persistence.MessagePersistence;

public class TestProcessors {
	
	MessagePersistence persistence = new JSONMessagePersistenceImpl();
	
	@Test
	public void testAllProcessor() {
		try {
			Message message = persistence.getBlankMessage();
			persistence.storeMessage(message);
			new GroupCurrencyFromProcessor().process(message);
			new GroupCurrencyToProcessor().process(message);
			new GroupOriginatingCountriesProcessor().process(message);
			assertNotNull("The object of currencyFrom is NULL", persistence.getGroupCurrencyFrom());
			assertNotNull("The object of currencyTo is NULL", persistence.getGroupCurrencyTo());
			assertNotNull("The object of originatingCountry is NULL", persistence.getGroupOriginatingCountry());
		} catch (MessageStoreException | MessageProcessException e) {
			if(e instanceof MessageStoreException){
				fail("MessageStoreException in testAllProcessor");
			} else if(e instanceof MessageProcessException){
				fail("MessageProcessException in testAllProcessor");
			}
		}
		
	}
	
	

}
