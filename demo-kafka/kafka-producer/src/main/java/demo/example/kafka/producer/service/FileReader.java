package demo.example.kafka.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import demo.example.kafka.producer.model.Trade;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileReader {
    public List<String> readFile(File jsonFile) throws IOException {
        return com.google.common.io.Files
                .readLines(jsonFile, Charsets.UTF_8);
    }

    public Trade convertJsonToTrade(String request) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonString = "{\"sym\":\"XETHZUSD\", \"T\":\"Trade\", \"P\":226.85, \"Q\":4.98,\"TS\":1538409733.3502, \"side\": \"b\", \"TS2\":1538409738828643608}";
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        Trade trade = objectMapper.readValue(request, new TypeReference<Trade>() {});
        System.out.println(trade);
        return trade;
    }
    List<Trade> convertFileToTrade(String fileName) throws IOException {
        return readFile(new File(fileName))
                .stream()
                .map(fileJson-> {
                    try {
                        return convertJsonToTrade(fileJson);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).collect(Collectors.toList());
    }

    List<String> convertFileToString(String fileName) throws IOException {
        return readFile(new File(fileName));

    }

}
