package com.processor.exceptions.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.processor.exceptions.MessageStoreException;

public class TestMessageStoreException {

	@Test
	public void test() {
		try {
			throw new MessageStoreException("An Exception occured during saving the message");
		} catch (Exception e) {
			if(e instanceof MessageStoreException){
				assertTrue("The instance of Exception is not catched successfully.",true);
			} else {
				assertTrue("The instance of Exception is not catched successfully.",false);
			}
		}
	}

}
