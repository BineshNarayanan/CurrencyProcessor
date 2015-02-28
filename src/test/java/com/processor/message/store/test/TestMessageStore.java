package com.processor.message.store.test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import com.processor.message.store.Message;
import com.processor.message.store.MessageStore;


public class TestMessageStore {

	MessageStore messageStore = MessageStore.getInstance();
	
	@Test
	public void testInitStore() {
		assertTrue("MessageStore is not getting instantiated",null != messageStore);
	}
	
	@Test
	public void testGetMessageId(){
		long messageId = messageStore.getMessageId();
		assertTrue("MessageId cannot be zero",messageId > 0);
	}
	
	@Test
	public void testGetInstance(){
		MessageStore messageStore1 = MessageStore.getInstance();
		assertTrue("There cannot be two instances of MessageStore",messageStore == messageStore1);
	}
	
	@Test
	public void testStoringNULLMessage(){
		Message message = null;
		boolean isStored = messageStore.storeMessage(message);
		assertFalse("The NULL message should not be stored",isStored);
	}
	
	@Test
	public void testStoreMessage(){
		Message message = messageStore.getBlankMessage();
		boolean isStored = messageStore.storeMessage(message);
		assertTrue("The message has not been stored",isStored);
	}
	
	
	@Test
	public void testGetMessage(){
		Message messageStored = messageStore.getBlankMessage();
		messageStore.storeMessage(messageStored);
		Message messageRetrieved = messageStore.getMessage(messageStored.getId());
		assertTrue("Get Message from Id is not working",messageStored.getId() == messageRetrieved.getId());
	}
	
	@Test
	public void testGetAllMessagesFromStore(){
		Message message1 = messageStore.getBlankMessage();
		messageStore.storeMessage(message1);
		Message message2 = messageStore.getBlankMessage();
		messageStore.storeMessage(message2);
		HashMap<Long, Message> store = messageStore.getAllMessagesFromStore();
		assertFalse("The message store should not be empty",store.isEmpty());
	}
	
	@Test
	public void testDeleteMessage(){
		Message message = messageStore.getBlankMessage();
		messageStore.storeMessage(message);
		boolean isDeleted = messageStore.deleteMessage(message);
		assertTrue("The delete method is not working correctly", isDeleted);
	}
	
}
