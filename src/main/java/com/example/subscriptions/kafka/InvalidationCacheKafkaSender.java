package com.example.subscriptions.kafka;

import com.example.subscriptions.model.event.ExpiredSubscriptionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
// TODO: по хорошему инвалидация кэша важный этап программы поэтому необходимо добавить сюда outbox: CDC или poller
public class InvalidationCacheKafkaSender {

    private final KafkaTemplate<String, ExpiredSubscriptionEvent> kafkaTemplate;

    @Value("${spring.kafka.subscription-invalidation-topic}")
    private String cacheInvalidationTopic;

    public void publish(ExpiredSubscriptionEvent event) {
        kafkaTemplate.send(cacheInvalidationTopic, event);
    }
}
