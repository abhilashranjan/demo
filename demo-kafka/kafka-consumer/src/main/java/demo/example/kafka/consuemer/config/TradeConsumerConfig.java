package demo.example.kafka.consuemer.config;


import demo.example.kafka.consuemer.model.Trade;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableKafka
public class TradeConsumerConfig {


  @Bean
  public Map<String, Object> tradeConsumerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, TradeDeserializer.class);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "json");

    return props;
  }

  @Bean
  public ConsumerFactory<String, Trade> tradeConsumerFactory() {
    return new DefaultKafkaConsumerFactory<>(tradeConsumerConfigs(), new StringDeserializer(),
        new JsonDeserializer<>(Trade.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Trade> tradeKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, Trade> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(tradeConsumerFactory());
//    factory.setRecordFilterStrategy(consumerRecord -> consumerRecord.equals("XDS"));

    return factory;
  }

}