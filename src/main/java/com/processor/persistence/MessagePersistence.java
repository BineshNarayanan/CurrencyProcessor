package com.processor.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.processor.exceptions.MessageStoreException;
import com.processor.message.store.Message;

public interface MessagePersistence {
	/**
	 * Save Message
	 * @param message
	 */
	public boolean storeMessage(Message message) throws MessageStoreException;
	
	/**
	 * Get Message from Id.
	 * @param id
	 */
	public Message getMessage(long id) throws MessageStoreException;
	
	/**
	 * Get Messages from Store.
	 * @return
	 * @throws MessageStoreException
	 */
	public HashMap<Long, Message> getMessages() throws MessageStoreException;
	
	/**
	 * Returns a blank Message with Id.
	 * @return
	 */
	public Message getBlankMessage();
	
	/**
	 * Save Message
	 * @param message
	 */
	public boolean deleteMessage(Message message) throws MessageStoreException;
	
	public boolean saveGroupCurrencyFrom(long id) throws MessageStoreException;
	
	public boolean saveGroupCurrencyTo(long id) throws MessageStoreException;
	
	public boolean saveGroupOriginatingCountry(long id) throws MessageStoreException;
	
	public HashMap<String, ArrayList<Long>> getGroupCurrencyFrom() throws MessageStoreException;
	
	public HashMap<String, ArrayList<Long>> getGroupCurrencyTo() throws MessageStoreException;
	
	public HashMap<String, ArrayList<Long>> getGroupOriginatingCountry() throws MessageStoreException;
	
}
