package com.example.subscriptions.job;

import com.example.subscriptions.kafka.InvalidationCacheKafkaSender;
import com.example.subscriptions.model.entity.User;
import com.example.subscriptions.model.event.ExpiredSubscriptionEvent;
import com.example.subscriptions.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeleteExpiredSubscriptionsService {

    private final SubscriptionService subscriptionService;
    private final InvalidationCacheKafkaSender sender;

    @Scheduled(cron = "${scheduling.cron-expression}")
    @SchedulerLock(
            name = "sendMessageWithDeletedSubscriptions",
            lockAtLeastFor = "PT1M",
            lockAtMostFor = "PT9M"
    )
    public void sendMessageWithDeletedSubscriptions() {

        List<User> usersWithExpiredSubscription = subscriptionService.deleteExpiredSubscriptions();

        if (!usersWithExpiredSubscription.isEmpty()) {
            usersWithExpiredSubscription.forEach(user ->
                    sender.publish(new ExpiredSubscriptionEvent(user.getLogin())));

        }
    }
}
