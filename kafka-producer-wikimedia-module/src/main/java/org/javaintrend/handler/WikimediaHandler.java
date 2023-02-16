package org.javaintrend.handler;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.javaintrend.producer.KafkaWikimediaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class WikimediaHandler implements EventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaHandler.class);
    KafkaTemplate<String,String> kafkaTemplate;
    String topicName;

    public WikimediaHandler(KafkaTemplate<String, String> kafkaTemplate, String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        LOGGER.info(String.format("MessageEvent sent %s",messageEvent.getLastEventId()));
        LOGGER.info(String.format("MessageEvent sent %s",messageEvent.getData()));
        kafkaTemplate.send(topicName,messageEvent.getLastEventId());
        kafkaTemplate.send(topicName,messageEvent.getData());
    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
