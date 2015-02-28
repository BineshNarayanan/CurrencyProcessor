package com.processor.message.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.processor.consume.Consumer;



public class MessageStore implements Store {
	
	private static Logger logger = Logger.getLogger(MessageStore.class);
	
	private long messageId;
	private static MessageStore messageStore = new MessageStore();
	private Map<Long, Message> storage;
	private HashMap<String,ArrayList<Long>> groupCurrencyFrom;
	private HashMap<String,ArrayList<Long>> groupCurrencyTo;
	private HashMap<String,ArrayList<Long>> groupOriginatingCountries;

	private MessageStore(){
		initStore();
	}
	
	public synchronized long getMessageId() {
		return messageId = messageId + 1;
		
	}
	
	public void initStore() {
		if(null == storage) {
			storage = Collections.synchronizedMap(new HashMap<Long, Message>());	
		}
	}
	
	public static synchronized MessageStore getInstance(){
		if(null == messageStore){
			messageStore = new MessageStore();
		}
		return messageStore;
	}
	
	public Message getMessage(long id) {
		if (null != storage) {
			return storage.get(id);
		}
		return null;
	}
	
	public synchronized boolean storeMessage(Message message) {
		if (null != storage && null != message) {
			storage.put(message.getId(), message);
			return true;
		}
		return false;
	}
	
	public Message getBlankMessage() {
		synchronized (MessageStore.class) {
			Message message = new Message(getMessageId());
			return message;
		}
	}
	
	public HashMap<Long, Message> getAllMessagesFromStore() {
		return (HashMap<Long, Message>) new HashMap<Long, Message>(storage).clone();
	}

	@Override
	public boolean deleteMessage(Message message) {
		if(null != message && null != storage){
			storage.remove(message.getId());
			return true;
		}
		return false;
	}

	public HashMap<String, ArrayList<Long>> getGroupCurrencyFrom() {
		return groupCurrencyFrom;
	}
	
	@Override
	public void saveToCurrencyFromStore(long id) throws Exception {
		try {
			Message message = storage.get(id);
			ArrayList<Long> listMessageIds = null;
			try {
				if(groupCurrencyFrom.containsKey(message.getCurrencyFrom())){
					listMessageIds = groupCurrencyFrom.get(message.getCurrencyFrom());
					try {
						listMessageIds.add(id);
					} catch (Exception e) {
						listMessageIds = new ArrayList<Long>();
						listMessageIds.add(id);
					}
				} else {
					listMessageIds = new ArrayList<Long>();
					listMessageIds.add(message.getId());
				}
			} catch (NullPointerException e) {
				groupCurrencyFrom = new HashMap<String,ArrayList<Long>>();
				listMessageIds = new ArrayList<Long>();
				listMessageIds.add(message.getId());
			}
			groupCurrencyFrom.put(message.getCurrencyFrom(), listMessageIds);	
		} catch (Exception e) {
			logger.error("Exception in saveToCurrencyFromStore ::", e);
			throw e;
		}
	}
	
	@Override
	public void saveToCurrencyToStore(long id) throws Exception {
		try {
			Message message = storage.get(id);
			ArrayList<Long> listMessageIds = null;
			try {
				if(groupCurrencyTo.containsKey(message.getCurrencyTo())){
					listMessageIds = groupCurrencyTo.get(message.getCurrencyTo());
					try {
						listMessageIds.add(id);
					} catch (Exception e) {
						listMessageIds = new ArrayList<Long>();
						listMessageIds.add(id);
					}
				} else {
					listMessageIds = new ArrayList<Long>();
					listMessageIds.add(message.getId());
				}
			} catch (NullPointerException e) {
				groupCurrencyTo = new HashMap<String,ArrayList<Long>>();
				listMessageIds = new ArrayList<Long>();
				listMessageIds.add(message.getId());
			}
			groupCurrencyTo.put(message.getCurrencyTo(), listMessageIds);
		} catch (Exception e) {
			logger.error("Exception in saveToCurrencyToStore ::", e);
			throw e;
		}
	}

	@Override
	public void saveToCurrencyOriginatingCountriesStore(long id) throws Exception {
		try {
			Message message = storage.get(id);
			ArrayList<Long> listMessageIds = null;
			try {
				if(groupOriginatingCountries.containsKey(message.getOriginatingCountry())){
					listMessageIds = groupOriginatingCountries.get(message.getOriginatingCountry());
					try {
						listMessageIds.add(id);
					} catch (Exception e) {
						listMessageIds = new ArrayList<Long>();
						listMessageIds.add(id);
					}
				} else {
					listMessageIds = new ArrayList<Long>();
					listMessageIds.add(message.getId());
				}
			} catch (NullPointerException e) {
				groupOriginatingCountries = new HashMap<String,ArrayList<Long>>();
				listMessageIds = new ArrayList<Long>();
				listMessageIds.add(message.getId());
			}
			groupOriginatingCountries.put(message.getOriginatingCountry(), listMessageIds);
		} catch (Exception e) {
			logger.error("Exception in saveToCurrencyOriginatingCountriesStore ::", e);
			throw e;
		}
	}
	
	public HashMap<String, ArrayList<Long>> getGroupCurrencyTo() {
		return groupCurrencyTo;
	}

	public HashMap<String, ArrayList<Long>> getGroupOriginatingCountries() {
		return groupOriginatingCountries;
	}

	
}
