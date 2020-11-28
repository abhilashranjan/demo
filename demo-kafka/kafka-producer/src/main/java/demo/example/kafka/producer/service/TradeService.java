package demo.example.kafka.producer.service;


import demo.example.kafka.producer.model.Trade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class TradeService {
    private static final String INPUT_TRADE_TOPIC = "input_trade_topic";
    @Autowired
    private KafkaTemplate<String, Trade> kafkaTradeTemplate;

    @Autowired
    FileReader fileReader;

//    public void sendMessage(Trade message) {
//        try {
//            this.kafkaTemplate.send(TOPIC, message);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void sendMessageToTopic(String fileName) throws IOException {
        fileReader.convertFileToTrade(fileName)
                .stream()
                .forEach(trade -> kafkaTradeTemplate.send(INPUT_TRADE_TOPIC,"tradeMessage",trade));
        log.info("Request send to topic");


    }
}
