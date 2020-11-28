package demo.example.kafka.producer;

import demo.example.kafka.producer.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ProducerApplication implements CommandLineRunner {
    @Autowired
    ProducerService producerService;


    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        int count=1;
//        while(true){
//            Thread.sleep(1000);
            log.info("Sending message counter :{}",count++);
            producerService.sendMessageToTopic(".//trade1.json");
//        }
    }
}
