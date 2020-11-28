package demo.example.kafka.streams.config;

import demo.example.kafka.streams.model.Trade;

import demo.example.kafka.streams.model.TradeSerde;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@EnableKafkaStreams
public class TradeStreamsConfig {

    private static final String INPUT_TRADE_TOPIC = "input_trade_topic";
    private static final String OUTPUT_TRADE_TOPIC = "output_trade_topic";

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kStreamsTradeConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafkaTradeStreams");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, TradeSerde.class);
        return new KafkaStreamsConfiguration(props);
    }

    @Bean
    public KStream<String, Trade> kStreamTrade(StreamsBuilder kStreamBuilder) {

        KStream<String, Trade> stream = kStreamBuilder.stream(INPUT_TRADE_TOPIC);
        stream.print(Printed.toSysOut());
        stream.filter((key, value) -> value.getSym().equalsIgnoreCase("XXBTZUSD")).to(OUTPUT_TRADE_TOPIC);

        return stream;
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
