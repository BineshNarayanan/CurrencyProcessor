package com.processor.persistence.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import com.processor.exceptions.MessageStoreException;
import com.processor.message.store.Message;
import com.processor.persistence.JSONMessagePersistenceImpl;
import com.processor.persistence.MessagePersistence;

public class TestJSONMessagePersistence {

	MessagePersistence persistence = new JSONMessagePersistenceImpl();
	
	@Test
	public void testGetBlankMessage() {
		Message message = persistence.getBlankMessage();
		assertNotNull("The object message cannot be null", message);
	}
	
	@Test
	public void testStoreMessage() {
		Message message = persistence.getBlankMessage();
		try {
			boolean isStored = persistence.storeMessage(message);
			assertTrue("The Message has not been stored",isStored);
		} catch (MessageStoreException e) {
			// TODO Auto-generated catch block
			
		}
	}
	
	@Test
	public void testGetMessages() {
		try {
			Message message = persistence.getBlankMessage();
			persistence.storeMessage(message);
			message = persistence.getBlankMessage();
			persistence.storeMessage(message);
			HashMap<Long,Message> messages = persistence.getMessages();
			assertFalse("The All Messages list cannot be null",messages.isEmpty());
		} catch (MessageStoreException e) {
			// TODO Auto-generated catch block
		}
	}
	
	
	@Test
	public void testGetMessage() {
		try {
			Message messageStored = persistence.getBlankMessage();
			persistence.storeMessage(messageStored);
			Message messageRetrieved = persistence.getMessage(messageStored.getId());
			assertTrue("The id of stored and retrieved message must be same",messageStored.getId() == messageRetrieved.getId());
		} catch (MessageStoreException e) {
			// TODO Auto-generated catch block
		}
	}
	
	@Test
	public void testDeleteMessage() {
		try {
			Message messageStored = persistence.getBlankMessage();
			persistence.storeMessage(messageStored);
			boolean isDeleted = persistence.deleteMessage(messageStored);
			assertTrue("The delete message method not working correctly",isDeleted);
		} catch (Exception e) {
			fail("There is some exception in testDeleteMessage method");
		}
	}
	
	@Test
	public void testSaveGroupCurrencyFrom(){
		try {
			Message message = persistence.getBlankMessage();
			persistence.storeMessage(message);
			persistence.saveGroupCurrencyFrom(message.getId());
			HashMap<String, ArrayList<Long>> groupCurrencyFrom = persistence.getGroupCurrencyFrom();
			assertNotNull("The object cannot be null", groupCurrencyFrom);
		} catch (Exception e) {
			fail("There is some exception in testSaveGroupCurrencyFrom method");
		}
	}
	
	@Test
	public void testSaveGroupCurrencyTo(){
		try {
			Message message = persistence.getBlankMessage();
			persistence.storeMessage(message);
			persistence.saveGroupCurrencyTo(message.getId());
			HashMap<String, ArrayList<Long>> groupCurrencyTo = persistence.getGroupCurrencyTo();
			assertNotNull("The object cannot be null", groupCurrencyTo);
		} catch (Exception e) {
			fail("There is some exception in testSaveGroupCurrencyTo method");
		}
	}
	
	@Test
	public void testSaveGroupOriginatingCountry(){
		try {
			Message message = persistence.getBlankMessage();
			persistence.storeMessage(message);
			persistence.saveGroupOriginatingCountry(message.getId());
			HashMap<String, ArrayList<Long>> groupOrigCountry = persistence.getGroupOriginatingCountry();
			assertNotNull("The object cannot be null", groupOrigCountry);
		} catch (Exception e) {
			fail("There is some exception in testSaveGroupOriginatingCountry method");
		}
	}
}
