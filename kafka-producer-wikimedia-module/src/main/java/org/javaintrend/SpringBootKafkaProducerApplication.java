package org.javaintrend;

import org.javaintrend.producer.KafkaWikimediaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootKafkaProducerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootKafkaProducerApplication.class);
    }

    @Autowired
    private KafkaWikimediaProducer kafkaWikimediaProducer;

    @Override
    public void run(String... args) throws Exception {
        kafkaWikimediaProducer.sendMessageToTopic();
    }
}
