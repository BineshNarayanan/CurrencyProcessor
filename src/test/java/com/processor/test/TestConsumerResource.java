package com.processor.test;

import static org.junit.Assert.*;

import java.net.URI;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class TestConsumerResource extends JerseyTest {

	@Override
    protected Application configure() {
        return new MyApplication();
    }

    @Override
    protected URI getBaseUri() {
        return UriBuilder.fromUri(super.getBaseUri()).path("CurrencyProcessor").build();
    }
	
	@Test
	public void test() {
		String responseString = target().path("/consumer").request().get(String.class);
		System.out.println(responseString);
		assertNotNull(responseString);
	}
	
	
	@Test
	public void testConsumeMessage() {
		Response response = target().path("/consumer/message").request().post(Entity.json("{\"userId\":\"134256\"}"));
        assertEquals(200, response.getStatus());
	}
	
	@Test
	public void testGetChartData(){
		target().path("/consumer/message").request().post(Entity.json("{\"userId\": \"134259\", \"currencyFrom\": \"GBP\", \"currencyTo\": \"USD\", \"amountSell\": 1000, \"amountBuy\": 747.10, \"rate\": 0.7471, \"timePlaced\" : \"14-JAN-15 10:27:44\", \"originatingCountry\" : \"US\"}"));
		Response response = target().path("/consumer/messageDashboard/currencyConversionChart").request().get();
		assertEquals(200 , response.getStatus());
	}
	
	@Test
	public void testAllMessages(){
		Response response = target().path("/consumer/message").request().get();
		assertEquals(200, response.getStatus());
	}

}
