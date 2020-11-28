//package demo.example.kafka.streams.serde;
//
//
//import demo.example.kafka.streams.model.Trade;
//import org.apache.kafka.common.serialization.Deserializer;
//import org.apache.kafka.common.serialization.Serde;
//import org.apache.kafka.common.serialization.Serializer;
//
//public class TradeSerde implements Serde<Trade> {
//    TradeSerilizer tradeSerilizer= new TradeSerilizer();
//    TradeDeserilizer tradeDeserilizer= new TradeDeserilizer();
//
//    @Override
//    public Serializer<Trade> serializer() {
//        return new TradeSerilizer();
//    }
//
//    @Override
//    public Deserializer<Trade> deserializer() {
//        return tradeDeserilizer;
//    }
//
//    @Override
//    public void close() {
//        tradeSerilizer.close();
//        tradeDeserilizer.close();
//    }
//}
//
