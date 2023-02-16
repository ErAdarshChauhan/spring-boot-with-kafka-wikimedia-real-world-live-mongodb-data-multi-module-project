package org.javaintrend.kafka;

import org.javaintrend.entity.Wikimedia;
import org.javaintrend.repository.WikimediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaWikimediaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaWikimediaConsumer.class);

    @Autowired
    private WikimediaRepository wikimediaRepository;

    @KafkaListener(topics = "wikimedia_recent_changes",groupId = "group_id")
    public void consume(String eventMessage){
        LOGGER.info(String.format("Event Message received -> %s",eventMessage));
        Wikimedia wikimedia = new Wikimedia();
        wikimedia.setWikiEventData(eventMessage);
        if (wikimedia!=null) {
            wikimediaRepository.save(wikimedia);
        }
    }
}
