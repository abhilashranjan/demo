package demo.example.kafka.producer.config;

import com.fasterxml.jackson.databind.JsonSerializer;

import demo.example.kafka.producer.model.Trade;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class TradeProducerConfig {
    private static final String INPUT_TRADE_TOPIC = "input_trade_topic";
    private static final String OUTPUT_TRADE_TOPIC = "output_trade_topic";
    @Bean
    public Map<String, Object> tradeConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, TradeSerializer.class);

        return props;
    }

    @Bean
    public ProducerFactory<String, Trade> tradeFactory() {
        return new DefaultKafkaProducerFactory<>(tradeConfigs());
    }

    @Bean
    public KafkaTemplate<String, Trade> kafkaTradeTemplate() {
        return new KafkaTemplate<String, Trade>(tradeFactory());
    }

    @Bean
    public NewTopic mySpringKafkaMessageINPUTTopic() {
        return TopicBuilder.name(INPUT_TRADE_TOPIC)
                .partitions(1)
                .replicas(1)
                .compact()
                .build();
    }

    @Bean
    public NewTopic mySpringKafkaMessageOUTPUTTopic() {
        return TopicBuilder.name(OUTPUT_TRADE_TOPIC)
                .partitions(1)
                .replicas(1)
                .compact()
                .build();
    }
}
