package com.processor.exceptions.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.processor.exceptions.MessageProcessException;

public class TestMessageProcessException {

	@Test
	public void test() {
		try {
			throw new MessageProcessException("An Exception occured during processing the message");
		} catch (Exception e) {
			if(e instanceof MessageProcessException){
				assertTrue("The instance of MessageProcessException is not catched successfully.",true);
			} else {
				assertTrue("The instance of MessageProcessException is not catched successfully.",false);
			}
		}
	}

}
