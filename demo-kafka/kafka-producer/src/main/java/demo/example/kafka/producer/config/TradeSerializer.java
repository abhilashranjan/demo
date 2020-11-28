package demo.example.kafka.producer.config;

import com.google.gson.Gson;
import demo.example.kafka.producer.model.Trade;
import org.apache.kafka.common.serialization.Serializer;

import java.io.Closeable;
import java.nio.charset.Charset;
import java.util.Map;

public class TradeSerializer implements Closeable, AutoCloseable, Serializer<Trade> {

    private static final Charset CHARSET = Charset.forName("UTF-8");
    static private Gson gson = new Gson();

    @Override
    public void configure(Map<String, ?> map, boolean b) {
    }

    @Override
    public byte[] serialize(String s, Trade person) {
        // Transform the Person object to String
        String line = gson.toJson(person);
        // Return the bytes from the String 'line'
        return line.getBytes(CHARSET);
    }

    @Override
    public void close() {

    }
}