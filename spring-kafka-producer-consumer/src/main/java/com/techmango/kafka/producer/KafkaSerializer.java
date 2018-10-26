package com.techmango.kafka.producer;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techmango.kafka.MessageVO;

/**
 * 
 * @author Thennarasu
 * SMI_177
 *
 */
public class KafkaSerializer implements Serializer<MessageVO> {

	private final Logger logger = LoggerFactory.getLogger(KafkaSerializer.class);
	
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		 // Do nothing because of X and Y.
	}

	@Override
	public void close() {
		 // Do nothing because of X and Y.
	}

	@Override
	public byte[] serialize(String topic, MessageVO data) {
		byte[] retVal = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			retVal = objectMapper.writeValueAsString(data).getBytes();
		} catch (JsonProcessingException e) {
			logger.error("Write Value as a String {}",e.getMessage());
		}
		return retVal;
	}

}
