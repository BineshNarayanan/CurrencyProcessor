package com.processor.message.store;

import java.util.HashMap;

public interface Store {
	/**
	 * Retrieves the message id.
	 * @return
	 */
	public long getMessageId();
	
	/**
	 * Initializes the store.
	 */
	public void initStore();
	
	
	/**
	 * Retrieves the message from id passed.
	 * @param id
	 * @return
	 */
	public Message getMessage(long id);
	
	/**
	 * Stores the message provided.
	 * @param message
	 * @return
	 */
	public boolean storeMessage(Message message);
	
	/**
	 * Returns an instance of blank message with id.
	 * @return
	 */
	public Message getBlankMessage();
	
	/**
	 * Deletes the message provided.
	 * @param message
	 * @return
	 */
	public boolean deleteMessage(Message message);
	
	/**
	 * Retrieves all messages from the store.
	 * @return
	 */
	public HashMap<Long, Message> getAllMessagesFromStore();
	
	/**
	 * Saves the processed currency from list in store.
	 * @param id
	 * @throws Exception
	 */
	public void saveToCurrencyFromStore(long id) throws Exception;
	
	/**
	 * Saves the processed Currency to list in store.
	 * @param id
	 * @throws Exception
	 */
	public void saveToCurrencyToStore(long id) throws Exception;
	
	/**
	 * Save the Currency originating countries in store.
	 * @param id
	 * @throws Exception
	 */
	public void saveToCurrencyOriginatingCountriesStore(long id) throws Exception;
}
