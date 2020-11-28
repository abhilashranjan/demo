//package demo.example.kafka.streams.serde;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import demo.example.kafka.streams.model.Trade;
//import org.apache.kafka.common.serialization.Serializer;
//
//import java.io.Closeable;
//
//public class TradeSerilizer implements Closeable, AutoCloseable, Serializer<Trade> {
//    @Override
//    public byte[] serialize(String s, Trade trade) {
//        byte[] retVal = null;
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            retVal = objectMapper.writeValueAsString(trade).getBytes();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return retVal;
//    }
//    @Override
//    public void close() {
//
//    }
//}
