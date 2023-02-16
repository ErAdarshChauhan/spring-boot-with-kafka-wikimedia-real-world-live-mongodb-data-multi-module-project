package org.javaintrend.producer;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.javaintrend.handler.WikimediaHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class KafkaWikimediaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaWikimediaProducer.class);

    private KafkaTemplate<String,String> kafkaTemplate;

    public KafkaWikimediaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessageToTopic() throws InterruptedException {
        String topicName = "wikimedia_recent_changes";
        EventHandler eventHandler = new WikimediaHandler(kafkaTemplate,topicName);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource = builder.build();
        eventSource.start();
        TimeUnit.MINUTES.sleep(1);
    }
}
