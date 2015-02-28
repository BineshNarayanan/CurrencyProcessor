package com.processor.message.store;

import java.util.HashMap;

public interface Store {
	public long getMessageId();
	
	public void initStore();
	
	public Message getMessage(long id);
	
	public boolean storeMessage(Message message);
	
	public Message getBlankMessage();
	
	public boolean deleteMessage(Message message);
	
	public HashMap<Long, Message> getAllMessagesFromStore();
	
	public void saveToCurrencyFromStore(long id) throws Exception;
	
	public void saveToCurrencyToStore(long id) throws Exception;
	
	public void saveToCurrencyOriginatingCountriesStore(long id) throws Exception;
}
