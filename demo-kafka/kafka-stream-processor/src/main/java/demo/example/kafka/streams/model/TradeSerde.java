package demo.example.kafka.streams.model;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class TradeSerde implements Serde<Trade> {
    private TradeSerializer serializer = new TradeSerializer();
    private TradeDeserializer deserializer = new TradeDeserializer();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        serializer.configure(configs, isKey);
        deserializer.configure(configs, isKey);
    }

    @Override
    public void close() {
        serializer.close();
        deserializer.close();
    }

    @Override
    public Serializer<Trade> serializer() {
        return serializer;
    }

    @Override
    public Deserializer<Trade> deserializer() {
        return deserializer;
    }
}