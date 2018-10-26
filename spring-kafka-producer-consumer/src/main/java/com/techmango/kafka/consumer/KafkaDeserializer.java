package com.techmango.kafka.consumer;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techmango.kafka.MessageVO;

/**
 * 
 * @author Thennarasu
 * SMI_177
 *
 */

public class KafkaDeserializer implements Deserializer<MessageVO> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
	}

	@Override
	public MessageVO deserialize(String topic, byte[] objectData) {
		//return (objectData == null) ? null : (MessageVO) SerializationUtils.deserialize(objectData);
	    ObjectMapper mapper = new ObjectMapper();
	    MessageVO message = null;
	    try {
	        message = mapper.readValue(objectData, MessageVO.class);
	    } catch (Exception e) {
	 
	      e.printStackTrace();
	    }
	    return message;
	}

	@Override
	public void close() {
	}

}
