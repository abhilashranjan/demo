package demo.example.kafka.streams.config;

import demo.example.kafka.streams.model.Trade;
import demo.example.kafka.streams.model.TradeSerde;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.CleanupConfig;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@EnableKafkaStreams
public class KafkaStreamsConfig {

    private static final String INPUT_TOPIC = "input_topic";
    private static final String OUTPUT_TOPIC = "output_topic";
    private static final String INPUT_TRADE_TOPIC = "input_trade_topic";
    private static final String OUTPUT_TRADE_TOPIC = "output_trade_topic";


    @Primary
    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kStreamsConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafkaStreams");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        return new KafkaStreamsConfiguration(props);
    }

    @Bean
    public KStream<String, String> kStream( @Qualifier("defaultKafkaStreamsBuilder") StreamsBuilder kStreamBuilder) {

        KStream<String, String> stream = kStreamBuilder.stream(INPUT_TOPIC);
        stream.print(Printed.toSysOut());
        stream.filter((key, value) -> ((String) value).contains("XXBTZUSD")).to(OUTPUT_TOPIC);

        return stream;
    }

    @Bean
    public NewTopic mySpringKafkaMessageINPUTTopic() {
        return TopicBuilder.name(INPUT_TOPIC)
                .partitions(1)
                .replicas(1)
                .compact()
                .build();
    }

    @Bean
    public NewTopic mySpringKafkaMessageOUTPUTTopic() {
        return TopicBuilder.name(OUTPUT_TOPIC)
                .partitions(1)
                .replicas(1)
                .compact()
                .build();
    }

//    @Bean(name = "commonStringBuilder")
//    public StreamsBuilderFactoryBean commonStringBuilder() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafkaString");
//        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
//        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        KafkaStreamsConfiguration streamsConfig = new KafkaStreamsConfiguration(props);
//        StreamsBuilderFactoryBean streamsBuilder = new StreamsBuilderFactoryBean(streamsConfig);
//        streamsBuilder.setSingleton(Boolean.FALSE);
//        return streamsBuilder;
//    }
//
////    @Bean(name = "tradeStringBuilder")
//    public StreamsBuilderFactoryBean tradeStringBuilder() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "tradeApplication");
//        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
//        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, TradeSerde.class);
////        props.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 4);
//        KafkaStreamsConfiguration streamsConfig = new KafkaStreamsConfiguration(props);
//        CleanupConfig cleanupConfig = new CleanupConfig(Boolean.TRUE, Boolean.TRUE);
//        StreamsBuilderFactoryBean streamsBuilder = new StreamsBuilderFactoryBean(streamsConfig, cleanupConfig);
//        streamsBuilder.setSingleton(Boolean.FALSE);
//        return streamsBuilder;
//    }


    @Primary
    @Qualifier("commonStringBuilderFactory")
    @Bean
    public FactoryBean<StreamsBuilder> commonStringBuilderFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafkaString");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        KafkaStreamsConfiguration streamsConfig = new KafkaStreamsConfiguration(props);
        StreamsBuilderFactoryBean streamsBuilder = new StreamsBuilderFactoryBean(streamsConfig);
        return streamsBuilder;
    }

    @Qualifier("tradeStringBuilderFactory")
    @Bean
    public FactoryBean<StreamsBuilder> tradeStringBuilderFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "tradeApplication");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, TradeSerde.class);
//        props.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 4);
        KafkaStreamsConfiguration streamsConfig = new KafkaStreamsConfiguration(props);
        CleanupConfig cleanupConfig = new CleanupConfig(Boolean.TRUE, Boolean.TRUE);
        StreamsBuilderFactoryBean streamsBuilder = new StreamsBuilderFactoryBean(streamsConfig, cleanupConfig);
        return streamsBuilder;
    }

    @Bean
    public KStream<String, String> bindKStream(@Qualifier("commonStringBuilderFactory") StreamsBuilder commonStringBuilderFactory) throws Exception {
        KStream<String, String> kStream = commonStringBuilderFactory.stream(INPUT_TOPIC);
        kStream.filter((key, value) -> ((String) value).contains("ADAEUR")).to(OUTPUT_TOPIC);
        return kStream;
    }

    @Bean
    public KStream<String, Trade> tradeKstream(@Qualifier("tradeStringBuilderFactory") StreamsBuilder tradeStringBuilderFactory) throws Exception {
        KStream<String, Trade> stream = tradeStringBuilderFactory.stream(INPUT_TRADE_TOPIC);
        stream.print(Printed.toSysOut());
        stream.filter((key, value) -> value.getSym().equalsIgnoreCase("XXBTZUSD")).to(OUTPUT_TRADE_TOPIC);
        return stream;

    }

        @Bean
    public NewTopic mySpringKafkaMessageINPUTTRADETopic() {
        return TopicBuilder.name(INPUT_TRADE_TOPIC)
                .partitions(1)
                .replicas(1)
                .compact()
                .build();
    }

    @Bean
    public NewTopic mySpringKafkaMessageOUTPUTTRADETopic() {
        return TopicBuilder.name(OUTPUT_TRADE_TOPIC)
                .partitions(1)
                .replicas(1)
                .compact()
                .build();
    }
}
