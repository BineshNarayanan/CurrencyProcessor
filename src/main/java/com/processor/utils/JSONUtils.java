package com.processor.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.processor.message.store.Message;
import com.processor.persistence.JSONMessagePersistenceImpl;

/**
 * JSON Util class for some common functions.
 * @author BNaraya
 *
 */
public abstract class JSONUtils {
	
	private static Logger logger = Logger.getLogger(JSONUtils.class);
	
	final public static void populateMessageObjectFromJson(JSONObject json, Message message){
		try {
			message.setUserId(json.getLong(Message.JSONFIELD_USER_ID));
			message.setCurrencyFrom(json.getString(Message.JSONFIELD_CURRENCY_FROM));
			message.setCurrencyTo(json.getString(Message.JSONFIELD_CURRENCY_TO));
			message.setAmountSell(json.getDouble(Message.JSONFIELD_AMOUNT_SELL));
			message.setAmountBuy(json.getDouble(Message.JSONFIELD_AMOUNT_BUY));
			message.setRate(json.getDouble(Message.JSONFIELD_RATE));
			message.setTimePlaced(json.getString(Message.JSONFIELD_TIME));
			message.setOriginatingCountry(json.getString(Message.JSONFIELD_ORIGINATING_COUNTRY));
		} catch (NumberFormatException | JSONException e) {
			logger.error("NumberFormatException | JSONException in populateMessageObjectFromJson ::", e);
		}
		
	}
	
	final public static void createJSONForChartsUI(String jsonKeyName, JSONObject json,
			HashMap<String, ArrayList<Long>> store) throws JSONException {
		try {
			JSONArray array = new JSONArray();
			Set<String> keys = store.keySet();
			for (String currencyCode : keys) {
				JSONObject obj = new JSONObject();
				obj.put("currencyCode", currencyCode);
				obj.put("value", store.get(currencyCode).size());
				array.put(obj);
			}
			json.put(jsonKeyName, array);
		} catch (JSONException e) {
			logger.error("JSONException in createJSONForChartsUI ::", e);
			throw e;
		}
	}
	
	final public static String getJSONFromMessage(Message message) throws JSONException {
		JSONObject obj = new JSONObject();
		try {
			obj.put(Message.JSONFIELD_MESSAGE_ID, message.getId());
			obj.put(Message.JSONFIELD_USER_ID, message.getUserId());
			obj.put(Message.JSONFIELD_AMOUNT_BUY, message.getAmountBuy());
			obj.put(Message.JSONFIELD_AMOUNT_SELL, message.getAmountSell());
			obj.put(Message.JSONFIELD_RATE, message.getRate());
			obj.put(Message.JSONFIELD_CURRENCY_FROM, message.getCurrencyFrom());
			obj.put(Message.JSONFIELD_CURRENCY_TO, message.getCurrencyTo());
			obj.put(Message.JSONFIELD_ORIGINATING_COUNTRY, message.getOriginatingCountry());
			obj.put(Message.JSONFIELD_TIME, message.getTimePlaced());
		} catch (JSONException e) {
			logger.error("JSONException in getJSONFromMessage ::", e);
			throw e;
		}
		return obj.toString();
	}

}
