package com.processor.consume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.processor.exceptions.MessageProcessException;
import com.processor.exceptions.MessageStoreException;
import com.processor.message.store.Message;
import com.processor.message.store.MessageStore;
import com.processor.messageprocessor.GroupCurrencyFromProcessor;
import com.processor.messageprocessor.GroupCurrencyToProcessor;
import com.processor.messageprocessor.GroupOriginatingCountriesProcessor;
import com.processor.persistence.JSONMessagePersistenceImpl;
import com.processor.persistence.MessagePersistence;
import com.processor.utils.JSONUtils;
import com.thoughtworks.xstream.XStream;

/**
 * Root resource (exposed at "consumer" path)
 */
@Path("consumer")
public class Consumer {
	
	private static Logger logger = Logger.getLogger(Consumer.class);
	
	MessagePersistence persistanceService = new JSONMessagePersistenceImpl();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    @POST
    @Path("/message")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response consumeMessage(String jsonString) {
    	JSONObject json = null;
    	try {
    		json = new JSONObject(jsonString);
    		Message message = persistanceService.getBlankMessage();
    		JSONUtils.populateMessageObjectFromJson(json, message);
    		if(persistanceService.storeMessage(message)){
    			process(message);	
    		}
		} catch (JSONException | MessageStoreException | MessageProcessException e) {
			logger.error("Exception in consumeMessage of Consumer :: ", e);
			json = new JSONObject();
			try {
				return Response.status(201).entity(e.getMessage()).build();
			} catch (Exception jsone) {
				logger.error("Exception in consumeMessage of Consumer :: ", jsone);
				return Response.status(404).entity(jsone.getMessage()).build();
			}
		}
    	return Response.status(200).entity(json.toString()).build();
	}

	private void process(Message message) throws MessageProcessException {
		try {
			new GroupCurrencyFromProcessor().process(message);
			new GroupCurrencyToProcessor().process(message);
			new GroupOriginatingCountriesProcessor().process(message);
		} catch (MessageProcessException e) {
			logger.error("MessageProcessException in process of Consumer :: ", e);
			throw new MessageProcessException(e.getMessage());
		}
	}
	
	
	@GET
	@Path("/messageDashboard/currencyConversionChart")
    public Response getChartData() {
		try {
			
			HashMap<String, ArrayList<Long>> currencyFrom = persistanceService.getGroupCurrencyFrom();
			HashMap<String, ArrayList<Long>> currencyTo = persistanceService.getGroupCurrencyTo();
			HashMap<String, ArrayList<Long>> originatingCountries = persistanceService.getGroupOriginatingCountry();
			
			JSONObject json = new JSONObject();
			if(null != currencyFrom){
				JSONUtils.createJSONForChartsUI("currencyFromCodeWiseCount", json, currencyFrom);	
			}
			
			if(null != currencyTo) {
				JSONUtils.createJSONForChartsUI("currencyToCodeWiseCount", json, currencyTo);	
			}
			
			if(null != originatingCountries) {
				JSONUtils.createJSONForChartsUI("countryWiseCount", json, originatingCountries);	
			}
			return Response.status(200).entity(json.toString()).build();
		} catch (MessageStoreException | JSONException e) {
			return Response.status(201).entity(e.getMessage()).build();
		}
    }
	
	@GET
	@Path("/message")
	public Response getAllMessages(){
		try {
			HashMap<Long, Message>  allMessages = persistanceService.getMessages();
			JSONObject jsonObj = new JSONObject();
			Set<Long> keySet = allMessages.keySet();
			JSONArray jsonArray = new JSONArray();
			for(long key : keySet){
				jsonArray.put(JSONUtils.getJSONFromMessage(allMessages.get(key)));
			}
			jsonObj.put("allMessages", jsonArray);
			return Response.status(200).entity(jsonObj.toString()).build();
		} catch (Exception e) {
			return Response.status(201).entity(e.getMessage()).build();
		}
			
	}
}
