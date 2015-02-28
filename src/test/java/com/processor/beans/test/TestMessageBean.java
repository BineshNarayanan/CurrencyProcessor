package com.processor.beans.test;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.processor.message.store.Message;
import com.processor.message.store.MessageStore;

public class TestMessageBean {

	@Test
	public void test() {
		MessageStore messageStore = MessageStore.getInstance();
		Message message1 = messageStore.getBlankMessage();
		Message message2 = messageStore.getBlankMessage();
		assertFalse("The ids should not be same",message1.getId() == message2.getId());
	}

}
