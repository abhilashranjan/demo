//package demo.example.kafka.streams.serde;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import demo.example.kafka.streams.model.Trade;
//import org.apache.kafka.common.serialization.Deserializer;
//
//import java.io.Closeable;
//
//public class TradeDeserilizer implements Closeable, AutoCloseable, Deserializer<Trade> {
//    @Override
//    public Trade deserialize(String s, byte[] bytes) {
//        ObjectMapper mapper = new ObjectMapper();
//        Trade trade = null;
//        try {
//            trade = mapper.readValue(bytes, Trade.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return trade;
//    }
//
//    @Override
//    public void close() {
//
//    }
//}
