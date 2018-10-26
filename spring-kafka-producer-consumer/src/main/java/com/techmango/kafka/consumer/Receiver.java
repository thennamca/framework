package com.techmango.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.techmango.kafka.MessageVO;
/**
 * 
 * @author Thennarasu
 * SMI_177
 *
 */
@Service
public class Receiver {

    private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);

    @KafkaListener(topics = "${app.topic.topics}")
    public void listen(@Payload MessageVO message) {
    	
    	System.out.println("received message"+message.getMessage());
    	System.out.println("received message='{}'"+ message.getAppName());
        LOG.info("received message='{}'", message.getMessage());
        LOG.info("received message='{}'", message.getAppName());
    }

}

