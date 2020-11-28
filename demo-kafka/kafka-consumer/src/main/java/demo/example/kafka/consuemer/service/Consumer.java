package demo.example.kafka.consuemer.service;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Service
public class Consumer {
    private CountDownLatch latch = new CountDownLatch(1);
    private static final String OUTPUT_TOPIC = "output_topic";
    public CountDownLatch getLatch() {
        return latch;
    }
    @KafkaListener(topics = OUTPUT_TOPIC,groupId = "group_id")
    public void consumeMessage(ConsumerRecord<?, ?> record){
        log.info("MESSAGE RECIVED [offset = {}, key = {}, value ={}]", record.offset(), record.key(), record.value());;
    }
}