package com.processor.utils.test;

import org.json.JSONException;
import org.json.JSONObject;
import static org.junit.Assert.*;
import org.junit.Test;

import com.processor.message.store.Message;
import com.processor.persistence.JSONMessagePersistenceImpl;
import com.processor.utils.JSONUtils;

public class TestJSONUtils {

	@Test
	public void test() {
		try {
			JSONObject json = new JSONObject("{\"userId\": \"134259\", \"currencyFrom\": \"GBP\", \"currencyTo\": \"USD\", \"amountSell\": 1000, \"amountBuy\": 747.10, \"rate\": 0.7471, \"timePlaced\" : \"14-JAN-15 10:27:44\", \"originatingCountry\" : \"US\"}");
			Message message = new JSONMessagePersistenceImpl().getBlankMessage();
			JSONUtils.populateMessageObjectFromJson(json, message);
			assertTrue("The userId property is not populated", 134259 == message.getUserId());
			assertTrue("The currencyFrom property is not populated", "GBP".equals(message.getCurrencyFrom()));
			assertTrue("The currencyTo property is not populated", "USD".equals(message.getCurrencyTo()));
			assertTrue("The amountSell property is not populated", 1000 == message.getAmountSell());
			assertTrue("The amountBuy property is not populated", 747.10 == message.getAmountBuy());
			assertTrue("The rate property is not populated", 0.7471 == message.getRate());
			assertTrue("The originatingCountry property is not populated", "US".equals(message.getOriginatingCountry()));
		} catch (JSONException e) {
			fail("JSONException in test method");
		}
	}
	
	@Test
	public void testGetJSONFromMessage(){
		try {
			JSONObject json = new JSONObject("{\"userId\": \"134259\", \"currencyFrom\": \"GBP\", \"currencyTo\": \"USD\", \"amountSell\": 1000, \"amountBuy\": 747.10, \"rate\": 0.7471, \"timePlaced\" : \"14-JAN-15 10:27:44\", \"originatingCountry\" : \"US\"}");
			Message message = new JSONMessagePersistenceImpl().getBlankMessage();
			JSONUtils.populateMessageObjectFromJson(json, message);
			String jsonStr = JSONUtils.getJSONFromMessage(message);
			JSONObject jsonActual = new JSONObject(jsonStr);
			assertNotNull("The object cannot be null", jsonActual);
		} catch (Exception e) {
			fail("Exception occured in testGetJSONFromMessage");
		}
	}

}
