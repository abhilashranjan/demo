package demo.example.kafka.consuemer.service;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Service
public class TradeConsumer {
    private CountDownLatch latch = new CountDownLatch(1);
    private static final String OUTPUT_TRADE_TOPIC = "output_trade_topic";
    public CountDownLatch getLatch() {
        return latch;
    }
    @KafkaListener(topics = OUTPUT_TRADE_TOPIC,groupId = "group_id")
    public void consumeMessage(ConsumerRecord<?, ?> record){
        log.info("MESSAGE TRADE RECIVED [offset = {}, key = {}, value ={}]", record.offset(), record.key(), record.value());;
    }
}