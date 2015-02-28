package com.processor.persistence;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.processor.exceptions.MessageStoreException;
import com.processor.message.store.Message;
import com.processor.message.store.MessageStore;

public class JSONMessagePersistenceImpl implements MessagePersistence {
	
	private static Logger logger = Logger.getLogger(JSONMessagePersistenceImpl.class);

	private MessageStore messageStore = MessageStore.getInstance();
	
	@Override
	public boolean storeMessage(Message message) throws MessageStoreException {
		try {
			if(null != message){
				messageStore.storeMessage(message);
				return true;
			}
		} catch (Exception e) {
			if(null == messageStore){
				throw new MessageStoreException("Message Store Not Initialized!");	
			} else {
				throw new MessageStoreException(e.getMessage());
			}
		}
		return false;
	}

	@Override
	public Message getMessage(long id) throws MessageStoreException {
		Message message = null;
		try {
			if(null != messageStore) {
				message = messageStore.getMessage(id);
			}
		} catch (Exception e) {
			if(null == messageStore){
				throw new MessageStoreException("Message Store is not Initialized!");	
			} else {
				throw new MessageStoreException(e.getMessage());
			}
		}
		return message;
	}

	@Override
	public HashMap<Long, Message> getMessages() throws MessageStoreException {
		HashMap<Long, Message> messages = null;
		try {
			messages =  messageStore.getAllMessagesFromStore();
		} catch (Exception e) {
			if(null == messageStore) {
				throw new MessageStoreException("Message Store is not Initialized!");
			} else {
				throw new MessageStoreException(e.getMessage());
			}
		}
		return messages;
	}

	@Override
	public Message getBlankMessage() {
		return messageStore.getBlankMessage();
	}

	@Override
	public boolean deleteMessage(Message message) throws MessageStoreException {
		try {
			if(null != message){
				return messageStore.deleteMessage(message);	
			}
		} catch (Exception e) {
			throw new MessageStoreException(e.getMessage());
		}
		return false;
		
	}

	@Override
	public boolean saveGroupCurrencyFrom(long id) throws MessageStoreException {
		try {
			messageStore.saveToCurrencyFromStore(id);
			return true;
		} catch (Exception e) {
			logger.error("Exception in saveGroupCurrencyFrom", e);
			throw new MessageStoreException(e.getMessage());
		}
	}

	@Override
	public boolean saveGroupCurrencyTo(long id) throws MessageStoreException {
		try {
			messageStore.saveToCurrencyToStore(id);
			return true;
		} catch (Exception e) {
			logger.error("Exception in saveGroupCurrencyTo", e);
			throw new MessageStoreException(e.getMessage());
		}
	}

	@Override
	public boolean saveGroupOriginatingCountry(long id) throws MessageStoreException {
		try {
			messageStore.saveToCurrencyOriginatingCountriesStore(id);
			return true;
		} catch (Exception e) {
			logger.error("Exception in saveGroupOriginatingCountry", e);
			throw new MessageStoreException(e.getMessage());
		}
	}

	@Override
	public HashMap<String, ArrayList<Long>> getGroupCurrencyFrom() throws MessageStoreException {
		try {
			HashMap<String, ArrayList<Long>> currencyFrom = messageStore.getGroupCurrencyFrom();
			return currencyFrom;
		} catch (Exception e) {
			logger.error("Exception in getGroupCurrencyFrom", e);
			throw new MessageStoreException(e.getMessage());
		}
	}

	@Override
	public HashMap<String, ArrayList<Long>> getGroupCurrencyTo() throws MessageStoreException {
		try {
			HashMap<String, ArrayList<Long>> currencyTo = messageStore.getGroupCurrencyTo();
			return currencyTo;
		} catch (Exception e) {
			logger.error("Exception in getGroupCurrencyTo", e);
			throw new MessageStoreException(e.getMessage());
		}
	}

	@Override
	public HashMap<String, ArrayList<Long>> getGroupOriginatingCountry() throws MessageStoreException {
		try {
			HashMap<String, ArrayList<Long>> originatingCountries = messageStore.getGroupOriginatingCountries();
			return originatingCountries;
		} catch (Exception e) {
			logger.error("Exception in getGroupOriginatingCountry", e);
			throw new MessageStoreException(e.getMessage());
		}
	}


}
