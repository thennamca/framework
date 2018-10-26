package com.techmango.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.techmango.kafka.producer.Sender;
/**
 * 
 * @author Thennarasu
 * SMI_177
 *
 */
@SpringBootApplication
public class ProducerConsumerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProducerConsumerApplication.class, args);
    }

    @Autowired
    private Sender sender;

    @Override
    public void run(String... strings) throws Exception {
        String data = "Spring Kafka Producer and Consumer Example";
        MessageVO message=new MessageVO();
        message.setMessage(data);
        message.setAppName("thenna");
        sender.send(message);
    }

}