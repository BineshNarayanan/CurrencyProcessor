package com.processor.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.processor.message.store.Message;

public abstract class JSONUtils {
	
	public static void populateMessageObjectFromJson(JSONObject json, Message message){
		try {
			message.setUserId(json.getLong("userId"));
			message.setCurrencyFrom(json.getString("currencyFrom"));
			message.setCurrencyTo(json.getString("currencyTo"));
			message.setAmountSell(json.getDouble("amountSell"));
			message.setAmountBuy(json.getDouble("amountBuy"));
			message.setRate(json.getDouble("rate"));
			message.setTimePlaced(json.getString("timePlaced"));
			message.setOriginatingCountry(json.getString("originatingCountry"));
		} catch (NumberFormatException | JSONException e) {
			// TODO Auto-generated catch block
		}
		
	}
	
	public static void createJSONForChartsUI(String jsonKeyName, JSONObject json,
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
			throw e;
		}
	}
	
	public static String getJSONFromMessage(Message message) throws JSONException {
		JSONObject obj = new JSONObject();
		try {
			obj.put("id", message.getId());
			obj.put("userId", message.getUserId());
			obj.put("amountBuy", message.getAmountBuy());
			obj.put("amountSell", message.getAmountSell());
			obj.put("rate", message.getRate());
			obj.put("currencyFrom", message.getCurrencyFrom());
			obj.put("currencyTo", message.getCurrencyTo());
			obj.put("originatingCountry", message.getOriginatingCountry());
			obj.put("time", message.getTimePlaced());
		} catch (JSONException e) {
			throw e;
		}
		return obj.toString();
	}

}
